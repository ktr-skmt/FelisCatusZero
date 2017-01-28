package time

import java.util.regex.{Matcher, Pattern}

import text.{StringNone, StringOption, StringSome}

import scala.util.control.Breaks
import scala.util.matching.Regex
import scala.util.matching.Regex.Match

/**
 * @author K.Sakamoto
 *         Created on 15/10/19
 */
object TimeExtractionPreprocessor {
  private val bracketsRegex: Pattern = Pattern.compile("""\(([^\(\)]+)\)""")
  private val regex: Regex = """(\d+)([^世年月日0-9巻]|\n)""".r

  def convert(text: StringOption): StringOption = {
    RegnalYearParser.convertToRomanCalendar(replaceContentInBrackets(text))
  }

  private def replaceContentInBrackets(text: StringOption): StringOption = {
    text map {
      str: String =>
        var t: String = str
        var start: Int = 0
        var element: String = ""
        var replacement: String = ""
        var m: Matcher = bracketsRegex.matcher(t)
        val b: Breaks = new Breaks()
        b.breakable {
          while (m.find(start)) {
            element = m.group(1)
            insert年(StringOption(element)) match {
              case StringSome(r) =>
                replacement = r
                t = t.replace(element, replacement)
                start += m.end() + replacement.length - element.length
                if (t.length <= start) {
                  b.break
                }
                m = bracketsRegex.matcher(t)
              case StringNone =>
                // Do nothing
            }
          }
        }
        t
    }
  }

  private def insert年(text: StringOption): StringOption = {
    text map {
      str: String =>
        var t: String = str.concat("\n")
        var mOpt: Option[Match] = regex.findFirstMatchIn(t)
        while (mOpt.nonEmpty) {
          val m: Match = mOpt.get
          val g1: String = m.group(1)
          val g2: String = m.group(2)
          //println("A:" + g1)
          //println("B:" + g2)
          t = t.replace(g1.concat(g2), s"${g1}年$g2").trim.concat("\n")
          mOpt = regex.findFirstMatchIn(t)
        }
        t.trim
    }
  }
}
