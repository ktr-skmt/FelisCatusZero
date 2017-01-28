package util.uima

import org.apache.uima.jcas.cas._

/**
  * @author K.Sakamoto
  *         Created on 2016/09/23
  */
object SeqUtils {
  implicit def seqToSeqUtils[T <: TOP](repr: Seq[T]): SeqUtils[T] = {
    new SeqUtils[T](repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr seq
  * @tparam T type
  */
class SeqUtils[T <: TOP](repr: Seq[T]) extends UimaUtilsTrait {
  def toFSList: FSList = {
    if (repr == Nil) {
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

  def toFSArray: FSArray = {
    if (repr == Nil) {
      //return an empty array
      return new FSArray(aJCas, 0)
    }

    val size: Int = repr.size
    val fsArray: FSArray = new FSArray(aJCas, size)
    for (i <- 0 until size) {
      fsArray.set(i, repr(i))
    }
    fsArray
  }
}

/**
  * @author K.Sakamoto
  */
object SeqStringUtils {
  implicit def seqStringToStringUtils(repr: Seq[String]): SeqStringUtils = {
    new SeqStringUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr string seq
  */
class SeqStringUtils(repr: Seq[String]) extends UimaUtilsTrait {
  def toStringList: StringList = {
    if (repr == Nil) {
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

  def toStringArray: StringArray = {
    if (repr == Nil) {
      //return an empty array
      return new StringArray(aJCas, 0)
    }

    val size: Int = repr.size
    val stringArray: StringArray = new StringArray(aJCas, size)
    for (i <- 0 until size) {
      stringArray.set(i, repr(i))
    }
    stringArray
  }
}

/**
  * @author K.Sakamoto
  */
object SeqIntUtils {
  implicit def seqIntToSeqIntUtils(repr: Seq[Int]): SeqIntUtils = {
    new SeqIntUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr integer seq
  */
class SeqIntUtils(repr: Seq[Int]) extends UimaUtilsTrait {
  def toIntegerList: IntegerList = {
    if (repr == Nil) {
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

  def toIntegerArray: IntegerArray = {
    if (repr == Nil) {
      //return an empty array
      return new IntegerArray(aJCas, 0)
    }

    val size: Int = repr.size
    val integerArray: IntegerArray = new IntegerArray(aJCas, size)
    for (i <- 0 until size) {
      integerArray.set(i, repr(i))
    }
    integerArray
  }
}

/**
  * @author K.Sakamoto
  */
object SeqFloatUtils {
  implicit def seqFloatToSeqFloatSeqUtils(repr: Seq[Float]): SeqFloatUtils = {
    new SeqFloatUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr float seq
  */
class SeqFloatUtils(repr: Seq[Float]) extends UimaUtilsTrait {
  def toFloatList: FloatList = {
    if (repr == Nil) {
      //return an empty list
      return new EmptyFloatList(aJCas)
    }

    var head: NonEmptyFloatList = new NonEmptyFloatList(aJCas)
    val list: NonEmptyFloatList = head
    val it: Iterator[Float] = repr.iterator
    while (it.hasNext) {
      head.setHead(it.next)
      if (it.hasNext) {
        head.setTail(new NonEmptyFloatList(aJCas))
        head = head.getTail.asInstanceOf[NonEmptyFloatList]
      } else {
        head.setTail(new EmptyFloatList(aJCas))
      }
    }
    list
  }

  def toFloatArray: FloatArray = {
    if (repr == Nil) {
      //return an empty array
      return new FloatArray(aJCas, 0)
    }

    val size: Int = repr.size
    val floatArray: FloatArray = new FloatArray(aJCas, size)
    for (i <- 0 until size) {
      floatArray.set(i, repr(i))
    }
    floatArray
  }
}