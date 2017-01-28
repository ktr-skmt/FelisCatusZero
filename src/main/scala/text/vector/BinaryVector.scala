package text.vector

import converter.UnigramSegmentator
import text.{StringNone, StringOption, StringSome}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/05/22
  */
class BinaryVector(var vector: Seq[String]) extends Vector {
  vector = vector.distinct

  def toFrequencyVector: FrequencyVector = {
    val map: mutable.Map[String, Int] = mutable.Map[String, Int]()
    vector foreach {
      term: String =>
        map(term) = 1
    }
    new FrequencyVector(map)
  }

  def sum: Long = {
    vector.size
  }

  def innerProduct(v2: BinaryVector): Long = {
    vector.intersect(v2.vector).size
  }

  override def characterLevelIndriQuery: String = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    vector foreach {
      element: String =>
        UnigramSegmentator.segmentator.segmentateWithCharacter(StringOption(element)) match {
          case StringSome(unigram) =>
            buffer += s"""#1($unigram)"""
          case StringNone =>
            // Do nothing
        }
    }
    buffer.result.mkString("#combine(", " ", ")")
  }

  override def wordLevelIndriQuery: String = {
    vector.mkString("#combine(", " ", ")")
  }
}
