package text.similarity

import text.vector.FrequencyVector
import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object SimilarityCalculator {
  def calculate(v1: FrequencyVector, v2: FrequencyVector): Double = {
    Similarity.calculate(v1, v2, Config.similarity)
  }
}

/**
  * @author K.Sakamoto
  * @param vector frequency vector
  */
class SimilarityCalculator(val vector: FrequencyVector) {
  def calculate(v2: FrequencyVector): Double = {
    Similarity.calculate(vector, v2, Config.similarity)
  }
}

/**
  * @author K.Sakamoto
  * @param vectors frequency vectors
  */
class AverageSimilarityCalculator(val vectors: Seq[FrequencyVector]) {
  val calculators: Seq[SimilarityCalculator] = {
    for (vector <- vectors) yield {
      new SimilarityCalculator(vector)
    }
  }
  val size: Int = calculators.size
  def calculate(v2: FrequencyVector): Double = {
    var score: Double = 0D
    calculators foreach {
      calculator: SimilarityCalculator =>
        score += calculator.calculate(v2)
    }
    Divider.divide(score, size)
  }
}