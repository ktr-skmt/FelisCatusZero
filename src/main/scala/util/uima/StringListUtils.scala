package util.uima

import org.apache.uima.jcas.cas._

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object StringListUtils {
  implicit def stringListToStringListUtils(repr: StringList): StringListUtils = {
    new StringListUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr string list
  */
class StringListUtils(repr: StringList) {
  def toSeq: Seq[String] = {
    if (repr == null || repr.isInstanceOf[EmptyStringList]) {
      //return an empty seq
      return Nil
    }

    var tail: StringList = repr
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    while (!tail.isInstanceOf[EmptyStringList] || tail.isInstanceOf[NonEmptyStringList]) {
      val nonEmptyStringList: NonEmptyStringList = tail.asInstanceOf[NonEmptyStringList]
      buffer += nonEmptyStringList.getHead
      tail = nonEmptyStringList.getTail
    }
    buffer.result
  }

  def toArray: Array[String] = {
    toSeq.toArray
  }
}
