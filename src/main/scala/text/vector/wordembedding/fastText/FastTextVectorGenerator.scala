package text.vector.wordembedding.fastText

import java.io.{File, IOException, PrintWriter}
import java.nio.charset.StandardCharsets
import java.nio.file.{Files, Path, Paths}

import ir.fulltext.indri.{IndriResult, TrecText}
import text.analyzer.JapaneseSentenceSplitter
import text.analyzer.mor.mecab.UnidicMecab
import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.io.Source
import scala.sys.process.Process
import scala.xml.{NodeSeq, XML}

/**
  * <pre>
  * Created on 2016/12/24.
  * </pre>
  *
  * @author K.Sakamoto
  */
object FastTextVectorGenerator {
  @throws[Exception]
  def main(args: Array[String]): Unit = {
    println(">> fastText Word Vector Generating")
    val indriResultMap: mutable.Map[String, IndriResult] = mutable.Map.empty[String, IndriResult]
    Config.trecTextFormatData foreach {
      path: String =>
        println(path.toString)
        Paths.get(path).toFile.listFiles foreach {
          file: File =>
            indriResultMap ++=
              TrecText.toIndriResultMap(Source.fromFile(file).getLines, StringNone, Nil, indriResultMap)
        }
    }

    val builder: StringBuilder = new StringBuilder()
    indriResultMap foreach {
      case (_, indriResult) =>
        indriResult.text match {
          case StringSome(t) =>
            builder.append(t)
          case StringNone =>
            // Do nothing
        }
    }

    //
    val mEssayExamFiles = ListBuffer.empty[File]
    Config.essayExamDirOpt match {
      case Some(essayExamDir) =>
        val essayExamDirFile: File = new File(essayExamDir)
        if (essayExamDirFile.canRead && essayExamDirFile.isDirectory) {
          essayExamDirFile.listFiles foreach {
            case file: File if file.canRead && file.isFile && file.getName.endsWith(".xml") =>
              mEssayExamFiles += file
            case _ =>
            // Do nothing
          }
        }
      case None =>
      // Do nothing
    }
    mEssayExamFiles.result foreach {
      file: File =>
        println(file.getName)
        val xml: NodeSeq = XML.loadFile(file)
        xml \ "answer_section" foreach {
          answerSection: NodeSeq =>
            answerSection \ "instruction" \ "p" foreach {
              p: NodeSeq =>
                //println(p.text.trim)
                builder.append(p.text.trim)
            }
            builder.append ({
              for (keyword <- answerSection \ "keyword_set" \ "keyword") yield {
                keyword.text.trim
              }
            }.mkString(" ", " ", " "))
            val answerSet: NodeSeq = answerSection \ "answer_set" \ "answer"
            answerSet foreach {
              answer: NodeSeq =>
                answer \ "expression_set"  \ "expression" foreach {
                  expression: NodeSeq =>
                    //println(expression.text.trim)
                    builder.append(expression.text.trim)
                }
            }
        }
    }

    val dataPath: Path = Paths.get(Config.fastTextResource)
    val writer: PrintWriter = new PrintWriter(Files.newBufferedWriter(dataPath, StandardCharsets.UTF_8))
    try {
      for (sentenceTmp <- JapaneseSentenceSplitter.split(StringOption(builder.result))) {
        UnidicMecab.extractWords(StringOption(sentenceTmp.text)) foreach {
          word: String =>
            if (word != " " && word != "　") {
              writer.print(word.concat(" "))
            }
        }
      }
    } catch {
      case e: IOException =>
        e.printStackTrace()
    } finally {
      if (Option(writer).nonEmpty) {
        try {
          writer.close()
        } catch {
          case e: IOException =>
            e.printStackTrace()
        }
      }
    }

    val modelPath: Path = Paths.get(Config.fastTextModel)
    Process(Seq[String](
      "fasttext",
      "skipgram",
      "-minCount", 1.toString,
      "-input", dataPath.toAbsolutePath.toString,
      "-output", modelPath.toAbsolutePath.toString
    )).run
  }
}
