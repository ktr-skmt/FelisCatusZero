package util

import java.util.stream.Collectors

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/**
 * <pre>
 * Created on 6/3/15.
 * </pre>
 * @author K.Sakamoto
 */
object JsoupHelper {
  implicit def elementsToElements4Scala(elements: Elements): Elements4Scala = {
    new Elements4Scala(elements)
  }
}

class Elements4Scala(that: Elements) {
  def toElementArray: Array[Element] = {
    val list: java.util.List[Element] = that.stream.collect(Collectors.toList[Element])
    list.toArray(new Array[Element](list.size()))
  }
}