package util.uima

import org.apache.uima.jcas.cas.StringArray

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object StringArrayUtils {
  implicit def stringArrayToStringArrayUtils(repr: StringArray): StringArrayUtils = {
    new StringArrayUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr string array
  */
class StringArrayUtils(repr: StringArray) {
  def toSeq: Seq[String] = {
    repr.toArray.toSeq
  }
}