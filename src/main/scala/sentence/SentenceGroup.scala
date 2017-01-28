package sentence

import exam.CharacterCounter
import jeqa.types.{Keyword, Sentence, Time}
import text.StringOption
import text.normalizer.NormalizedString

import scala.collection.mutable.ListBuffer

/**
 * @author K.Sakamoto
 * @param keyword keyword
 * @param sentences sentences
 */
class SentenceGroup(val keyword: Keyword, val sentences: Seq[Sentence]) {
  private type CountTextTime = (Int, String, Time, Time)

  def sortedByCharacterCount: (Keyword, List[CountTextTime]) = {
    val buffer: ListBuffer[CountTextTime] = ListBuffer.empty[CountTextTime]
    sentences.foreach {
      sentence: Sentence =>
        val sentenceText: NormalizedString = NormalizedString(StringOption(sentence.getText))
        buffer += ((CharacterCounter.count(sentenceText.toStringOption), sentenceText.toString, sentence.getBeginTime, sentence.getEndTime))
    }
    val sort: Seq[CountTextTime] = buffer.result.sortWith((f1, f2) => f1._1 < f2._1)
    val length: Int = sort.length
    val result: Array[CountTextTime] = new Array[CountTextTime](length)
    for (i <- 0 until length) {
      if (0 < i) {
        result(i) = (sort(i)._1 - sort(i - 1)._1, sort(i)._2, sort(i)._3, sort(i)._4)
      } else if (i == 0) {
        result(i) = (sort(i)._1, sort(i)._2, sort(i)._3, sort(i)._4)
      }
    }
    (keyword, result.toList)
  }

  override def toString: String = {
    """%s
      |Sentences:
      |%s
      |""".stripMargin.format(
        keyword,
        sentencesToString(sentences)
      )
  }

  private def sentencesToString(sentences: Seq[Sentence]) : String = {
    val builder: StringBuilder = new StringBuilder()
    sentences foreach {
      sentence: Sentence =>
        builder.append(sentence.getText)
    }
    builder.result
  }
}
