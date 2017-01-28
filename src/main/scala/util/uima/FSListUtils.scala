package util.uima

import org.apache.uima.jcas.cas.{EmptyFSList, FSList, NonEmptyFSList, TOP}

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
object FSListUtils {
  implicit def fsListToFSListUtils[T <: TOP](repr: FSList): FSListUtils[TOP] = {
    try {
      new FSListUtils(repr)//, repr.getNthElement(0).getClass)
    } catch {
      case e: IndexOutOfBoundsException =>
        throw e
    }
  }
}

/**
  * @author K.Sakamoto
  * @param repr fs list
  * @tparam T type
  */
class FSListUtils[T <: TOP](repr: FSList) {//, classType: Class[T]) {
  def toSeq: Seq[T]  = {
    if (repr == null || repr.isInstanceOf[EmptyFSList]) {
      //return an empty list
      return Nil
    }

    var tail: FSList = repr
    val buffer: ListBuffer[T] = ListBuffer.empty[T]
    while (!tail.isInstanceOf[EmptyFSList] || tail.isInstanceOf[NonEmptyFSList]) {
      val nonEmptyFSList: NonEmptyFSList = tail.asInstanceOf[NonEmptyFSList]
      buffer += nonEmptyFSList.getHead.asInstanceOf[T]
      tail = nonEmptyFSList.getTail
    }
    buffer.result
  }
}
