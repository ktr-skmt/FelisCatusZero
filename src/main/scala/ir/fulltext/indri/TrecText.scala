package ir.fulltext.indri

import text.{StringNone, StringOption, StringSome}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import scala.xml.{NodeSeq, XML}

/**
  * <pre>
  * Created on 2016/12/24.
  * </pre>
  *
  * @author K.Sakamoto
  */
object TrecText {
  private val TAG_NAME_LIST: Seq[(String, String)] = Seq[(String, String)](
    ("docno", "[Dd][Oo][Cc][Nn][Oo]"),
    ("doc", "[Dd][Oo][Cc]"),
    ("score", "[Ss][Cc][Oo][Rr][Ee]"),
    ("title", "[Tt][Ii][Tt][Ll][Ee]"),
    ("text", "[Tt][Ee][Xx][Tt]")
  ).sorted.sortWith((a, b) => a._1.length > b._1.length)

  private val SCORE_REGEX: Regex = """([^\t]+)\t[^\t]+\t[^\t]+\t[^\t]+""".r

  def toIndriResultMap(lines: Iterator[String],
                       keywordOriginalTextOpt: StringOption,
                       expansionOnlyList: Seq[String],
                       indriResultMap: mutable.Map[String, IndriResult]): Map[String, IndriResult] = {
    val dataSet: StringBuilder = new StringBuilder()
    var start: Boolean = false
    var scoreOpt: StringOption = StringNone

    lines foreach {
      line: String =>
        StringOption(line.trim) match {
          case StringSome(lineTmp) =>
            var l: String = lineTmp
            val docStartTag: String = "<[Dd][Oo][Cc]>"
            if (l.matches(docStartTag)) {
              if (!start) {
                start = true
              }
              l = l.replaceFirst(docStartTag, raw"""<doc score="${scoreOpt.getOrElse("0")}">""")
            }
            if (start) {
              dataSet.append(l)
            }
            l match {
              case SCORE_REGEX(score) =>
                scoreOpt = StringOption(score)
              case _ =>
              // Do nothing
            }
          case StringNone =>
          // Do nothing
        }
    }

    val dataNode: NodeSeq = XML.loadString {
      var str: String = raw"""<data_set>${dataSet.result}</data_set>"""
      TAG_NAME_LIST foreach {
        case (tagName, regex) =>
          str = str.
            replaceAll(s"""</$regex>""", s"""</$tagName>""").
            replaceAll(s"""<$regex>""", s"""<$tagName>""")
        case _ =>
        // Do nothing
      }
      str
    }

    (dataNode \ "doc") foreach {
      doc: NodeSeq =>
        val scoreTextOpt: StringOption = StringOption((doc \ "@score").text.trim)
        val docnoOpt: StringOption = StringOption((doc \ "docno").text.trim)
        val titleOpt: StringOption = removeSpace(StringOption((doc \ "title").text.trim))
        val textOpt: StringOption = removeSpace(StringOption((doc \ "text").text.trim))
        if (docnoOpt.nonEmpty && !indriResultMap.contains(docnoOpt.get) && scoreTextOpt.nonEmpty && titleOpt.nonEmpty && textOpt.nonEmpty) {
          val scoreOpt: Option[Double] = try {Option(scoreTextOpt.get.toDouble)} catch {case e: NumberFormatException => e.printStackTrace(); None}
          indriResultMap(docnoOpt.get) = new IndriResult(
            textOpt map {
              text: String =>
                correct(text, keywordOriginalTextOpt, expansionOnlyList)
            },
            docnoOpt,
            titleOpt,
            scoreOpt.getOrElse(0D)
          )
        }
    }

    indriResultMap.toMap
  }

  //character normalization, 表記揺れを直す。
  def correct(text: String, keywordOriginalTextOpt: StringOption, expansionOnlyList: Seq[String]): String = {
    StringOption(
      keywordOriginalTextOpt match {
        case StringSome(keywordOriginalText) =>
          if (expansionOnlyList.isEmpty) {
            text
          } else {
            text.replaceAll(
              expansionOnlyList.
                mkString("(", "|", ")"),
              keywordOriginalText)
          }
        case StringNone =>
          text
      }
    ).get
  }

  //TODO: 形態素レベルのcorrection処理
  //TODO: 文頭・文末処理やその他の文短縮
  //TODO: カッコの削除処理
  //private def correctByMorphemeAnalysis(text: String, textClass: TextClass.Value): String = {
  //  text
  //}

  //private object TextClass extends Enumeration {
  //  val WORD, SENTENCE, PASSAGE, DOCUMENT = Value
  //}

  private def removeSpace(text: StringOption): StringOption = {
    text map {
      t: String =>
        val charBuffer: ListBuffer[Char] = ListBuffer.empty[Char]
        t.trim.foreach {
          case ch if ch != ' ' =>
            charBuffer += ch
          case _ =>
          // Do nothing
        }
        new String(charBuffer.result.toArray)
    }
  }
}
