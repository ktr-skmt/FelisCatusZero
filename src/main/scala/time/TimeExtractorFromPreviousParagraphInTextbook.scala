package time
import java.io.{BufferedReader, File}
import java.nio.charset.StandardCharsets
import java.nio.file.Files

import ir.fulltext.indri.{IndriResult, TrecText}
import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author K.Sakamoto
  *         Created on 2016/12/07
  */
object TimeExtractorFromPreviousParagraphInTextbook extends TimeExtractor {
  private val docnoTimeTmpListMap: Map[String, Seq[TimeTmp]] = initialize

  def initialize: Map[String, Seq[TimeTmp]] = {
    val map: mutable.Map[String, Seq[TimeTmp]] = mutable.Map.empty[String, Seq[TimeTmp]]

    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    Config.trecTextFormatData foreach {
      dir: String =>
        new File(dir).listFiles foreach {
          case file: File if file.canRead && file.isFile && file.getName.endsWith(".xml") =>
            val reader: BufferedReader = Files.newBufferedReader(file.toPath, StandardCharsets.UTF_8)
            val iterator: java.util.Iterator[String] = reader.lines.iterator
            while (iterator.hasNext) {
              val line: String = iterator.next
              buffer += line
            }
            reader.close()
          case _ =>
            // Do nothing
        }
    }
    val iterator: Iterator[IndriResult] = TrecText.toIndriResultMap(
      buffer.result.iterator,
      StringNone,
      Nil,
      mutable.Map.empty[String, IndriResult]).valuesIterator
    while (iterator.hasNext) {
      val result: IndriResult = iterator.next
      result.docno match {
        case StringSome(docno) if !map.contains(docno) =>
          map(docno) = TimeExtractorForWorldHistory.extract(result.text)
        case _ =>
          // Do nothing
      }
    }

    map.toMap
  }

  override def extract(docnoOpt: StringOption): Seq[TimeTmp] = {
    docnoOpt match {
      case StringSome(docno) if isOk(docno) =>
        docnoTimeTmpListMap(docno)
      case _ =>
        Nil
    }
  }

  private def isOk(docno: String): Boolean = {
    docno.matches("""^(?:T-WH-[ABS]|Y-JH)-.+$""") &&
      !isFirstDoc(docno) &&
      !docno.startsWith("YamakawaWorldHistoryGlossary") && // just in case
      docnoTimeTmpListMap.contains(docno)
  }

  private def isFirstDoc(docno: String): Boolean = {
    Seq[String](
      "T-WH-A-1-0-0-0",
      "T-WH-B-1-0-0-0",
      "T-WH-S-1-0-0-0",
      "Y-JH-00-00-0"
    ) contains docno
  }
}
