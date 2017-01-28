package text

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author K.Sakamoto
 */
class Skip2GramListBuffer {
  private val map: mutable.Map[String, mutable.Map[String, mutable.Map[Int, Int]]] = mutable.Map.empty[String, mutable.Map[String, mutable.Map[Int, Int]]]

  def apply(skip2Gram: Skip2Gram): Skip2GramListBuffer = {
    val firstGram: String = skip2Gram.firstGram.get
    val lastGram: String = skip2Gram.lastGram.get
    val gap: Int = skip2Gram.gap
    val frequency: Int = skip2Gram.frequency
    if (map contains firstGram) {
      if (map(firstGram) contains lastGram) {
        if (map(firstGram)(lastGram) contains gap) {
          map(firstGram)(lastGram)(gap) += frequency
        } else {
          map(firstGram)(lastGram)(gap) = frequency
        }
      } else {
        map(firstGram)(lastGram) = mutable.Map(gap -> frequency)
      }
    } else {
      val temporalMap: mutable.Map[Int, Int] = mutable.Map(gap -> frequency)
      map(firstGram) = mutable.Map(lastGram -> temporalMap)
    }
    this
  }

  def toMap: Map[String, Map[String, Map[Int, Int]]] = {
    val mapTemp: mutable.Map[String, Map[String, Map[Int, Int]]] = mutable.Map.empty[String, Map[String, Map[Int, Int]]]
    map foreach {
      case (firstGram, map1) =>
        val map1Temp: mutable.Map[String, Map[Int, Int]] = mutable.Map.empty[String, Map[Int, Int]]
        map1 foreach {
          case (lastGram, map2) =>
            map1Temp(lastGram) = map2.toMap
          case _ =>
            //Do nothing
        }
        mapTemp(firstGram) = map1Temp.toMap
      case _ =>
        //Do nothing
    }
    mapTemp.toMap
  }

  def result: Skip2GramList = {
    val buffer: ListBuffer[Skip2Gram] = ListBuffer.empty[Skip2Gram]
    toMap foreach {
      case (firstGram, map1) =>
        map1 foreach {
          case (lastGram, map2) =>
            map2 foreach {
              case (gap, frequency) =>
                buffer += Skip2Gram(StringOption(firstGram), StringOption(lastGram), gap, frequency)
              case _ =>
                //Do nothing
            }
          case _ =>
            //Do nothing
        }
      case _ =>
        //Do nothing
    }
    Skip2GramList(buffer.result)
  }
}
