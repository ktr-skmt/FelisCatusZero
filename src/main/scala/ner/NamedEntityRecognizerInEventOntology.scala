package ner

import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Files

import text.normalizer.NormalizedString
import text.{StringNone, StringOption, StringSome}
import time.{TimeExtractorInTimeExpression, TimeMerger, TimeTmp}
import util.Config

import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2015/11/26
  */
object NamedEntityRecognizerInEventOntology extends NamedEntityRecognizer {
  protected override val recognizerName: String = "EventOntology"

  protected override val entityList: NEList = initialize

  protected override def initialize: NEList = {
    val neBuffer: ListBuffer[NE] = ListBuffer.empty[NE]
    Config.eventOntologyClass.toFile.listFiles foreach {
      file: File =>
        val fileName: NEFile = file.getName
        val reader: java.util.Iterator[String] = Files.newBufferedReader(file.toPath, StandardCharsets.UTF_8).lines.iterator
        while (reader.hasNext) {
          val line: NELine = reader.next
          val metaInfo: MetaInfo = (fileName, line)
          val elements: Array[String] = line.split(',')
          if (5 < elements.length) {
            val years: Seq[StringOption] = Seq[StringOption](StringOption(elements(2)), StringOption(elements(3)))
            val time: TimeTmp = TimeMerger.union(
              for (year <- years) yield {
                TimeMerger.union(
                  TimeExtractorInTimeExpression.extract(year))
              }
            )
            for (i <- elements.indices) {
              if (!(Seq[Int](0, 2, 3) contains i)) {
                NormalizedString(StringOption(elements(i).trim)).toStringOption match {
                  case StringSome(str) =>
                    val synonyms: Array[String] = str.split('@')
                    synonyms foreach {
                      synonym: String =>
                        StringOption(synonym.trim) match {
                          case StringSome(text) =>
                            val ne: NE = (text, metaInfo, time, synonyms)
                            neBuffer += ne
                          case StringNone =>
                          //Do nothing
                        }
                    }
                  case StringNone =>
                  //Do nothing
                }
              }
            }
          }
        }
    }
    neBuffer.result
  }
}
