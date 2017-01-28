package text.vector

import converter.UnigramSegmentator
import text.{StringNone, StringOption, StringSome}

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/05/22
  */
class FrequencyVector(val vector: scala.collection.mutable.Map[String, Int]) extends Vector {
  def apply(key: String): Int = {
    if (vector contains key) {
      vector(key)
    } else {
      0
    }
  }

  def increment(key: String): Unit = {
    if (vector contains key) {
      vector(key) += 1
    } else {
      vector(key) = 1
    }
  }

  def toBinaryVector: BinaryVector = {
    new BinaryVector(vector.keys.toSeq)
  }

  def sum: Long = {
    var summation: Long = 0L
    vector foreach {
      case (key, value) =>
        summation += value
      case _ =>
        // Do nothing
    }
    summation
  }

  def squareSum: Long = {
    var summation: Long = 0L
    vector foreach {
      case (key, value) =>
        summation += value * value
      case _ =>
        // Do nothing
    }
    summation
  }

  def keySum: Long = {
    vector.keySet.size
  }

  def keySum(v2: FrequencyVector): Long = {
    val keySet: scala.collection.Set[String] = vector.keySet
    var summation: Long = keySet.size
    v2.vector.keys foreach {
      case key: String if keySet contains key =>
        // Do nothing
      case _ =>
        summation += 1
    }
    summation
  }

  def average: Double = {
    sum / keySum
  }

  def variance: Double = {
    val avg: Double = average
    var summation: Double = 0D
    vector foreach {
      case (key, value) =>
        val diff: Double = value - avg
        summation += diff * diff
      case _ =>
        // Do nothing
    }
    summation / vector.size
  }

  def standardDeviation: Double = {
    math.sqrt(variance)
  }

  def covariance(v2: FrequencyVector): Double = {
    val keySet: scala.collection.Set[String] = vector.keySet | v2.vector.keySet
    val size: Int = keySet.size

    val avgV1: Double = sum.toDouble / size
    val avgV2: Double = v2.sum.toDouble / size

    var summation: Double = 0D
    keySet foreach {
      key: String =>
        summation += (this(key) - avgV1) * (v2(key) - avgV2)
    }
    summation / size
  }

  def innerProduct(v2: FrequencyVector): Long = {
    val vector2: scala.collection.mutable.Map[String, Int] = v2.vector
    val keySetV2: scala.collection.Set[String] = vector2.keySet
    var summation: Long = 0L
    vector foreach {
      case (key1, value1) if keySetV2 contains key1 =>
        summation += value1 * vector2(key1)
      case _ =>
        // Do nothing
    }
    summation
  }

  override def characterLevelIndriQuery: String = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    vector foreach {
      case (term, frequency) =>
        UnigramSegmentator.segmentator.segmentateWithCharacter(StringOption(term)) match {
          case StringSome(unigram) =>
            buffer += s"""$frequency #1($unigram)"""
          case StringNone =>
            // Do nothing
        }
      case _ =>
        // Do nothing
    }
    buffer.result.mkString("#weight(", " ", ")")
  }

  override def wordLevelIndriQuery: String = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    vector foreach {
      case (term, frequency) =>
        buffer += s"""$frequency $term"""
      case _ =>
        // Do nothing
    }
    buffer.result.mkString("#weight(", " ", ")")
  }
}
