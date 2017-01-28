package converter

import text.{StringNone, StringOption, StringSome}

import scala.util.matching.Regex

/**
 * <pre>
 * Created on 7/5/15.
 * </pre>
 * @author K.Sakamoto
 */
object KanjiNumeralParser {
  private val numeralNotation1Regex: Regex = """([^兆億万]+兆)?([^兆億万]+億)?([^兆億万]+万)?([^兆億万]*)""".r//("cho", "oku", "man", "num")
  private val numeralNotation2Regex: Regex = """([^千百十]*千)?([^千百十]*百)?([^千百十]*十)?([^千百十]*)""".r//("thousand", "hundred", "ten", "num")
  private val zero: String = "〇"

  def parseKanjiNumerals(kanjiNumerals: StringOption): Option[Long] = {
    kanjiNumerals match {
      case StringSome(kanji) =>
        numeralNotation1Parser(StringOption(kanji.
          replaceAll("ゼロ-zero", zero).//Unidic対策
          replaceAll("零",        zero)
        ))
      case StringNone =>
        None
    }
  }

  private def numeralNotation1Parser(numerals: StringOption): Option[Long] = {
    numerals match {
      case StringSome(n) =>
        n match {
          case numeralNotation1Regex(cho, oku, man, num) =>
            val base: Int = 10000
            val defaultString: String = "a"
            val choStr: String = StringOption(cho).getOrElse(defaultString).init
            val okuStr: String = StringOption(oku).getOrElse(defaultString).init
            val manStr: String = StringOption(man).getOrElse(defaultString).init

            var value: Long = 0L
            value += numeralNotation2Parser(StringOption(choStr)).getOrElse(0L) * base * base * base
            value += numeralNotation2Parser(StringOption(okuStr)).getOrElse(0L) * base * base
            value += numeralNotation2Parser(StringOption(manStr)).getOrElse(0L) * base
            value += numeralNotation2Parser(StringOption(num)).getOrElse(0L)
            Option(value)
          case _ =>
            None
        }

      case StringNone =>
        None
    }
  }

  private def numeralNotation2Parser(numerals: StringOption): Option[Long] = {
    if (numerals.isEmpty || numerals.get == "a") {
      return None
    }

    numerals match {
      case StringSome(n) =>
        n match {
          case numeralNotation2Regex(thousand, hundred, ten, num) =>
            if (StringOption(thousand).nonEmpty ||
              StringOption(hundred).nonEmpty  ||
              StringOption(ten).nonEmpty) {
              val base: Int = 10

              val defaultString: String = "a"
              val thousandStr: String = StringOption(thousand).getOrElse(defaultString).init
              val hundredStr: String = StringOption(hundred).getOrElse(defaultString).init
              val tenStr: String = StringOption(ten).getOrElse(defaultString).init

              var value: Long = 0L
              value += numeralNotation3Parser(StringOption(thousandStr),    hasDefault = true ).getOrElse(0) * base * base * base
              value += numeralNotation3Parser(StringOption(hundredStr),     hasDefault = true ).getOrElse(0) * base * base
              value += numeralNotation3Parser(StringOption(tenStr),         hasDefault = true ).getOrElse(0) * base
              value += numeralNotation3Parser(StringOption(num), hasDefault = false).getOrElse(0)
              Option(value)
            } else {
              numeralNotation4Parser(numerals) map {_.toLong}
            }
          case _ =>
            None
        }
      case StringNone =>
        None
    }
  }

  private def replaceAllKanjiToArabic(numerals: StringOption): StringOption = {
    numerals match {
      case StringSome(n) =>
        StringOption(n.
          replaceAll("一", "1").
          replaceAll("二", "2").
          replaceAll("三", "3").
          replaceAll("四", "4").
          replaceAll("五", "5").
          replaceAll("六", "6").
          replaceAll("七", "7").
          replaceAll("八", "8").
          replaceAll("九", "9").
          replaceAll(zero, "0").
          replaceAll("[^0123456789]", "").
          replaceAll("^0+", ""))
      case StringNone =>
        StringNone
    }
  }

  private def numeralNotation3Parser(numerals: StringOption, hasDefault: Boolean): Option[Int] = {
    if (numerals.isEmpty) {
      if (hasDefault) {
        return Some(1)
      } else {
        return None
      }
    }

    try {
      replaceAllKanjiToArabic(numerals) match {
        case StringSome(n) =>
          Option(n.toInt)
        case StringNone =>
          None
      }
    } catch {
      case e: Exception =>
        e.printStackTrace()
        None
    }
  }

  private def numeralNotation4Parser(numerals: StringOption): Option[Int] = {
    replaceAllKanjiToArabic(numerals) match {
      case StringSome(n) =>
        val numeralArray: Array[String] = n.toCharArray.reverse.map(_.toString)
        var num: Int = 0

        for (i <- numeralArray.indices) {
          try {
            num += (numeralArray(i).toDouble * math.pow(10, i)).toInt
          } catch {
            case e: NumberFormatException =>
              e.printStackTrace()
          }
        }
        Option(num)
      case StringNone =>
        None
    }
  }
}
