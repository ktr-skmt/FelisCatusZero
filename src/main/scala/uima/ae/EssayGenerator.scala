package uima.ae

import answer.{AnswerCandidatesGenerator, AnswerCandidate, AnswerCandidates}
import exam.CharacterCounter
import jeqa.types._
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.cas.FSIterator
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceInitializationException
import score.noupdate.NoUpdateTypeScorer
import score.update.UpdateTypeScorer
import sentence.{SentenceCombination, SentenceCombinationGenerator, SentenceGroup}
import text.{StringNone, StringOption, StringSome}
import util.Config
import util.uima.FSListUtils._
import util.uima.JCasUtils
import util.uima.SeqUtils._

import scala.collection.mutable.ListBuffer

/**
 * <p>日本語の論述問題を解くプログラム</p>
 * Coverage + MMR
 * @author K.Sakamoto
 *         Created on 15/10/30
 */
class EssayGenerator extends JCasAnnotator_ImplBase {
  private val mIndriScoreIndex: Int = 0
  private val mScoreIndex: Int = 1
  private val mAnswerGenerator: AnswerCandidatesGenerator = new AnswerCandidatesGenerator(mScoreIndex)

  @throws[ResourceInitializationException]
  override def initialize(aContext: UimaContext): Unit = {
    println(">> Essay Generator Initializing")

    super.initialize(aContext)

  }

  private def mmrScore(summary: Seq[Sentence], candidates: Seq[Sentence], scoreIndex: Int): Seq[(Sentence, Double)] = {
    val scorer: NoUpdateTypeScorer = new NoUpdateTypeScorer(summary)
    val candidateArray: Array[Sentence] = candidates.toArray
    for (i <- candidateArray.indices) yield {
      val candidate: Sentence = candidateArray(i)
      val restOfCandidates: Seq[Sentence] = candidateArray.drop(i).toSeq
      (candidate, Config.lambdaOfMMR * candidate.getScoreList(scoreIndex).getScore - (1D - Config.lambdaOfMMR) * scorer.score(restOfCandidates))
    }
  }

  @throws[AnalysisEngineProcessException]
  override def process(aJCas: JCas): Unit = {
    println(">> Essay Generator Processing")
    JCasUtils.setAJCasOpt(Option(aJCas))

    @SuppressWarnings(Array[String]("rawtypes"))
    val itExam: FSIterator[Nothing] = aJCas.getAnnotationIndex(Exam.`type`).iterator(true)
    while (itExam.hasNext) {
      val exam: Exam = itExam.next
      val questionSet: FSArray = exam.getQuestionSet
      for (i <- 0 until questionSet.size) {
        //score sentences
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val questionDocument: Document = question.getDocument
        val scorer: UpdateTypeScorer = {
          if (question.getKeywordSet.toSeq.nonEmpty) {
            new UpdateTypeScorer(questionDocument, mScoreIndex)
          } else {
            new UpdateTypeScorer(questionDocument, mIndriScoreIndex)
          }
        }
        val querySet: FSArray = question.getQuerySet()
        val keywordQueryBuffer: ListBuffer[KeywordQuery] = ListBuffer.empty[KeywordQuery]
        val bowQueryBuffer: ListBuffer[BoWQuery] = ListBuffer.empty[BoWQuery]
        for (j <- 0 until querySet.size()) {
          querySet.get(j) match {
            case query: KeywordQuery =>
              keywordQueryBuffer += query
            case query: BoWQuery =>
              bowQueryBuffer += query
            case _ =>
            // Do nothing
          }
        }
        if (keywordQueryBuffer.nonEmpty) {
          generateAnswer(aJCas, question, keywordQueryBuffer.result.map(query => query.getKeyword), scorer, mScoreIndex)
        } else {
          generateAnswer(aJCas, question, bowQueryBuffer.result.map(query => query.getIndriQuery), scorer, mIndriScoreIndex)
        }
      }
    }
  }

  private def generateAnswer(aJCas: JCas, question: Question, keywordSet: Seq[Keyword], scorer: UpdateTypeScorer, scoreIndex: Int): Unit = {
    val selectedSentenceBuffer: ListBuffer[Sentence] = ListBuffer.empty[Sentence]
    keywordSet foreach {
      keyword: Keyword =>
        val sentenceList: Seq[Sentence] = keyword.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]
        sentenceList foreach {
          sentence: Sentence =>
            scorer.scoreBySentence(sentence)
            selectedSentenceBuffer += sentence
        }
    }

    //select top-N sentences for each keyword
    val sentenceGroupBuffer: ListBuffer[SentenceGroup] = ListBuffer.empty[SentenceGroup]
    keywordSet foreach {
      keyword: Keyword =>
        val sentenceList: Seq[Sentence] = keyword.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]
        sentenceGroupBuffer += new SentenceGroup(
          keyword,
          sentenceList.sortWith {
            (a, b) =>
              a.getScoreList(scoreIndex).getScore > b.getScoreList(scoreIndex).getScore
          }.take(Config.limitOfSentenceSelection)
        )
    }

    //combinations
    val sentenceCombinationGenerator: SentenceCombinationGenerator = new SentenceCombinationGenerator(scoreIndex)
    val sentenceCombinationList: Seq[SentenceCombination] = sentenceCombinationGenerator.
      generate(sentenceGroupBuffer.result, question.getEndCharacterLimit)

    //Maximal Marginal Relevance (MMR) for filling the rest of character limit
    val selectedSentenceList: Seq[Sentence] = selectedSentenceBuffer.result
    val answerCandidateBuffer: ListBuffer[SentenceCombination] = ListBuffer.empty[SentenceCombination]
    val beginCharacterLimit: Int = question.getBeginCharacterLimit
    val endCharacterLimit: Int = question.getEndCharacterLimit
    sentenceCombinationList foreach {
      sentenceCombination: SentenceCombination =>
        val characterNumber: Int = sentenceCombination.characterNumber
        val restOfCharacterLimit: Int = endCharacterLimit - characterNumber
        if (0 < restOfCharacterLimit) {
          //generally come on here because characterNumber < endCharacterLimit in general

          //sort sentences by MMR
          val sentenceMMR: Seq[(Sentence, Double)] = mmrScore(sentenceCombination.sentences, selectedSentenceList, scoreIndex).
            sortWith((a, b) => a._2 > b._2)
          val additionalSentenceBuffer: ListBuffer[Sentence] = ListBuffer.empty[Sentence]
          var counter: Int = sentenceCombination.characterNumber
          sentenceMMR.map(_._1) foreach {
            s: Sentence =>
              val count: Int = CharacterCounter.count(StringOption(s.getText))
              if (counter + count <= endCharacterLimit) {
                counter += count
                additionalSentenceBuffer += s
              }
          }

          if (additionalSentenceBuffer.nonEmpty && beginCharacterLimit <= counter && counter <= endCharacterLimit) {
            answerCandidateBuffer += new SentenceCombination(
              sentenceCombination.sentences ++ additionalSentenceBuffer.result,
              counter,
              sentenceCombination.score,
              sentenceCombination.linkScore,
              sentenceCombination.mergedScores)
          } else if (beginCharacterLimit <= characterNumber && characterNumber <= endCharacterLimit) {
            answerCandidateBuffer += sentenceCombination
          } else {
            // Do nothing
          }
        } else if (beginCharacterLimit <= characterNumber && characterNumber <= endCharacterLimit) {
          // logically, (beginCharacterLimit <=) characterNumber == endCharacterLimit
          answerCandidateBuffer += sentenceCombination
        } else {
          // Do nothing
        }
    }

    if (0 < answerCandidateBuffer.size) {
      val answerResults: AnswerCandidates = mAnswerGenerator.generate(answerCandidateBuffer.result)
      val topAnswerResult: AnswerCandidate = answerResults.answerResults.sortWith((a, b) => a.score > b.score).head
      topAnswerResult.text match {
        case StringSome(text) =>
          val answer: Answer = new Answer(aJCas)
          answer.addToIndexes()
          answer.setIsGoldStandard(false)
          answer.setWriter(Config.systemName)
          val document: Document = new Document(aJCas)
          document.addToIndexes()
          document.setText(text)
          document.setSentenceSet(topAnswerResult.sentenceList.toFSList)
          answer.setDocument(document)
          val answerSet: Seq[Answer] = question.getAnswerSet.toSeq.asInstanceOf[Seq[Answer]]
          question.setAnswerSet((answerSet :+ answer).toFSList)
        case StringNone =>
          generateEmptyAnswer()
      }
    } else {
      generateEmptyAnswer()
    }

    def generateEmptyAnswer(): Unit = {
      val answer: Answer = new Answer(aJCas)
      answer.addToIndexes()
      answer.setIsGoldStandard(false)
      answer.setWriter(Config.systemName)
      val document: Document = new Document(aJCas)
      document.addToIndexes()
      document.setText("")
      answer.setDocument(document)
      val answerSet: Seq[Answer] = question.getAnswerSet.toSeq.asInstanceOf[Seq[Answer]]
      question.setAnswerSet((answerSet:+ answer).toFSList)
    }
  }
}
