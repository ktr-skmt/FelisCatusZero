package uima.ae

import geography.GeographyExtractorForQuestion
import jeqa.types._
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.cas.FSIterator
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceInitializationException
import question.{QueryGenerator, QuestionFocusAnalyzer}
import text.StringOption
import time.{TimeExtractorForQuestion, TimeTmp}
import util.uima.FSListUtils._
import util.uima.JCasUtils
import util.uima.SeqStringUtils._
import util.uima.SeqUtils._

/**
  * <p>
  *   ExamをQuestionに分解して、それぞれのQuestion Format Typeを推定する。
  *   もし、Question Format Typeが論述なら
  *   <ul>
  *     <li>検索する知識源の種類</li>
  *     <li>必要な記述を検索するためのクエリ</li>
  *     <li>時間・地域など検索結果を絞り込む条件</li>
  *     <li>解候補の適切性を計測するために使用する情報</li>
  *   </ul>
  *   を抽出・生成・推定する。
  * </p>
  * @author K.Sakamoto
  *         Created on 15/10/30
  */
class QuestionAnalyzer extends JCasAnnotator_ImplBase with DocumentAnnotator {
  @throws[ResourceInitializationException]
  override def initialize(aContext: UimaContext): Unit = {
    println(">> Question Analyzer Initializing")
    super.initialize(aContext)
  }

  @throws[AnalysisEngineProcessException]
  override def process(aJCas: JCas): Unit = {
    println(">> Question Analyzer Processing")
    JCasUtils.setAJCasOpt(Option(aJCas))

    @SuppressWarnings(Array[String]("rawtypes"))
    val itExam: FSIterator[Nothing] = aJCas.getAnnotationIndex(Exam.`type`).iterator(true)
    while (itExam.hasNext) {
      val exam: Exam = itExam.next
      val questionSet: FSArray = exam.getQuestionSet
      for (i <- 0 until questionSet.size) {
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val document: Document = question.getDocument
        if (StringOption(document.getText).nonEmpty) {

          annotate(aJCas, document, Nil)
          document.setTitle(question.getLabel)

          question.setDocument(document)

          val sentenceList: Seq[Sentence] = document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]

          //文脈的な時間解析
          val sentenceArray: Array[Sentence] = sentenceList.toArray
          for (j <- 1 until sentenceArray.length) {
            val sentence: Sentence = sentenceArray(j)
            if (Option(sentence.getBeginTime).isEmpty && Option(sentence.getEndTime).isEmpty) {
              val previousSentence: Sentence = sentenceArray(j - 1)
              sentence.setBeginTime(previousSentence.getBeginTime)
              sentence.setEndTime(previousSentence.getEndTime)
            }
          }

          //begin {time limit analysis}
          val timeLimit: TimeTmp = TimeExtractorForQuestion.extract(sentenceList)
          timeLimit.beginTime match {
            case Some(beginTime) =>
              val beginTimeLimit: Time = new Time(aJCas)
              beginTimeLimit.addToIndexes()
              beginTimeLimit.setYear(beginTime)
              beginTimeLimit.setTextList(timeLimit.beginTimeTextList.toStringList)
              question.setBeginTimeLimit(beginTimeLimit)
            case None =>
              // Do nothing
          }

          timeLimit.endTime match {
            case Some(endTime) =>
              val endTimeLimit: Time = new Time(aJCas)
              endTimeLimit.addToIndexes()
              endTimeLimit.setYear(endTime)
              endTimeLimit.setTextList(timeLimit.endTimeTextList.toStringList)
              question.setEndTimeLimit(endTimeLimit)
            case None =>
              // Do nothing
          }
          //end {time limit analysis}

          //begin {geography limit}
          val (areaList, termList): (Seq[String], Seq[String]) = GeographyExtractorForQuestion.extract(sentenceList)
          val geographyLimit: Geography = new Geography(aJCas)
          geographyLimit.addToIndexes()
          geographyLimit.setTermList(termList.toStringList)
          geographyLimit.setArea(areaList.toStringList)
          question.setGeographyLimit(geographyLimit)
          //end {geography limit}

          //begin {question focus}
          question.setQuestionFocusSet(QuestionFocusAnalyzer.analyze(sentenceList).toStringList)
          //end {question focus}

          //begin {question format type}
          question.setQuestionFormatType(
            if (question.getKeywordSet.toSeq.nonEmpty) {
              "essayWithKeywords"
            } else {
              "essayWithoutKeyword"
            }
          )
          //end {question format type}

          //begin {answer format type}
          question.setAnswerFormatType("essay")
          //end {answer format type}

          //begin {lexical answer type}
          //question.setLexicalAnswerTypeSet(document.getContentWordList)
          //end {lexical answer type}

          //begin {semantic answer type}
          //question.setSemanticAnswerTypeSet(document.getContentWordList)
          //end {semantic answer type}

          //begin {query}
          question.setQuerySet(QueryGenerator.generate(aJCas, question).toFSArray)
          //end {query}
        }
      }
    }
  }
}
