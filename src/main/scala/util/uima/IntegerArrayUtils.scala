package util.uima

import org.apache.uima.jcas.cas.IntegerArray

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object IntegerArrayUtils {
  implicit def integerArrayToIntegerArrayUtils(repr: IntegerArray): IntegerArrayUtils = {
    new IntegerArrayUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr integer array
  */
class IntegerArrayUtils(repr: IntegerArray) {
  def toSeq: Seq[Int] = {
    repr.toArray.toSeq
  }
}