package converter

import text.StringOption

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 2014/11/24
 * </pre>
 * @param nGram N-gram
 * @author K.Sakamoto
 */
class NgramSegmentator(nGram: Int) {
  private val DELIMITER: String = "\u0020" //" "

  private def segmentate(elements: Seq[String]): Seq[Array[String]] = {
    val length: Int = elements.length
    val elementArray: Array[String] = new Array[String](length)
    for (i <- 0 until length) {
      elementArray(i) = elements(i)
    }
    val nGramArray: ListBuffer[Array[String]] = ListBuffer.empty[Array[String]]
    for (i <- 0 to length - nGram) {
      val array: Array[String] = java.util.Arrays.copyOfRange(elementArray, i, i + nGram)
      nGramArray += array
    }
    nGramArray
  }

  private def merge(segmentatedElements: Seq[Array[String]]): StringOption = {
    val builder: StringBuilder = new StringBuilder(calculateRequiredSize(segmentatedElements.length))
    var isFirst: Boolean = true
    segmentatedElements foreach {
      segment: Array[String] =>
        if (isFirst) {
          segment foreach {
            builder.append
          }
          isFirst = false
        } else {
          builder.append(DELIMITER)
          segment foreach {
            builder.append
          }
        }
    }
    StringOption(builder.toString)
  }

  private def calculateRequiredSize(size: Int): Int = {
    val min: Int = 32
    val ret: Int = (size - nGram) * 2 + 1
    math.max(min, ret)
  }

  def segmentateWithSpaceChar(segments: Seq[String]): StringOption = {
    val builder: StringBuilder = new StringBuilder()
    var isFirst: Boolean = true
    segments foreach {
      segment: String =>
        if (isFirst) {
          builder.
            append(segment)
          isFirst = false
        } else {
          builder.
            append(DELIMITER).
            append(segment)
        }
    }
    StringOption(builder.toString)
  }

  def segmentateWithCharacter(text: StringOption): StringOption = {
    merge(segmentate({
      import util.StringUtils._
      for (segment <- text.getOrElse("").toCodePointArray) yield {
        new String(Array[Int](segment), 0, nGram)
      }
    }))
  }

  def segmentateWithMorpheme(morphemes: Seq[String]): StringOption = {
    merge(segmentate(morphemes))
  }
}
