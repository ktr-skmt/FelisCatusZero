package time

import text.{StringNone, StringOption, StringSome}

import scala.util.matching.Regex
import scala.util.matching.Regex.Match

/**
 * <pre>
 * Created on 11/19/14.
 * </pre>
 * @author K.Sakamoto
 */
object TimeExtractorInTimeExpression extends TimeExtractor {
  private var romanCalendar: Int = 0

/*
//４０年代の場合、ひとつ前の年が1801年なら、1840年代と解釈するが、
//ひとつ前の年がわからない場合は、1940年代と解釈する処理
  def correctTime(times: Seq[Time]): Seq[Time] = {
    val size = times.size
    if (1 < size) {
      val buffer = ListBuffer[Time]()
      buffer += times(0)
      {for (i <- 1 until times.size) yield {
        var yearBegin = times(i).yearBegin
        var yearEnd   = times(i).yearEnd
        yearBegin match {
          case Some(begin) =>
            yearEnd match {
              case Some(end) =>
                if (0 < begin && begin < 100) {

                } else {

                }
              case None =>
            }
          case None =>
            yearEnd match {
              case Some(end) =>
              case None =>
                times(i)
            }
        }
      }}.toList ++= buffer
      buffer.result()
    } else {
      times
    }
  }
*/
  private type BeginCountOptions = (Option[Int], Option[Int])
  private type BeginEndOptions   = (Option[Int], Option[Int])

  override def extract(sentence: StringOption): Seq[TimeTmp] = {
    val timeRegex: Regex = """(紀元前|前)?([0-9]*)?(年間|年分|年にわたる|年に渡る|千年紀|世紀|年代|年|現代|近代|近現代|近世|中世|古代)?(前半|後半|初頭|末|初期|末期|初め|はじめ|終り|終わり|おわり)?(ころ|ごろ|頃|辺り|あたり|らへん|前後)?(以前|以後|から|まで)?""".r
    var time: Seq[String] = Nil

    {for (m <- timeRegex.findAllMatchIn(
      TimeExtractionPreprocessor.convert(sentence).getOrElse("")
    ) if isTime(m)) yield {
      time = Seq[String](m.group(0))
      val (beginOpt, endOpt): (Option[Int], Option[Int]) =
        reviseThird(
          reviseSecond(
            reviseFirst(
              parseTime(
                toIntOption(
                  StringOption(m.group(2))
                ),
                m.group(3),
                StringOption(m.group(1))),
              m.group(4)),
            m.group(5)),
          m.group(6))

      new TimeTmp(
        beginOpt,
        endOpt,
        if (beginOpt.nonEmpty) time else Nil,
        if (endOpt.nonEmpty)   time else Nil)
    }}.toSeq
  }

  private def parse千年紀(numOpt: Option[Int], clueOpt: StringOption): BeginCountOptions = {
    numOpt match {
      case Some(n) =>
        if (clueOpt.nonEmpty) {
          (Option(n * -1000), Option(999))
        } else {
          (Option((n - 1) * 1000 + 1), Option(999))
        }
      case None =>
        (None, None)
    }
  }

  private def parse世紀(numOpt: Option[Int], clueOpt: StringOption): BeginCountOptions = {
    numOpt match {
      case Some(n) =>
        if (clueOpt.nonEmpty) {
          (Option(n * -100), Option(99))
        } else {
          (Option((n - 1) * 100 + 1), Option(99))
        }
      case None =>
        (None, None)
    }
  }

  private def parse年代(numOpt: Option[Int], clueOpt: StringOption): BeginCountOptions = {
    numOpt match {
      case Some(n) =>
        if (clueOpt.nonEmpty) {
          (Option(n * -1 - 9), Option(10))
        } else {
          (Option(n), Option(10))
        }
      case None =>
        (None, None)
    }
  }

  private def parse年(numOpt: Option[Int], clueOpt: StringOption): BeginCountOptions = {
    numOpt match {
      case Some(n) =>
        if (clueOpt.nonEmpty) {
          (Option(n * -1), Option(0))
        } else {
          val n2: Int = if (-100 < n && n < 100) {
            n + romanCalendar
          } else if (0 <= n) {
            romanCalendar = n / 100 * 100
            n
          } else {
            0
          }
          (Option(n2), Option(0))
        }
      case None =>
        (None, None)
    }
  }

  private def parseTime(numOpt: Option[Int], clue1: String, clue2Opt: StringOption): BeginCountOptions = {
    clue1 match {
      case "千年紀" =>
        parse千年紀(numOpt, clue2Opt)
      case "世紀" =>
        parse世紀(numOpt, clue2Opt)
      case "年代" =>
        parse年代(numOpt, clue2Opt)
      case "年" =>
        parse年(numOpt, clue2Opt)
      case "現代" =>
        (Option(1918), None)
      case "近代" =>
        (Option(1700), Option(1918 - 1700))
      case "近現代" =>
        (Option(1700), None)
      case "近世" =>
        (Option(1453), Option(1700 - 1453))
      case "中世" =>
        (Option(476), Option(1453 - 476))
      case "古代" =>
        (Option(-2600), Option(476 + 2600))
      case _ =>
        (None, None)
    }
  }

  private def reviseFirst(beginCountOpt: BeginCountOptions, clue: String): BeginEndOptions = {
    val (beginOpt, countOpt) = beginCountOpt
    clue match {
      case "以前" | "まで" => beginOpt match {
        case Some(b) =>
          return (None, Option(b + countOpt.getOrElse(0)))
        case None =>
      }
      case "以後" | "から" =>
        return (beginOpt, None)
      case "前半" | "前期" => beginOpt match {
        case Some(b) =>
          return (beginOpt, Option(b + (countOpt.getOrElse(0) * 0.5).toInt))
        case None =>
      }
      case "後半" | "後期" => beginOpt match {
        case Some(b) =>
          val count = countOpt.getOrElse(0)
          return (Option(b + (count * 0.5).toInt), Option(b + count))
        case None =>
      }
      case "初頭" | "初期" | "初め" | "はじめ" => beginOpt match {
        case Some(b) =>
          return (beginOpt, Option(b + (countOpt.getOrElse(0) * 0.3).toInt))
        case None =>
      }
      case "末" | "末期" | "終り" | "終わり" | "おわり" => beginOpt match {
        case Some(b) =>
          val count = countOpt.getOrElse(0)
          return (Option(b + (count * 0.7).toInt), Option(b + count))
        case None =>
      }
      case _ => beginOpt match {
        case Some(b) =>
          return (beginOpt, Option(b + countOpt.getOrElse(0)))
        case None =>
      }
    }
    (None, None)
  }

  private def reviseSecond(beginEndOpt: BeginEndOptions, clue: String): BeginEndOptions = {
    val (beginOpt, endOpt) = beginEndOpt
    clue match {
      case "ころ" | "ごろ" | "頃" | "辺り" | "あたり" | "らへん" | "前後" =>
        beginOpt match {
          case Some(b) =>
            endOpt match {
              case Some(e) =>
                val c = 0 //if (b == e) {5} else {((e - b) * 0.2).toInt}
                return (Option(b - c), Option(e + c))
              case None =>
            }
          case None =>
        }
      case _ =>
    }
    (beginOpt, endOpt)
  }

  private def reviseThird(beginEndOpt: BeginEndOptions, clue: String): BeginEndOptions = {
    val (beginOpt, endOpt) = beginEndOpt
    clue match {
      case "以前" | "まで" =>
        (None, endOpt)
      case "以後" | "から" =>
        (beginOpt, None)
      case _ =>
        (beginOpt, endOpt)
    }
  }

  private def isTime(m: Match): Boolean = {
    StringOption(m.group(0)).nonEmpty &&
    !Set("年間", "年分", "年にわたる", "年に渡る").contains(m.group(3)) &&
    (
      (
        StringOption(m.group(2)).nonEmpty &&
        StringOption(m.group(3)).nonEmpty
      ) ||
      Set("現代", "近代", "近現代", "中世", "古代").contains(m.group(3))
    )
  }

  private def toIntOption(numberCharactersOpt: StringOption): Option[Int] = {
    numberCharactersOpt match {
      case StringSome(n) =>
        try {
          Option(n.toInt)
        } catch {
          case e: NumberFormatException =>
            e.printStackTrace()
            None
        }
      case StringNone =>
        None
    }
  }
}
