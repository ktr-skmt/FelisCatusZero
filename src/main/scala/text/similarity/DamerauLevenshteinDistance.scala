package text.similarity

import util.Config

import scala.collection.mutable

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object DamerauLevenshteinDistance extends DamerauLevenshteinDistance(
  Config.damerauLevenshteinDeleteCost,
  Config.damerauLevenshteinInsertCost,
  Config.damerauLevenshteinReplaceCost,
  Config.damerauLevenshteinSwapCost)

/**
  * @author K.Sakamoto
  * @param deleteCost delete cost
  * @param insertCost insert cost
  * @param replaceCost replace cost
  * @param swapCost swap cost
  */
class DamerauLevenshteinDistance(deleteCost: Double, insertCost: Double, replaceCost: Double, swapCost: Double) extends Distance {
  override def calculate[Element](array1: Array[Element], array2: Array[Element]): Double = {
    DirectionTurner.oneMinus(calculateSimilarity(array1, array2))
  }

  def calculateSimilarity[Element](array1: Array[Element], array2: Array[Element]): Double = {
    calculateCost(array1, array2) / math.max(array1.length, array2.length)
  }

  def calculateCost[Element](array1: Array[Element], array2: Array[Element]): Double = {
    val length1: Int = array1.length
    val length2: Int = array2.length


    if (length1 == 0) {
      return length2 * insertCost
    }

    if (length2 == 0) {
      return length1 * deleteCost
    }

    val table: Array[Array[Double]] = Array.ofDim(length1, length2)
    val array1IndexByElement: mutable.Map[Element, Int] = mutable.Map[Element, Int]()
    if (array1(0) != array2(0)) {
      table(0)(0) = math.min(replaceCost, deleteCost + insertCost)
    }
    array1IndexByElement.put(array1(0), 0)

    for (i <- 1 until length1) {
      val deleteDistance: Double = table(i - 1)(0) + deleteCost
      val insertDistance: Double = (i + 1) * deleteCost + insertCost
      val matchDistance: Double = i * deleteCost + (if (array1(i) == array2(0)) 0 else replaceCost)
      table(i)(0) = math.min(math.min(deleteDistance, insertDistance), matchDistance)
    }

    for (j <- 1 until length2) {
      val deleteDistance: Double = (j + 1) * insertCost + deleteCost
      val insertDistance: Double = table(0)(j - 1) + insertCost
      val matchDistance: Double = j * insertCost + (if (array1(0) == array2(j)) 0 else replaceCost)
      table(0)(j) = math.min(math.min(deleteDistance, insertDistance),
        matchDistance)
    }

    for (i <- 1 until length1) {
      var maxArray1LetterMatchIndex: Int = if (array1(i) == array2(0)) 0 else -1
      for (j <- 1 until length2) {
        val candidateSwapIndex: Option[Int] = array1IndexByElement.get(array2(j))
        val jSwap: Int = maxArray1LetterMatchIndex
        val deleteDistance: Double = table(i - 1)(j) + deleteCost
        val insertDistance: Double = table(i)(j - 1) + insertCost
        var matchDistance: Double = table(i - 1)(j - 1)
        if (array1(i) != array2(j)) {
          matchDistance += replaceCost
        } else {
          maxArray1LetterMatchIndex = j
        }
        val swapDistance: Double =
          if (candidateSwapIndex.nonEmpty && jSwap != -1) {
            val iSwap: Int = candidateSwapIndex.get
            val preSwapCost: Double =
              if (iSwap == 0 && jSwap == 0) {
                0
              } else {
                table(math.max(0, iSwap - 1))(math.max(0, jSwap - 1))
              }
            preSwapCost + (i - iSwap - 1) * deleteCost + (j - jSwap - 1) * insertCost + swapCost
          } else {
            Int.MaxValue
          }
        table(i)(j) = math.min(math.min(math.min(deleteDistance, insertDistance), matchDistance), swapDistance)

      }
      array1IndexByElement.put(array1(i), i)
    }

    table(length1 - 1)(length2 - 1)
  }
}
