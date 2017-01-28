package text.similarity

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
trait Distance {
  def calculate(text1: String, text2: String): Double = {
    calculate[Int](text1.codePoints.toArray, text2.codePoints.toArray)
  }

  def calculate[Element](array1: Array[Element], array2: Array[Element]): Double
}
