package util

/**
  * @author K.Sakamoto
  *         Created on 2016/08/08
  */
object FloatUtils {
  implicit def floatToFloatUtils(repr: Float): FloatUtils = {
    new FloatUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr float
  */
class FloatUtils(repr: Float) {
  def toHexString: String = {
    java.lang.Float.toHexString(repr)
  }
}