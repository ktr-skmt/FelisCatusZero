package text.similarity

import text.vector.{BinaryVector, FrequencyVector}
import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object Similarity extends Enumeration {
  val None,
  AverageTwoWayConversions,
  Cosine,
  Covariance,
  Dice,
  InnerProduct,//unnormalized
  Jaccard,
  JaccardSimpson,
  Lin98,
  Mihalcea04,//unnormalized
  PearsonProductMomentCorrelationCoefficient,
  ReciprocalChebyshev,
  ReciprocalEuclidean,
  ReciprocalManhattan,
  ReciprocalMinkowsky,
  Simpson,
  Tversky = Value

  def calculate(v1: FrequencyVector, v2: FrequencyVector, similarity: Similarity.Value): Double = {
    similarity match {
      case AverageTwoWayConversions =>
        calculateAverageTwoWayConversions(v1, v2)
      case Cosine =>
        calculateCosine(v1, v2)
      case Covariance =>
        calculateCovariance(v1, v2)
      case Dice =>
        calculateDice(v1, v2)
      case InnerProduct =>
        calculateInnerProduct(v1, v2)
      case Jaccard =>
        calculateJaccard(v1, v2)
      case JaccardSimpson =>
        calculateJaccardSimpson(v1, v2)
      case Lin98 =>
        calculateLin98(v1, v2)
      case Mihalcea04 =>
        calculateMihalcea04(v1, v2)
      case PearsonProductMomentCorrelationCoefficient =>
        calculatePearsonProductMomentCorrelationCoefficient(v1, v2)
      case ReciprocalChebyshev =>
        calculateReciprocalChebyshev(v1, v2)
      case ReciprocalEuclidean =>
        calculateReciprocalEuclidean(v1, v2)
      case ReciprocalManhattan =>
        calculateReciprocalManhattan(v1, v2)
      case ReciprocalMinkowsky =>
        calculateReciprocalMinkowsky(v1, v2)
      case Simpson =>
        calculateSimpson(v1, v2)
      case Tversky =>
        calculateTversky(v1, v2, Config.tverskyA, Config.tverskyB)
      case _ =>
        0D
    }
  }

  def calculateAverageTwoWayConversions(v1: FrequencyVector, v2: FrequencyVector): Double = {
    (
      OverlapCalculator.calculate(v1.toBinaryVector, v2.toBinaryVector) +
        OverlapCalculator.calculate(v2.toBinaryVector, v1.toBinaryVector)
      ) / 2
  }

  def calculateCosine(v1: FrequencyVector, v2: FrequencyVector): Double = {
    Divider.divide(v1.innerProduct(v2).toDouble, math.sqrt(v1.squareSum * v2.squareSum))
  }

  def calculateCovariance(v1: FrequencyVector, v2: FrequencyVector): Double = {
    v1.covariance(v2)
  }

  def calculateDice(v1: FrequencyVector, v2: FrequencyVector): Double = {
    calculateTversky(v1, v2, 0.5, 0.5)
  }

  def calculateInnerProduct(v1: FrequencyVector, v2: FrequencyVector): Double = {
    v1.innerProduct(v2)
  }

  def calculateJaccard(v1: FrequencyVector, v2: FrequencyVector): Double = {
    calculateTversky(v1, v2, 1, 1)
  }

  def calculateJaccardSimpson(v1: FrequencyVector, v2: FrequencyVector): Double = {
    (calculateJaccard(v1, v2) + calculateSimpson(v1, v2)) / 2
  }

  def calculateLin98(v1: FrequencyVector, v2: FrequencyVector): Double = {
    val vector2: scala.collection.mutable.Map[String, Int] = v2.vector
    var summation: Double = 0D
    v1.vector foreach {
      case (key1, value1) if vector2.contains(key1) =>
        summation += value1 + vector2(key1)
      case _ =>
        // Do nothing
    }
    Divider.divide(summation * 2, v1.sum + v2.sum)
  }

  def calculateMihalcea04(v1: FrequencyVector, v2: FrequencyVector): Double = {
    calculateMihalcea04(v1, v2)
  }

  def calculateMihalcea04(v1: BinaryVector, v2: BinaryVector): Double = {
    Divider.divide(v1.innerProduct(v2), math.log(v1.sum) + math.log(v2.sum))
  }

  def calculatePearsonProductMomentCorrelationCoefficient(v1: FrequencyVector, v2: FrequencyVector): Double = {
    Divider.divide(v1.covariance(v2), v1.variance * v2.variance)
  }

  def calculateReciprocalChebyshev(v1: FrequencyVector, v2: FrequencyVector): Double = {
    DirectionTurner.reciprocal(Dissimilarity.calculate(v1, v2, Dissimilarity.Chebyshev))
  }

  def calculateReciprocalEuclidean(v1: FrequencyVector, v2: FrequencyVector): Double = {
    DirectionTurner.reciprocal(Dissimilarity.calculate(v1, v2, Dissimilarity.Euclidean))
  }

  def calculateReciprocalManhattan(v1: FrequencyVector, v2: FrequencyVector): Double = {
    DirectionTurner.reciprocal(Dissimilarity.calculate(v1, v2, Dissimilarity.Manhattan))
  }

  def calculateReciprocalMinkowsky(v1: FrequencyVector, v2: FrequencyVector): Double = {
    DirectionTurner.reciprocal(Dissimilarity.calculate(v1, v2, Dissimilarity.Minkowsky))
  }

  def calculateSimpson(v1: FrequencyVector, v2: FrequencyVector): Double = {
    Divider.divide(v1.innerProduct(v2), math.min(v1.squareSum, v2.squareSum))
  }

  def calculateTversky(v1: FrequencyVector, v2: FrequencyVector, a: Double, b: Double): Double = {
    val innerProduct: Long = v1.innerProduct(v2)
    Divider.divide(innerProduct, innerProduct + a * (v1.squareSum - innerProduct) + b * (v2.squareSum - innerProduct))
  }
}