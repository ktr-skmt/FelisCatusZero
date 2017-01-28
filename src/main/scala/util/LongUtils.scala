package util

/**
  * @author K.Sakamoto
  *         Created on 2016/08/08
  */
object LongUtils {
  implicit def longToLongUtils(repr: Long): LongUtils = {
    new LongUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr long
  */
class LongUtils(repr: Long) {
  def toHexString: String = {
    java.lang.Long.toHexString(repr)
  }

  def toOctalString: String = {
    java.lang.Long.toOctalString(repr)
  }

  def toBinaryString: String = {
    java.lang.Long.toBinaryString(repr)
  }
}