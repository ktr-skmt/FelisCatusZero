package util.uima

import org.apache.uima.jcas.cas._

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object IntegerListUtils {
  implicit def integerListToIntegerListUtils(repr: IntegerList): IntegerListUtils = {
    new IntegerListUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr integer list
  */
class IntegerListUtils(repr: IntegerList) {
  def toSeq: Seq[Int] = {
    if (repr == null || repr.isInstanceOf[EmptyIntegerList]) {
      //return an empty seq
      return Nil
    }

    var tail: IntegerList = repr
    val buffer: ListBuffer[Int] = ListBuffer.empty[Int]
    while (!tail.isInstanceOf[EmptyIntegerList] || tail.isInstanceOf[NonEmptyIntegerList]) {
      val nonEmptyIntegerList: NonEmptyIntegerList = tail.asInstanceOf[NonEmptyIntegerList]
      buffer += nonEmptyIntegerList.getHead
      tail = nonEmptyIntegerList.getTail
    }
    buffer.result
  }

  def toArray: Array[Int] = {
    toSeq.toArray
  }
}