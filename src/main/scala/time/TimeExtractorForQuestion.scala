package time

import jeqa.types.{Sentence, Time}
import text.StringOption
import util.uima.StringListUtils._

import scala.collection.mutable.ListBuffer

/**
 * @author K.Sakamoto
 *         Created on 15/10/19
 */
object TimeExtractorForQuestion {
  private def isQuestionSentence(sentence: Sentence): Boolean = {
    Seq[String](
      "述べ",
      "説明",
      "答え",
      "論じ",
      "論述",
      "略述"
    ) foreach {
      case x if StringOption(sentence.getText).nonEmpty && (sentence.getText contains x) =>
        return true
      case _ =>
        // Do nothing
    }
    false
  }

  def extract(sentenceList: Seq[Sentence]): TimeTmp = {
    val timeBuffer: ListBuffer[TimeTmp] = ListBuffer.empty[TimeTmp]
    sentenceList.reverse foreach {
      sentence: Sentence =>
        if (isQuestionSentence(sentence)) {
          val beginTime: Time = sentence.getBeginTime
          val endTime:   Time = sentence.getEndTime
          timeBuffer += new TimeTmp(
            Option(beginTime.getYear),
            Option(endTime.getYear),
            beginTime.getTextList.toSeq,
            endTime.getTextList.toSeq)
        }
    }
    TimeMerger.union(timeBuffer.result)
  }
}
