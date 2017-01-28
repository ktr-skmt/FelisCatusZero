package util.uima

import org.apache.uima.jcas.cas._

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object FloatListUtils {
  def floatListToFloatListUtils(repr: FloatList): FloatListUtils = {
    new FloatListUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr float list
  */
class FloatListUtils(repr: FloatList) {
  def toSeq: Seq[Float] = {
    if (repr == null || repr.isInstanceOf[EmptyFloatList]) {
      //return an empty seq
      return Nil
    }

    var tail: FloatList = repr
    val buffer: ListBuffer[Float] = ListBuffer.empty[Float]
    while (!tail.isInstanceOf[EmptyFloatList] || tail.isInstanceOf[NonEmptyFloatList]) {
      val nonEmptyFloatList: NonEmptyFloatList = tail.asInstanceOf[NonEmptyFloatList]
      buffer += nonEmptyFloatList.getHead
      tail = nonEmptyFloatList.getTail
    }
    buffer.result
  }

  def toArray: Array[Float] = {
    toSeq.toArray
  }
}
