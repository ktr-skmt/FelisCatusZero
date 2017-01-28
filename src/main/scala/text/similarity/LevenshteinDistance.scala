package text.similarity

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object LevenshteinDistance extends Distance {
  override def calculate[Element](array1: Array[Element], array2: Array[Element]): Double = {
    DirectionTurner.oneMinus(calculateSimilarity(array1, array2))
  }

  def calculateSimilarity[Element](array1: Array[Element], array2: Array[Element]): Double = {
    calculateCost(array1, array2) / math.max(array1.length, array2.length)
  }

  def calculateCost[Element](array1: Array[Element], array2: Array[Element]): Double = {
    val n: Int = array1.length
    var p: Array[Int] = new Array[Int](n + 1)
    var d: Array[Int] = new Array[Int](n + 1)

    val m: Int = array2.length
    if (n == 0 || m == 0) {
      if (n == m) {
        return 1D
      } else {
        return 0D
      }
    }

    for (i <- 0 to n) {
      p(i) = i
    }

    for (j <- 1 to m) {
      val t_j = array2(j-1)
      d(0) = j

      for (i <- 1 to n) {
        val cost = if (array1(i - 1) == t_j) 0 else 1
        d(i) = math.min(math.min(d(i - 1) + 1, p(i) + 1),  p(i - 1) + cost)
      }

      val _d = p
      p = d
      d = _d
    }

    p(n).toDouble
  }
}
