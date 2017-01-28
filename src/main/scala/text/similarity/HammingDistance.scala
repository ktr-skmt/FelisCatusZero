package text.similarity

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object HammingDistance extends Distance {
  override def calculate[Element](array1: Array[Element], array2: Array[Element]): Double = {
    val length1: Int = array1.length
    val length2: Int = array2.length
    if (length1 != length2) {
      throw new IllegalArgumentException("Arrays must have the same length")
    }
    var counter: Int = 0
    for (i <- 0 until length1) {
      if (array1(i) != array2(i)) {
        counter += 1
      }
    }
    counter.toDouble / length1
  }

  def calculateSimilarity[Element](array1: Array[Element], array2: Array[Element]): Double = {
    1D - calculate(array1, array2)
  }
}
