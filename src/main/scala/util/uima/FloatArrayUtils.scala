package util.uima

import org.apache.uima.jcas.cas.FloatArray

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object FloatArrayUtils {
  implicit def floatArrayToFloatArrayUtils(repr: FloatArray): FloatArrayUtils = {
    new FloatArrayUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr float array
  */
class FloatArrayUtils(repr: FloatArray) {
  def toSeq: Seq[Float] = {
    repr.toArray.toSeq
  }
}
