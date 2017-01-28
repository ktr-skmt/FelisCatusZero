package uima.cc

import java.awt.Desktop
import java.io.{BufferedReader, File, IOException, PrintWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

import converter.HtmlTextConverter
import evaluation.{MacroAveraging, MicroAveraging, RougeN, SummaryStatistics}
import jeqa.types._
import org.apache.uima.cas.{CAS, CASException, FSIterator}
import org.apache.uima.collection.{CasConsumer_ImplBase, CollectionException}
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceProcessException
import org.jsoup.nodes.Element
import uima.ae.DocumentAnnotator
import util.Config
import util.uima.FSListUtils._
import util.uima.JCasUtils
import util.uima.StringListUtils._

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/**
  * <p>論述評価をファイルに書き出すプログラム</p>
  * @author K.Sakamoto
  *         Created on 15/10/30
  */
class EssayEvaluator extends CasConsumer_ImplBase with DocumentAnnotator {
  private val mHistoryId: String = "evaluation_result_history"
  private val mTemplateFormat: String =
    s"""<!DOCTYPE html>
        |<html>
        |<head>
        |<meta charset="utf-8">
        |<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        |<title>Evaluation Results</title>
        |<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        |</head>
        |<body>
        |<div class="container docs-content">
        |%s
        |</div>
        |<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>
        |<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        |<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>
        |</body>
        |</html>""".stripMargin


  override def initialize(): Unit = {
    println(">> Essay Evaluator Initializing")
    super.initialize()
  }

  @throws[ResourceProcessException]
  override def processCas(aCAS: CAS): Unit = {
    println(">> Essay Evaluator Processing")

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

    val rouge1: RougeN = new RougeN(1)
    val rouge2: RougeN = new RougeN(2)

    val rouge1Map: mutable.Map[String, ArrayBuffer[Seq[Double]]] = mutable.Map.empty[String, ArrayBuffer[Seq[Double]]]
    val rouge2Map: mutable.Map[String, ArrayBuffer[Seq[Double]]] = mutable.Map.empty[String, ArrayBuffer[Seq[Double]]]

    val particularExamMap: mutable.LinkedHashMap[String, Map[String, (Boolean, String, Seq[(String, Double, Double)])]] =
      mutable.LinkedHashMap.empty[String, Map[String, (Boolean, String, Seq[(String, Double, Double)])]]

    val examBuffer: ListBuffer[Exam] = ListBuffer.empty[Exam]
    @SuppressWarnings(Array[String]("rawtypes"))
    val itExam: FSIterator[Nothing] = aJCas.getAnnotationIndex(Exam.`type`).iterator(true)
    while (itExam.hasNext) {
      val exam: Exam = itExam.next
      examBuffer += exam
    }

    examBuffer.result.sortWith((a, b) => a.getLabel < b.getLabel) foreach {
      exam: Exam =>

      val rouge1Map4Exam: mutable.Map[String, ListBuffer[Double]] = mutable.Map.empty[String, ListBuffer[Double]]
      val rouge2Map4Exam: mutable.Map[String, ListBuffer[Double]] = mutable.Map.empty[String, ListBuffer[Double]]

      val questionSet: FSArray = exam.getQuestionSet

      // for checking no answer
      val writerList: ListBuffer[String] = ListBuffer.empty[String]
      for (i <- 0 until questionSet.size) {
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val answerSet: Seq[Answer] = question.getAnswerSet.toSeq.asInstanceOf[Seq[Answer]]
        answerSet foreach {
          case answer: Answer if !answer.getIsGoldStandard && !writerList.contains(answer.getWriter) =>
            writerList += answer.getWriter
          case _ =>
            // Do nothing
        }
      }

      val particularQuestionMap: mutable.LinkedHashMap[String, (Boolean, String, Seq[(String, Double, Double)])] =
        mutable.LinkedHashMap.empty[String, (Boolean, String, Seq[(String, Double, Double)])]
      for (i <- 0 until questionSet.size) {
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val answerSet: Seq[Answer] = question.getAnswerSet.toSeq.asInstanceOf[Seq[Answer]]
        val writerAnswerMap: mutable.Map[String, Seq[Sentence]] = mutable.Map.empty[String, Seq[Sentence]]
        val goldStandardSet: ListBuffer[Seq[Sentence]] = ListBuffer.empty[Seq[Sentence]]
        answerSet foreach {
          answer: Answer =>
            val document: Document = answer.getDocument
            if (answer.getIsGoldStandard) {
              annotate(aJCas, document, Nil)
              goldStandardSet += document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]
            } else {
              writerAnswerMap(answer.getWriter) = document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]

            }
        }
        // for no answer
        writerList.result foreach {
          writer: String =>
            if (!writerAnswerMap.contains(writer)) {
              writerAnswerMap(writer) = Nil
            }
        }
        val goldStandardTermList: Seq[Seq[Seq[String]]] = goldStandardSet.result map {
          goldStandard: Seq[Sentence] =>
            goldStandard map {
              sentence: Sentence =>
                sentence.getContentWordList.toSeq
            }
        }

        val particularWriterBuffer: ListBuffer[(String, Double, Double)] = ListBuffer.empty[(String, Double, Double)]
        writerAnswerMap foreach {
          case (writer, sentenceList) =>
            val systemTermList: Seq[Seq[String]] = sentenceList map {sentence: Sentence => sentence.getContentWordList.toSeq}
            if (!(rouge1Map4Exam contains writer)) {
              rouge1Map4Exam(writer) = ListBuffer.empty[Double]
            }
            val rouge1Score: Double = rouge1.evaluate(systemTermList, goldStandardTermList)
            rouge1Map4Exam(writer) += rouge1Score

            if (!(rouge2Map4Exam contains writer)) {
              rouge2Map4Exam(writer) = ListBuffer.empty[Double]
            }
            val rouge2Score: Double = rouge2.evaluate(systemTermList, goldStandardTermList)
            rouge2Map4Exam(writer) += rouge2Score

            particularWriterBuffer += ((writer, rouge1Score, rouge2Score))
          case _ =>
            // Do nothing
        }
        particularQuestionMap(question.getLabel) = (
          question.getKeywordSet.toSeq.nonEmpty,
          s"""${question.getBeginCharacterLimit.toString} - ${question.getEndCharacterLimit.toString}""",
          particularWriterBuffer.result
        )
      }
      particularExamMap(exam.getLabel) = particularQuestionMap.toMap

      rouge1Map4Exam foreach {
        case (writer, scores) =>
          if (!(rouge1Map contains writer)) {
            rouge1Map(writer) = ArrayBuffer.empty[Seq[Double]]
          }
          rouge1Map(writer) += scores.result
      }

      rouge2Map4Exam foreach {
        case (writer, scores) =>
          if (!(rouge2Map contains writer)) {
            rouge2Map(writer) = ArrayBuffer.empty[Seq[Double]]
          }
          rouge2Map(writer) += scores.result
      }
    }

    val rouge1Cell: String = """<th rowspan="2" scope="row">ROUGE-1 F1 Score by Content Word</th>"""
    val rouge2Cell: String = """<th rowspan="2" scope="row">ROUGE-2 F1 Score by Content Word</th>"""

    val tableSummaryBuilder: StringBuilder = new StringBuilder()
    val tableParticularsBuilder: StringBuilder = new StringBuilder()
    val writers: Seq[String] = rouge1Map.keySet.toSeq.sorted
    writers foreach {
      writer: String =>
        val writerCell: String = s"""<th rowspan="4" scope="row">$writer</th>"""

        val rouge1: Array[Seq[Double]] = rouge1Map(writer).toArray[Seq[Double]]
        val rouge1MicroAveragingSummaryStatistics: SummaryStatistics = MicroAveraging.summaryStatistics(rouge1)
        val rouge1MicroAveragingCells: String = s"""<th scope="row">Micro</th>$rouge1MicroAveragingSummaryStatistics"""

        val rouge1MacroAveragingSummaryStatistics: SummaryStatistics = MacroAveraging.summaryStatistics(rouge1)
        val rouge1MacroAveragingCells: String = s"""<th scope="row">Macro</th>$rouge1MacroAveragingSummaryStatistics"""

        val rouge2: Array[Seq[Double]] = rouge2Map(writer).toArray[Seq[Double]]
        val rouge2MicroAveragingSummaryStatistics: SummaryStatistics = MicroAveraging.summaryStatistics(rouge2)
        val rouge2MicroAveragingCells: String = s"""<th scope="row">Micro</th>$rouge2MicroAveragingSummaryStatistics"""

        val rouge2MacroAveragingSummaryStatistics: SummaryStatistics = MacroAveraging.summaryStatistics(rouge2)
        val rouge2MacroAveragingCells: String = s"""<th scope="row">Macro</th>$rouge2MacroAveragingSummaryStatistics"""

        tableSummaryBuilder.
          append(s"""<tr>$writerCell$rouge1Cell$rouge1MicroAveragingCells</tr>""").append('\n').
          append(s"""<tr>$rouge1MacroAveragingCells</tr>""").append('\n').
          append(s"""<tr>$rouge2Cell$rouge2MicroAveragingCells</tr>""").append('\n').
          append(s"""<tr>$rouge2MacroAveragingCells</tr>""").append('\n')
    }

    val datasetNumOfLinesMap: mutable.Map[String, Int] = mutable.Map.empty[String, Int]
    particularExamMap.toMap foreach {
      case (datasetLabel, question) =>
        var counter: Int = 0
        question foreach {
          case (_, scores) =>
            counter += scores._3.size
        }
        datasetNumOfLinesMap(datasetLabel) = counter
      case _ =>
        // Do nothing
    }

    particularExamMap.toMap foreach {
      case (datasetLabel, question) =>
        var isFirst4Dataset: Boolean = true
        val datasetLabelCell: String = s"""<th rowspan="${datasetNumOfLinesMap(datasetLabel)}" scope="row"><a href="./${datasetLabel.dropRight(4)}_${Config.systemName}.xml" target="_blank">$datasetLabel</a></th>"""
        question foreach {
          case (questionLabel, scores) =>
            var isFirst4Question: Boolean = true
            val size: Int = scores._3.size
            val questionLabelCell: String = s"""<th rowspan="$size" scope="row">$questionLabel</th><td rowspan="$size">${if (scores._1) "Yes" else "No"}</td><td rowspan="$size">${scores._2}</td>"""
            scores._3 foreach {
              case (writer, r1, r2) =>
                val scoreCells: String = f"""<td>$writer</td><td>$r1%.5f</td><td>$r2%.5f</td>"""
                tableParticularsBuilder.append(s"""<tr>${
                  if (isFirst4Dataset) {
                    isFirst4Dataset = false
                    datasetLabelCell
                  } else ""
                }${
                  if (isFirst4Question) {
                    isFirst4Question = false
                    questionLabelCell
                  } else ""
                }$scoreCells</tr>""").append('\n')
              case _ =>
                // Do nothing
            }
          case _ =>
            // Do nothing
        }
      case _ =>
        // Do nothing
    }

    val partOfEvaluationResult: String =
      s"""<h1>Evaluation Results</h1>
         |<p>${Config.timestamp}</p>
         |<p><a href="../index.html">HISTORY</a></p>
         |<h2>Summary</h2>
         |<table class="table table-sm table-bordered">
         |<thead class="thead-inverse"><tr><th>WRITER</th><th>ROUGE</th><th>AVERAGING</th><th># OF DATASETs</th><th># OF Qs</th><th>MEAN</th><th>MAX</th><th>MEDIAN</th><th>MIN</th><th>VARIANCE</th><th>STANDARD DEVIATION</th></tr></thead>
         |<tbody>$tableSummaryBuilder</tbody>
         |</table>
         |<h2>Particulars</h2>
         |<table class="table table-sm table-bordered">
         |<thead class="thead-inverse"><tr><th>DATASET</th><th>QUESTION LABEL</th><th>HAS KEYWORDS?</th><th>CHAR LIMIT</th><th>WRITER</th><th>ROUGE-1 F1 Score by Content Word</th><th>ROUGE-2 F1 Score by Content Word</th></tr></thead>
         |<tbody>$tableParticularsBuilder</tbody>
         |</table>""".stripMargin
    println(partOfEvaluationResult.replaceAll("<[^>]+>", " "))
    val evaluationResult: String = mTemplateFormat.format(partOfEvaluationResult)

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
    val indexFile: Path = Paths.get(resultDir.toString, "index.html")
    val evaluationResultPath: Path = Paths.get(outputDir.toString, "evaluation_result.html")
    var printWriterOpt: Option[PrintWriter] = None
    var reader4InputFileOpt: Option[BufferedReader] = None
    var printWriter4IndexFileOpt: Option[PrintWriter] = None
    try {
      printWriterOpt = Option(new PrintWriter(
        Files.newBufferedWriter(evaluationResultPath, StandardCharsets.UTF_8)))
      if (printWriterOpt.nonEmpty) {
        printWriterOpt.get.print(evaluationResult)
      }
      val htmlBuilder: StringBuilder = new StringBuilder()
      val indexHtmlFile: File = indexFile.toFile
      if (indexHtmlFile.canRead && indexHtmlFile.isFile) {
        reader4InputFileOpt = Option(Files.newBufferedReader(indexFile, StandardCharsets.UTF_8))
        if (reader4InputFileOpt.nonEmpty) {
          val reader: BufferedReader = reader4InputFileOpt.get
          val lines: java.util.Iterator[String] = reader.lines.iterator
          while (lines.hasNext) {
            val line: String = lines.next
            htmlBuilder.append(line)
          }
        }
      }
      printWriter4IndexFileOpt = Option(new PrintWriter(
        Files.newBufferedWriter(indexFile, StandardCharsets.UTF_8)))
      if (printWriter4IndexFileOpt.nonEmpty) {
        val printWriter4IndexFile: PrintWriter = printWriter4IndexFileOpt.get
        HtmlTextConverter.toHtml(htmlBuilder.result) match {
          case Some(document) =>
            val pastResultBuilder: StringBuilder = new StringBuilder()
            val historyOpt: Option[Element] = Option(document.getElementById(mHistoryId))
            if (historyOpt.nonEmpty) {
              pastResultBuilder.append(historyOpt.get.html)
            }
            val partOfIndexHtml: String =
              s"""<h1>Evaluation Result History</h1>
                 |<ol id="$mHistoryId">
                 |<li><a href="./${Config.timestamp}/evaluation_result.html">${Config.timestamp}</a></li>
                 |$pastResultBuilder
                 |</ol>""".stripMargin
            println(partOfIndexHtml.replaceAll("<[^>]+>", " "))
            val indexHtml: String = mTemplateFormat.format(partOfIndexHtml)
            printWriter4IndexFile.print(indexHtml)
          case None =>
            // Do nothing
        }
      }
    } catch {
      case e: IOException =>
        throw e
    } finally {
      try {
        if (printWriterOpt.nonEmpty) {
          printWriterOpt.get.close()
        }
        if (reader4InputFileOpt.nonEmpty) {
          reader4InputFileOpt.get.close()
        }
        if (printWriter4IndexFileOpt.nonEmpty) {
          printWriter4IndexFileOpt.get.close()
        }
        if (Config.wantToBrowse && Desktop.isDesktopSupported) {
          try {
            Desktop.getDesktop.browse(evaluationResultPath.toFile.toURI)
          } catch {
            case e: IOException =>
              throw e
          }
        }
      } catch {
        case e: IOException =>
          throw e
      }
    }
  }

  @throws[ResourceProcessException]
  override def destroy(): Unit = {
    //Do nothing
  }
}
