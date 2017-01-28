package exam.national_center_test.xml

import java.io.{BufferedReader, File}
import java.nio.file.Paths

import exam.national_center_test.xml.choice.ChoiceParser
import text.normalizer.Normalizer
import text.{StringNone, StringOption, StringSome}
import util.{Config, Logger, StaticValue}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Node, NodeSeq, XML}

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
class NationalCenterTestParser {
  private val availableYears: Seq[Int] = Seq(
    1997,
    2001,
    2003,
    2005,
    2007,
    2009
  )

  private def isAvailable(year: Int): Boolean = {
    availableYears contains year
  }

  def parse(year: Int): Option[NationalCenterTestQuestion] = {
    if (isAvailable(year)) {
      Option(extractQuestion(year))
    } else {
      None
    }
  }

  private def extractQuestion(year: Int): NationalCenterTestQuestion = {
    new NationalCenterTestQuestion(year, extractBigQuestions(centerTestXml(year)))
  }

  private def extractBigQuestions(exam: NodeSeq): Seq[BigQuestion] = {
    for (bigQuestion <- exam \ "question") yield {
      new BigQuestion(
        (bigQuestion \ "instruction").text.trim,
        extractMiddleQuestions(bigQuestion)
      )
    }
  }

  private def extractMiddleQuestions(bigQuestion: NodeSeq): Seq[MiddleQuestion] = {
    val middleQuestionBuffer: ListBuffer[MiddleQuestion] = ListBuffer.empty[MiddleQuestion]
    val smallQuestionBuffer: ListBuffer[SmallQuestion] = ListBuffer.empty[SmallQuestion]
    var context: NodeSeq = Nil
    var isFirstData: Boolean = true

    def append(): Unit = {
      middleQuestionBuffer += new MiddleQuestion(
        context,
        smallQuestionBuffer.result
      )
      smallQuestionBuffer.clear
    }

    def underLinePart(smallQuestion: NodeSeq): StringOption = {
      Option(smallQuestion \ "instruction" \ "ref" \ "@target") match {
        case Some(target) =>
          val targetId: String = target.text.trim
          val targetUText: NodeSeq =
            (context \\ "uText") filter(
              uText =>
                (uText  \ "@id") exists (_.text == targetId))
          var ret: StringOption = StringNone
          targetUText foreach {
            uText =>
              uText.child foreach {
                case u if u.label != "label" =>
                  ret = Normalizer.normalize(StringOption(u.text.trim))
                case otherwise =>
              }
          }
          ret
        case None =>
          StringNone
      }
    }

    def sentences(smallQuestion: NodeSeq): Option[Map[String, String]] = {
      val map: mutable.Map[String, String] = mutable.Map.empty[String, String]
      var key: String = ""
      (smallQuestion \ "data" \ "lText") foreach {
        lText =>
          lText.child foreach {
            l =>
              if (l.label == "label") {
                val label: NodeSeq = l
                key = Normalizer.normalize(StringOption(label.text.trim)).getOrElse("")
              } else if (l.label == "br") {
                //no process
              } else {
                val text: StringOption = StringOption(l.text.trim)
                if (text.nonEmpty) {
                  Normalizer.normalize(text) match {
                    case StringSome(nText) =>
                      map(key) = nText
                    case StringNone =>
                  }
                }
              }
          }
      }
      if (map.isEmpty) {
        None
      } else {
        Option(map.toMap)
      }
    }

    def explanation(smallQuestion: NodeSeq, middleQuestion: NodeSeq): Option[NodeSeq] = {
      val dataFromSmallQuestion: NodeSeq = smallQuestion \ "data"
      if (dataFromSmallQuestion.isEmpty || (dataFromSmallQuestion \ "blank").isEmpty) {
        val dataFromMiddleQuestion: NodeSeq = middleQuestion \ "data"
        if (dataFromMiddleQuestion.isEmpty || (dataFromMiddleQuestion \ "blank").isEmpty) {
          None
        } else {
          Option(dataFromMiddleQuestion.head.child)
        }
      } else {
        Option(dataFromSmallQuestion.head.child)
      }
    }

    bigQuestion foreach {
      middleQuestion =>
        middleQuestion.child foreach {
          middleQuestionElement =>
            if (middleQuestionElement.label == "data") {
              if (isFirstData) {
                isFirstData = false
              } else {
                append()
              }
              val data: Node = middleQuestionElement
              context = data.child filter(
                element =>
                  element.label != "br")
            } else if (middleQuestionElement.label == "question") {
              val smallQuestion: NodeSeq = middleQuestionElement
              smallQuestionBuffer += new SmallQuestion(
                try {
                  (smallQuestion \ "@anscol").text.tail.toInt
                } catch {
                  case e: NumberFormatException => -1
                },
                (smallQuestion \ "instruction").head.child,
                underLinePart(smallQuestion),
                sentences(smallQuestion),
                explanation(smallQuestion, middleQuestion),
                ChoiceParser.parse(smallQuestion \ "choices")
              )
            } else if (middleQuestionElement.label == "instruction") {
              //no process
            }
        }
    }
    append()
    middleQuestionBuffer.result
  }


  private def centerTestXml(year: Int): NodeSeq = {
    centerTestFileName(year) match {
      case Some(f) =>
        centerTestXml(
          centerTestReader(
            f
          )
        )
      case None => NodeSeq.Empty
    }
  }

  private def centerTestXml(reader: Option[BufferedReader]): NodeSeq = {
    reader match {
      case Some(r) => XML load r
      case None => NodeSeq.Empty
    }
  }

  private def centerTestReader(fileName: String): Option[BufferedReader] = {
    val path = Paths.get(
      Config.centerTestJaXmlDir,
      File.separator,
      fileName
    )
    if (path.toFile.canRead) {
      Some(StaticValue.reader(path))
    } else {
      try {
        Logger.logWriter.println(
          path.toAbsolutePath.toString concat " is not found."
        )
      } catch {
        case e: Exception =>
          e.printStackTrace(Logger.logWriter)
      }
      None
    }
  }

  private def centerTestFileName(year: Int): Option[String] = {
    validateYear {
      Option(s"Center-$year--Main-SekaishiB.xml")
    }(year)
  }

  private def validateYear(proc : => Option[String])(year: Int): Option[String] = {
    if (isAvailable(year)) {
      proc
    } else {
      None
    }
  }
}
