package ner

import java.util.regex.{Matcher, Pattern}

import text.{StringNone, StringOption, StringSome}
import time.TimeTmp

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2015/11/26
  */

trait NamedEntityRecognizer {
  protected type NEFile = String
  protected type NELine = String
  protected type MetaInfo = (NEFile, NELine)
  protected type NEText = String
  protected type NESynonyms = Seq[String]
  protected type NE = (NEText, MetaInfo, TimeTmp, NESynonyms)
  protected type NEList = List[NE]
  protected val recognizerName: String
  protected val entityList: NEList
  def recognize(textOpt: StringOption): Seq[NamedEntity] = {
    textOpt match {
      case StringSome(text) =>
        val buffer: ListBuffer[NamedEntity] = ListBuffer[NamedEntity]()
        entityList foreach {
          case (entity, (file, line), time, synonyms) if text contains entity =>
            val matcher: Matcher = Pattern.compile(Pattern.quote(entity)).matcher(text)
            while (matcher.find) {
              buffer += NamedEntity(
                StringOption(matcher.group),
                time,
                matcher.start until matcher.end,
                StringOption("%s:%s".format(file, line)),
                StringOption(recognizerName),
                synonyms
              )
            }
          case _ =>
          //Do nothing
        }
        buffer.result
      case StringNone =>
        Nil
    }
  }
  protected def initialize: NEList
}
