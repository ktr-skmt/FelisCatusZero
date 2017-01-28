package uima.cc

import java.io.{File, IOException, PrintWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Paths}

import jeqa.types._
import org.apache.uima.cas.{CAS, CASException, FSIterator}
import org.apache.uima.collection.{CasConsumer_ImplBase, CollectionException}
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceProcessException
import util.Config
import util.StringUtils._
import util.uima.FSListUtils._
import util.uima.JCasUtils

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node, PrettyPrinter, XML}

/**
  * <p>論述文をファイルに書き出すプログラム</p>
  * @author K.Sakamoto
  *         Created on 15/10/30
  */
class EssayWriter extends CasConsumer_ImplBase {
  override def initialize(): Unit = {
    println(">> Essay Writer Initializing")
    super.initialize()
  }

  @throws[ResourceProcessException]
  override def processCas(aCAS: CAS): Unit = {
    println(">> Essay Writer Processing")

    var aJCasOption: Option[JCas] = None
    try {
      aJCasOption = Option(aCAS.getJCas)
    } catch {
      case e: CASException =>
        throw new CollectionException(e)
    }
    if (aJCasOption.isEmpty) {
      return
    }
    val aJCas: JCas = aJCasOption.get
    JCasUtils.setAJCasOpt(Option(aJCas))

    val itExam: FSIterator[Nothing] = aJCas.getAnnotationIndex(Exam.`type`).iterator(true)
    while (itExam.hasNext) {
      val exam: Exam = itExam.next
      val questionSet: FSArray = exam.getQuestionSet
      val writerQuestionAnswer: mutable.Map[String, ListBuffer[Elem]] = mutable.Map.empty[String, ListBuffer[Elem]]
      for (i <- 0 until questionSet.size) {
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val answerSet: Seq[Answer] = question.getAnswerSet.toSeq.asInstanceOf[Seq[Answer]]
        val writerAnswerMap: mutable.Map[String, String] = mutable.Map.empty[String, String]
        val writerGoldStandardListBuffer: ListBuffer[(String, String)] = ListBuffer.empty[(String, String)]
        answerSet foreach {
          answer: Answer =>
            if (answer.getIsGoldStandard) {
              val document: Document = answer.getDocument
              writerGoldStandardListBuffer += ((answer.getWriter, document.getText))
            } else {
              val document: Document = answer.getDocument
              writerAnswerMap(answer.getWriter) = document.getText
            }
        }
        val writerGoldStandardList: Seq[(String, String, Boolean)] = writerGoldStandardListBuffer.result map (element => (element._1, element._2, true))
        val xml: Elem = XML.loadString(question.getXml)
        writerAnswerMap foreach {
          case (writer, text) =>
            if (!(writerQuestionAnswer contains writer)) {
              writerQuestionAnswer(writer) = ListBuffer.empty[Elem]
            }
            writerQuestionAnswer(writer) += getAnswers(writerGoldStandardList ++ Seq[(String, String, Boolean)]((writer, text, false)), xml)
          case _ =>
            // Do nothing
        }
      }
      val fileNameFormat: String = s"""${
        val label: String = exam.getLabel
        if (label.endsWith(".xml")) {
          label.dropRight(4)
        } else {
          label
        }
      }_%s.xml"""
      writerQuestionAnswer foreach {
        case (writer, answerSectionList) =>
          val printer: PrettyPrinter = new PrettyPrinter(10000, 2)
          val answerSheet: Node = <answer_sheet ver="0.1">{answerSectionList.result}</answer_sheet>
          var printWriterOpt: Option[PrintWriter] = None
          try {
            val resultDir: File = Paths.get("out", "result").toAbsolutePath.toFile
            if (!resultDir.canRead) {
              resultDir.mkdir
            } else if (!resultDir.isDirectory) {
              resultDir.delete
              resultDir.mkdir
            }
            val outputDir: File = Paths.get(resultDir.toString, Config.timestamp).toAbsolutePath.toFile
            if (!outputDir.canRead) {
              outputDir.mkdir
            } else if (!outputDir.isDirectory) {
              outputDir.delete
              outputDir.mkdir
            }
            printWriterOpt = Option(new PrintWriter(
              Files.newBufferedWriter(
                Paths.get(outputDir.toString, fileNameFormat.format(writer)),
                StandardCharsets.UTF_8)))
            if (printWriterOpt.nonEmpty) {
              val printWriter: PrintWriter = printWriterOpt.get
              printWriter.println("""<?xml version="1.0" encoding="UTF-8"?>""")
              printWriter.print(printer.format(answerSheet))
            }
          } catch {
            case e: IOException =>
              throw e
          } finally {
            try {
              if (printWriterOpt.nonEmpty) {
                printWriterOpt.get.close()
              }
            } catch {
              case e: IOException =>
                throw e
            }
          }
        case _ =>
          // Do nothing
      }
    }
  }

  private def getAnswers(answers: Seq[(String, String, Boolean)], answerSection: Elem): Elem = {
    XML.loadString(answerSection.toString.replaceAllLiteratim("\n", "").
      replaceAll("<expression[ >][^<]*</expression>", "").
      replaceFirst(
        """<expression_set>[^<]*</expression_set>""",
        s"""<expression_set>${getAnswers(answers)}</expression_set>"""
      ))
  }

  private def getAnswers(answers: Seq[(String, String, Boolean)]): String = {
    val builder: StringBuilder = new StringBuilder()
    answers foreach {
      case (writer, answer, isGoldStandard) =>
        builder.append(getExpression(writer, answer, isGoldStandard))
      case _ =>
        // Do nothing
    }
    builder.result
  }

  private def getExpression(writer: String, answer: String, isGoldStandard: Boolean): String = {
    s"""<expression is_gold_standard="$isGoldStandard" writer="$writer">$answer</expression>"""
  }

  @throws[ResourceProcessException]
  override def destroy(): Unit = {
    //Do nothing
  }
}
