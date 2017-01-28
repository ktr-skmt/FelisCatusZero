package ner

import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path}

import text.normalizer.NormalizedString
import text.{StringNone, StringOption, StringSome}
import time.{TimeExtractorInGlossaryEntries, TimeMerger}
import util.Config

import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

/**
  * @author K.Sakamoto
  *         Created on 2015/11/26
  */
object NamedEntityRecognizerInGlossary extends NamedEntityRecognizer {
  private val quotationRegex1: Regex = """^[「『]([^」』]+)[」』]$""".r
  private val quotationRegex2: Regex = """^(.+)\(([^\)]+)\)(.*)$""".r
  private val titleRegex: Regex = """<[Tt][Ii][Tt][Ll][Ee]>(?<title>[^<]+)</[Tt][Ii][Tt][Ll][Ee]>""".r
  //val textRegex: Regex  = """<[Tt][Ee][Xx][Tt]>(?<text>[^<]+)</[Tt][Ee][Xx][Tt]>""".r

  override protected val recognizerName: String = "Glossary"

  override val entityList: NEList = initialize

  def expand(termOpt: StringOption): Seq[String] = {
    if (termOpt.isEmpty) {
      return Nil
    }
    val term: String = termOpt.get
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    buffer += term
    term match {
      case quotationRegex1(t) =>
        buffer += t
      case _ =>
        // Do nothing
    }
    term match {
      case quotationRegex2(t1, t2, t3) =>
        buffer += t1.concat(t3)
        buffer += t2.concat(t3)
      case _ =>
        // Do nothing
    }
    buffer.result
  }

  private def getTitle(textOpt: StringOption): Seq[Seq[String]] = {
    if (textOpt.isEmpty) {
      return Nil
    }
    val text: String = textOpt.get
    val buffer: ListBuffer[Seq[String]] = ListBuffer.empty[Seq[String]]
    titleRegex.findAllMatchIn(text) foreach {
      mTitle: Regex.Match =>
        NormalizedString(StringOption(mTitle.group("title"))).toStringOption match {
          case StringSome(str) =>
            val synonyms: Array[String] = str.split(',')
            val synonymBuffer: ListBuffer[String] = ListBuffer.empty[String]
            synonyms foreach {
              synonym: String =>
                synonymBuffer ++= expand(StringOption(synonym))
            }
            buffer += synonymBuffer.result
          case StringNone =>
          // Do nothing
        }
    }
    buffer.result
  }

  override protected def initialize: NEList = {
    val buffer: ListBuffer[NE] = ListBuffer.empty[NE]
    Config.trecTextFormatGlossary foreach {
      glossary: Path =>
        val reader: java.util.Iterator[String] = Files.newBufferedReader(glossary, StandardCharsets.UTF_8).lines.iterator
        while (reader.hasNext) {
          val line: String = reader.next
          getTitle(StringOption(line)) foreach {
            synonyms: Seq[String] =>
              synonyms foreach {
                synonym: String =>
                  buffer += ((
                    synonym,
                    (glossary.toFile.getName, ""),
                    TimeMerger.union(TimeExtractorInGlossaryEntries.extract(StringOption(synonym))),
                    synonyms))
              }
          }
        }
    }
    buffer.result
  }
}
