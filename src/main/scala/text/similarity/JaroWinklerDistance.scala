package text.similarity

import util.Config

import scala.util.control.Breaks

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object JaroWinklerDistance extends JaroWinklerDistance(Config.jaroWinklerThreshold, Config.jaroWinklerScalingFactor)

/**
  * @author K.Sakamoto
  */
object JaroDistance extends JaroWinklerDistance(Config.jaroWinklerThreshold, Config.jaroWinklerScalingFactor) {
  override def calculate[Element](array1: Array[Element], array2: Array[Element]): Double = {
    JaroWinklerDistance.calculateJaroDistance(array1, array2)
  }
}

/**
  * @author K.Sakamoto
  * @param threshold threshold
  * @param scalingFactor scaling factor
  */
class JaroWinklerDistance(threshold: Double, scalingFactor: Double) extends Distance {
  private var prefixLength: Int = 0
  private var prefixScale: Int = 0

  override def calculate[Element](array1: Array[Element], array2: Array[Element]): Double = {
    val jaro: Double = calculateJaroDistance(array1, array2)
    if (jaro < threshold) jaro else jaro + math.min(scalingFactor, 1D / prefixScale) * prefixLength * (1 - jaro)
  }

  def calculateOneMinusDistance(text1: String, text2: String): Double = {
    calculateOneMinusDistance[Int](text1.codePoints.toArray, text2.codePoints.toArray)
  }

  def calculateOneMinusDistance[Element](array1: Array[Element], array2: Array[Element]): Double = {
    1D - calculate(array1, array2)
  }

  def calculateReciprocalDistance(text1: String, text2: String): Double = {
    calculateReciprocalDistance[Int](text1.codePoints.toArray, text2.codePoints.toArray)
  }

  def calculateReciprocalDistance[Element](array1: Array[Element], array2: Array[Element]): Double = {
    val jw: Double = calculate(array1, array2)
    if (jw == 0D) {
      1D
    } else {
      1D / jw
    }
  }

  def calculateJaroDistance[Element](array1: Array[Element], array2: Array[Element]): Double = {
    class PseudoElement(val element: Element)
    def matches(array1: Array[Element], array2: Array[Element]): (Int, Int, Int, Int) = {
      val (max, min): (Array[Element], Array[Element]) =
        if (array2.length < array1.length) {
          (array1, array2)
        } else {
          (array2, array1)
        }
      val range: Int = math.max(max.length / 2 - 1, 0)
      val matchIndexes: Array[Int] = new Array[Int](min.length)
      java.util.Arrays.fill(matchIndexes, -1)
      val matchFlags: Array[Boolean] = new Array[Boolean](max.length)
      var matches: Int = 0
      for (mi <- min.indices) {
        val c1: Element = min(mi)
        val b: Breaks = new Breaks
        b.breakable {
          for (xi <- math.max(mi - range, 0) until math.min(mi + range + 1, max.length)) {
            if (!matchFlags(xi) && c1 == max(xi)) {
              matchIndexes(mi) = xi
              matchFlags(xi) = true
              matches += 1
              b.break
            }
          }
        }
      }
      val ms1: Array[PseudoElement] = new Array[PseudoElement](matches)
      val ms2: Array[PseudoElement] = new Array[PseudoElement](matches)
      var si: Int = 0
      for (i <- min.indices) {
        if (matchIndexes(i) != -1) {
          ms1(si) = new PseudoElement(min(i))
          si += 1
        }
      }
      si = 0
      for (i <- max.indices) {
        if (matchFlags(i)) {
          ms2(si) = new PseudoElement(max(i))
          si += 1
        }
      }
      var transpositions: Int = 0
      for (mi <- ms1.indices) {
        if (ms1(mi).element != ms2(mi).element) {
          transpositions += 1
        }
      }
      var prefix: Int = 0
      val b: Breaks = new Breaks
      b.breakable {
        for (mi <- min.indices) {
          if (array1(mi) == array2(mi)) {
            prefix += 1
          } else {
            b.break
          }
        }
      }
      (matches, transpositions / 2, prefix, max.length)
    }

    val mtp: (Int, Int, Int, Int) = matches(array1, array2)
    val m: Double = mtp._1
    if (m == 0D) {
      return 0D
    }

    prefixLength = mtp._3
    prefixScale = mtp._4

    (m / array1.length + m / array2.length + (m - mtp._2) / m) / 3
  }
}