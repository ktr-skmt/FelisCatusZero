package text.similarity

/**
  * @author K.Sakamoto
  *         Created on 2016/05/23
  */
object Divider {
  def divide(numerator: Double, denominator: Double): Double = {
    if (denominator == 0D) {
      return 0D
    }
    numerator / denominator
  }
}
