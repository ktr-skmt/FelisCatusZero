package evaluation

import text.similarity.Divider
import text.vector.{FrequencyVector, FrequencyVectorGenerator}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * <pre>
  * Created on 2016/12/11.
  * </pre>
  *
  * @author K.Sakamoto
  */
class RougeN(n: Int) extends Rouge {
  override def evaluate(termList: Seq[Seq[String]], modelList: Seq[Seq[Seq[String]]]): Double = {
    val termListTmp: Seq[String] = termList.flatten
    val modelListTmp: Seq[Seq[String]] = for (m <- modelList) yield {m.flatten}
    var summation: Double = 0D
    modelListTmp foreach {
      modelTermList: Seq[String] =>
        summation += evaluateWithOneModel(termListTmp, modelTermList)
    }
    Divider.divide(summation, modelList.size)
  }

  private def evaluateWithOneModel(termList1: Seq[String], termList2: Seq[String]): Double = {
    evaluate(termList2FrequencyVector(termList1), termList2FrequencyVector(termList2))
  }

  private def evaluate(vector1: FrequencyVector, vector2: FrequencyVector): Double = {
    val numerator: Double = calculateNumerator(vector1, vector2).toDouble
    val v1: Double = Divider.divide(numerator, calculateDenominator(vector1))
    val v2: Double = Divider.divide(numerator, calculateDenominator(vector2))
    Divider.divide(2 * v1 * v2, v1 + v2)
  }

  private def termList2FrequencyVector(termList: Seq[String]): FrequencyVector = {
    val nGramList: Seq[String] = termList2NgramList(termList)
    val map: mutable.Map[String, Int] = mutable.Map.empty[String, Int]
    nGramList foreach {
      nGram: String =>
        if (map contains nGram) {
          map(nGram) += 1
        } else {
          map(nGram) = 1
        }
    }
    val buffer: ListBuffer[(String, Int)] = ListBuffer.empty[(String, Int)]
    map foreach {
      case (nGram, frequency) =>
        buffer += ((nGram, frequency))
      case _ =>
        // Do nothing
    }
    FrequencyVectorGenerator.getVector(buffer.result)
  }

  private def termList2NgramList(termList: Seq[String]): Seq[String] = {
    {
      termList.sliding(n) map {
        nGramParts: Seq[String] =>
          nGramParts.mkString("")
      }
    }.toSeq
  }

  private def calculateNumerator(vector1: FrequencyVector, vector2: FrequencyVector): Int = {
    val v1: Map[String, Int] = vector1.vector.toMap
    val v2: Map[String, Int] = vector2.vector.toMap
    var summation: Int = 0
    (vector1.vector.keySet | vector2.vector.keySet) foreach {
      key: String =>
        summation += math.min(getFrequency(v1, key), getFrequency(v2, key))
    }
    summation
  }

  private def getFrequency(vector: Map[String, Int], concept: String): Int = {
    if (vector contains concept) {
      vector(concept)
    } else {
      0
    }
  }

  private def calculateDenominator(vector: FrequencyVector): Int = {
    var summation: Int = 0
    vector.vector foreach {
      case (_, frequency) =>
        summation += frequency
    }
    summation
  }
}
