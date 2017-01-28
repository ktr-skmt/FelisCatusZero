package answer

import jeqa.types.Sentence
import sentence.SentenceCombination
import text.StringOption
import time.TimeSorter

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 2/8/15.
 * </pre>
 * @param scoreIndex score listの中でこのクラスで扱うscoreのindex
 * @author K.Sakamoto
 */
class AnswerCandidatesGenerator(scoreIndex: Int) {
  def generate(sentenceCombinationSet: Seq[SentenceCombination]): AnswerCandidates = {
    val buffer: ListBuffer[AnswerCandidate] = ListBuffer.empty[AnswerCandidate]
    sentenceCombinationSet foreach {
      sentenceCombination: SentenceCombination =>
        var score: Double = 0D
        val sentences: Seq[Sentence] = sentenceCombination.sentences
        sentenceCombination.sentences foreach {
          sentence: Sentence =>
            score += sentence.getScoreList(scoreIndex).getScore
        }
        score /= sentences.size
        val builder: StringBuilder = new StringBuilder(sentenceCombination.characterNumber)
        val sentenceList: Seq[Sentence] = TimeSorter.sort(sentences)
        sentenceList foreach {
          sentence: Sentence =>
            builder.append(sentence.getText)
        }
        buffer += new AnswerCandidate(score, StringOption(builder.result), sentenceList)
    }
    new AnswerCandidates(buffer.result.sortWith((a, b) => a.score > b.score))
  }
}
