package util.uima

import org.apache.uima.jcas.cas.FSArray

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/09/25
  */
trait FSArrayUtilsTrait

/**
  * @author K.Sakamoto
  */
object FSArrayUtils {
  implicit def fsArrayToFSArrayUtils(repr: FSArray): FSArrayUtilsTrait = {
    if (0 < repr.size) {
      throw new IndexOutOfBoundsException()
    }
    new FSArrayUtils(repr, repr.get(0).getClass)
  }
}

/**
  * @author K.Sakamoto
  * @param repr fs array
  * @param classType class
  * @tparam T type
  */
class FSArrayUtils[T](repr: FSArray, classType: Class[T]) extends FSArrayUtilsTrait {
  def toSeq: Seq[T] = {
    val size: Int = repr.size

    if (size == 0) {
      //return an empty seq
      return Nil
    }

    val buffer: ListBuffer[T] = ListBuffer.empty[T]
    for (i <- 0 until size) {
      buffer += repr.get(i).asInstanceOf[T]
    }
    buffer.result
  }
}
