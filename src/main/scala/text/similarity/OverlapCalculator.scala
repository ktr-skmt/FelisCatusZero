package text.similarity

import text.vector.BinaryVector
import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object OverlapCalculator {
  def calculate(v1: BinaryVector, v2: BinaryVector): Double = {
    Overlap.calculate(v1, v2, Config.convergence)
  }
}

/**
  * @author K.Sakamoto
  * @param vector binary vector
  */
class OverlapCalculator(val vector: BinaryVector) {
  def calculate(v2: BinaryVector): Double = {
    Overlap.calculate(vector, v2, Config.convergence)
  }
}

/**
  * @author K.Sakamoto
  * @param vectors binary vectors
  */
class AverageOverlapCalculator(val vectors: Seq[BinaryVector]) {
  val calculators: Seq[OverlapCalculator] = {
    for (vector <- vectors) yield {
      new OverlapCalculator(vector)
    }
  }
  val size: Int = calculators.size
  def calculate(v2: BinaryVector): Double = {
    var score: Double = 0D
    var counter: Int = 0
    calculators foreach {
      calculator: OverlapCalculator =>
        counter += 1
        score += calculator.calculate(v2)
    }
    Divider.divide(score, size)
  }
}
