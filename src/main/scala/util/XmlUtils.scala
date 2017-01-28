package util

import scala.xml.NodeSeq

/**
  * @author K.Sakamoto
  *         Created on 2016/10/27
  */
object XmlUtils {
  implicit def nodeSeq2XmlUtils(nodeSeq: NodeSeq): XmlUtils = {
    new XmlUtils(nodeSeq)
  }
}

/**
  * @author K.Sakamoto
  * @param repr XML
  */
class XmlUtils(repr: NodeSeq) {
  import XmlUtils._

  def attrFilter(name: String, value: String): NodeSeq = {
    repr filter (_ \ ("@" + name) exists (_.text == value))
  }

  def \\@(nodeName: String, attrName: String, value: String): NodeSeq = {
    (repr \\ nodeName).attrFilter(attrName, value)
  }

  def \@(nodeName: String, attrName: String, value: String): NodeSeq = {
    (repr \ nodeName).attrFilter(attrName, value)
  }
}
