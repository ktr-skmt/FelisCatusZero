package util.uima

import org.apache.uima.jcas.cas._

/**
  * @author K.Sakamoto
  *         Created on 2016/09/23
  */
object ArrayUtils {
  implicit def arrayToArrayUtils[T <: TOP](repr: Array[T]): ArrayUtils[T] = {
    new ArrayUtils[T](repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr array
  * @tparam T type
  */
class ArrayUtils[T <: TOP](repr: Array[T]) extends UimaUtilsTrait {
  def toFSArray: FSArray = {
    val size: Int = repr.length
    val fsArray: FSArray = new FSArray(aJCas, size)
    for (i <- 0 until size) {
      fsArray.set(i, repr(i))
    }
    fsArray
  }

  def toFSList: FSList = {
    if (repr.isEmpty) {
      //return an empty list
      return new EmptyFSList(aJCas)
    }

    var head: NonEmptyFSList = new NonEmptyFSList(aJCas)
    val list: NonEmptyFSList = head
    val it: Iterator[T] = repr.iterator
    while (it.hasNext) {
      head.setHead(it.next)
      if (it.hasNext) {
        head.setTail(new NonEmptyFSList(aJCas))
        head = head.getTail.asInstanceOf[NonEmptyFSList]
      } else {
        head.setTail(new EmptyFSList(aJCas))
      }
    }
    list
  }
}

/**
  * @author K.Sakamoto
  */
object ArrayStringUtils {
  implicit def arrayStringToArrayStringUtils(repr: Array[String]): ArrayStringUtils = {
    new ArrayStringUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr string array
  */
class ArrayStringUtils(repr: Array[String]) extends UimaUtilsTrait {
  def toStringArray: StringArray = {
    val size: Int = repr.length
    val stringArray: StringArray = new StringArray(aJCas, size)
    for (i <- 0 until size) {
      stringArray.set(i, repr(i))
    }
    stringArray
  }

  def toStringList: StringList = {
    if (repr.isEmpty) {
      //return an empty list
      return new EmptyStringList(aJCas)
    }

    var head: NonEmptyStringList = new NonEmptyStringList(aJCas)
    val list: NonEmptyStringList = head
    val it: Iterator[String] = repr.iterator
    while (it.hasNext) {
      head.setHead(it.next)
      if (it.hasNext) {
        head.setTail(new NonEmptyStringList(aJCas))
        head = head.getTail.asInstanceOf[NonEmptyStringList]
      } else {
        head.setTail(new EmptyStringList(aJCas))
      }
    }
    list
  }
}

/**
  * @author K.Sakamoto
  */
object ArrayIntegerUtils {
  implicit def arrayIntegerToArrayIntegerUtils(repr: Array[Int]): ArrayIntegerUtils = {
    new ArrayIntegerUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr integer array
  */
class ArrayIntegerUtils(repr: Array[Int]) extends UimaUtilsTrait {
  def toIntegerArray: IntegerArray = {
    val size: Int = repr.length
    val integerArray: IntegerArray = new IntegerArray(aJCas, size)
    for (i <- 0 until size) {
      integerArray.set(i, size)
    }
    integerArray
  }

  def toIntegerList: IntegerList = {
    if (repr.isEmpty) {
      //return an empty list
      return new EmptyIntegerList(aJCas)
    }

    var head: NonEmptyIntegerList = new NonEmptyIntegerList(aJCas)
    val list: NonEmptyIntegerList = head
    val it: Iterator[Int] = repr.iterator
    while (it.hasNext) {
      head.setHead(it.next)
      if (it.hasNext) {
        head.setTail(new NonEmptyIntegerList(aJCas))
        head = head.getTail.asInstanceOf[NonEmptyIntegerList]
      } else {
        head.setTail(new EmptyIntegerList(aJCas))
      }
    }
    list
  }
}

/**
  * @author K.Sakamoto
  */
object ArrayFloatUtils {
  implicit def arrayFloatToArrayFloatUtils(repr: Array[Float]): ArrayFloatUtils = {
    new ArrayFloatUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr float array
  */
class ArrayFloatUtils(repr: Array[Float]) extends UimaUtilsTrait {
  def toFloatArray: FloatArray = {
    val size: Int = repr.length
    val floatArray: FloatArray = new FloatArray(aJCas, size)
    for (i <- 0 until size) {
      floatArray.set(i, size)
    }
    floatArray
  }

  def toFloatList: FloatList = {
    if (repr.isEmpty) {
      //return an empty list
      return new EmptyFloatList(aJCas)
    }

    var head: NonEmptyFloatList = new NonEmptyFloatList(aJCas)
    val list: NonEmptyFloatList = head
    val it: Iterator[Float] = repr.iterator
    while (it.hasNext) {
      if (it.hasNext) {
        head.setTail(new NonEmptyFloatList(aJCas))
        head = head.getTail.asInstanceOf[NonEmptyFloatList]
      } else {
        head.setTail(new EmptyFloatList(aJCas))
      }
    }
    list
  }
}