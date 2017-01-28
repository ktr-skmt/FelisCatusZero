package time

import java.io.{BufferedReader, IOException}
import java.nio.file.{Files, Paths}

import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * @author K.Sakamoto
 *         Created on 15/10/19
 */
object TimeExtractorInGlossaryEntries extends TimeExtractor {
  private val entryWordAndTimeMap: Map[String, TimeTmp] = {
    val map: mutable.Map[String, TimeTmp] = mutable.Map[String, TimeTmp]()
    val readerOpt: Option[BufferedReader] = Option(
      Files.newBufferedReader(
        Paths.get(Config.glossaryEntriesForTimeExtractor)))
    try {
      if (readerOpt.nonEmpty) {
        val reader: BufferedReader = readerOpt.get
        val lineIt: java.util.Iterator[String] = reader.lines.iterator
        while (lineIt.hasNext) {
          val line: StringOption = StringOption(lineIt.next)
          parseLine(line) match {
            case Some((entry, time)) =>
              map(entry) = time
            case _ =>
              //Do nothing
          }
        }
      }
      map.toMap
    } catch {
      case e: IOException =>
        e.printStackTrace()
        Map.empty[String, TimeTmp]
    } finally {
      try {
        if (readerOpt.nonEmpty) {
          readerOpt.get.close()
        }
      } catch {
        case e: IOException =>
          e.printStackTrace()
      }
    }
  }
  private val entryWords: Iterator[String] = entryWordAndTimeMap.keysIterator

  override def extract(text: StringOption): Seq[TimeTmp] = {
    text match {
      case StringSome(t) =>
        val buffer: ListBuffer[TimeTmp] = ListBuffer.empty[TimeTmp]
        while (entryWords.hasNext) {
          val entryWord: String = entryWords.next
          if (t contains entryWord) {
            buffer += entryWordAndTimeMap(entryWord)
          }
        }
        buffer.result
      case StringNone =>
        Nil
    }
  }

  private def parseLine(line: StringOption): Option[(String, TimeTmp)] = {
    line match {
      case StringSome(l) =>
        val csv: Array[String] = l.split(',')
        if (csv.length == 4) {
          val entry: String = csv.head
          def toInt(str: String): Option[Int] = {
            StringOption(str) match {
              case StringSome(s) =>
                try {
                  Option(s.toInt)
                } catch {
                  case e: Exception =>
                    None
                }
              case StringNone =>
                None
            }
          }
          val begin: Option[Int] = toInt(csv(1))
          val end:   Option[Int] = toInt(csv(2))
          val text: String  = csv(3)
          try {
            Option(
              entry,
              new TimeTmp(
                begin,
                end,
                if (begin.nonEmpty) Seq[String](text) else Nil,
                if (end.nonEmpty)   Seq[String](text) else Nil
              )
            )
          } catch {
            case e: NumberFormatException =>
              e.printStackTrace()
              None
          }
        } else {
          None
        }
      case StringNone =>
        None
    }
  }
}
