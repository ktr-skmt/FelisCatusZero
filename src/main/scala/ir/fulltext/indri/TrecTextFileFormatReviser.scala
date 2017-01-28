package ir.fulltext.indri

import java.io.{BufferedReader, File, IOException, PrintWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths, StandardOpenOption}

import converter.{ExtensionReviser, NgramSegmentator}
import text.analyzer.JapaneseSentenceSplitter
import text.analyzer.mor.mecab.UnidicMecab
import text.{StringNone, StringOption, StringSome}
import text.normalizer.NormalizedString

import scala.util.matching.Regex

/**
  * <pre>
  * Created on 2017/01/13.
  * </pre>
  *
  * @author K.Sakamoto
  */
class TrecTextFileFormatReviser(nGram: Int, isChar: Boolean) {
  //private val regexTagHead: Regex = "^<[^>]+>".r
  private def formatTitle(title: String): String = {
    //<TITLE>{title}</TITLE>.toString
    "<TITLE>%s</TITLE>".format(title)
  }
  private def formatText(text: String): String = {
    "<TEXT>%s</TEXT>".format(text)
  }
  private val regexTitleTag: Regex = formatTitle("([^<]+)").r
  private val segmentator: NgramSegmentator = new NgramSegmentator(nGram)
  private val extensionReviser: ExtensionReviser = new ExtensionReviser("xml")
  private val regexTextTag: Regex = formatText("([^<]*)").r
  private val regexTextStartTag: Regex = "<TEXT>([^<]*)$".r
  private val regexTextEndTag: Regex   = "^([^<]*)</TEXT>".r

  def reviseInDirectory(inputDirectoryPath: Path, outputDirectoryPath: Path): Unit = {
    try {
      val iterator: java.util.Iterator[Path] = Files.newDirectoryStream(inputDirectoryPath).iterator
      while (iterator.hasNext) {
        val next: Path = iterator.next
        if (!next.toFile.getName.endsWith(".DS_Store")) {
          revise(
            next,
            outputDirectoryPath
          )
        }
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
    }
  }

  def revise(inputPath: Path, outputDirectoryPath: Path): Unit = {
    val reader: BufferedReader = Files.newBufferedReader(
      inputPath,
      StandardCharsets.UTF_8
    )
    val readerIterator: java.util.Iterator[String] = reader.lines.iterator
    val path: Path = extensionReviser.revise(
      Paths.get(outputDirectoryPath.toString, inputPath.toAbsolutePath.toString.split(File.separator).last)
    )
    val writer: PrintWriter = new PrintWriter(
      Files.newBufferedWriter(
        path,
        StandardCharsets.UTF_8,
        StandardOpenOption.CREATE
      )
    )
    var isText: Boolean = false

    try {
      while (readerIterator.hasNext) {
        StringOption(readerIterator.next) match {
          case StringSome(line) =>
            StringOption(reviseTag(line) match {
              case text if text contains "<TEXT></TEXT>" =>
                isText = false
                "<TEXT></TEXT>"
              case regexTextTag(text) =>
                formatText("\n%s\n" format segment(StringOption(normalize(text))))
              case regexTextStartTag(text) if !isText =>
                isText = true
                "<TEXT>".concat(normalize(text))
              case regexTextEndTag(text) if isText =>
                isText = false
                normalize(text).concat("</TEXT>")
              case text if isText =>
                segment(StringOption(normalize(text)))
              case regexTitleTag(title) =>
                formatTitle(segment(StringOption(NormalizedString(StringOption(title)).toString)))
              case otherwise =>
                otherwise
            }) match {
              case StringSome(l) =>
                writer.println(l)
              case StringNone =>
                // Do nothing
            }
          case StringNone =>
            // Do nothing
        }
      }
    } catch {
      case e: IOException =>
        e.printStackTrace()
    } finally {
      try {
        writer.close()
      } catch {
        case e: IOException =>
          e.printStackTrace()
      }
    }
  }

  private def segment(text: StringOption): String = {
    if (isChar) {
      segmentator.segmentateWithCharacter(text).getOrElse("")
    } else {
      segmentator.segmentateWithMorpheme(UnidicMecab.extractWords(text)).getOrElse("")
    }
  }

  private def reviseTag(line: String): String = {
    line.
      replaceAll("<[dD][oO][cC]>", "<DOC>").
      replaceAll("</[dD][oO][cC]>", "</DOC>").
      replaceAll("<[dD][oO][cC][nN][oO]>", "<DOCNO>").
      replaceAll("</[dD][oO][cC][nN][oO]>", "</DOCNO>").
      replaceAll("<[tT][eE][xX][tT]>", "<TEXT>").
      replaceAll("</[tT][eE][xX][tT]>", "</TEXT>").
      replaceAll("<[tT][iI][tT][lL][eE]>", "<TITLE>").
      replaceAll("</[tT][iI][tT][lL][eE]>", "</TITLE>")
  }

  private def normalize(line: String): String = {
    val builder: StringBuilder = new StringBuilder()
    JapaneseSentenceSplitter.split(StringOption(line)) foreach {
      sentence =>
        builder.append(sentence.text)
    }
    builder.result
  }
}