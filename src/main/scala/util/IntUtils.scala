package util

/**
  * @author K.Sakamoto
  *         Created on 2016/08/08
  */
object IntUtils {
  implicit def intToIntUtils(repr: Int): IntUtils = {
    new IntUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr integer
  */
class IntUtils(repr: Int) {
  def toHexString: String = {
    java.lang.Integer.toHexString(repr)
  }

  def toOctalString: String = {
    java.lang.Integer.toOctalString(repr)
  }

  def toBinaryString: String = {
    java.lang.Integer.toBinaryString(repr)
  }
}
