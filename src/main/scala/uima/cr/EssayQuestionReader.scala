package uima.cr

import java.io.{File, IOException}
import java.nio.file.Paths
import java.util.Locale

import exam.essay.xml.Answer
import jeqa.types.{Document, Exam, Keyword, Question, Answer => UAnswer}
import org.apache.uima.cas.{CAS, CASException}
import org.apache.uima.collection.{CollectionException, CollectionReader_ImplBase}
import org.apache.uima.jcas.JCas
import org.apache.uima.resource.ResourceInitializationException
import org.apache.uima.util.Progress
import text.{StringNone, StringOption, StringSome}
import util.Config
import util.uima.JCasUtils
import util.uima.SeqUtils._

import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex
import scala.util.matching.Regex.Match
import scala.xml.{Node, NodeSeq, XML}

/**
 * <p>論述問題をファイルから読み込むプログラム</p>
 * @author K.Sakamoto
 *         Created on 15/10/30
 */
class EssayQuestionReader extends CollectionReader_ImplBase {
  private var mHasNextFlag: Boolean = false
  private var mEssayExamDir: Option[String] = None
  private val mEssayExamFiles: ListBuffer[File] = ListBuffer.empty[File]
  private var mId: Int = 0

  private var aJCasOption: Option[JCas] = None

  @throws[ResourceInitializationException]
  override def initialize(): Unit = {
    println(">> Essay Question Reader Initializing")
    super.initialize()
    mHasNextFlag = true
    mEssayExamDir = Config.essayExamDirOpt
    mEssayExamDir match {
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
  }

  @throws[IOException]
  @throws[CollectionException]
  override def getNext(aCAS: CAS): Unit = {
    println(">> Essay Question Reader Processing")

    try {
      aJCasOption = Option(aCAS.getJCas)
      if (aJCasOption.isEmpty | mEssayExamDir.isEmpty | mEssayExamFiles.isEmpty) {
        return
      }
    } catch {
      case e: CASException =>
        throw new CollectionException(e)
    }

    val aJCas: JCas = aJCasOption.get
    aJCas.setDocumentLanguage(Locale.JAPANESE.getLanguage)
    JCasUtils.setAJCasOpt(Option(aJCas))
    val baseDir: String = Paths.get(mEssayExamDir.get).toAbsolutePath.toString

    mEssayExamFiles.result foreach {
      file: File =>
        val exam: Exam = new Exam(aJCas)
        exam.addToIndexes()
        exam.setLabel(file.getName)
        exam.setDir(baseDir)
        exam.setLang(Locale.JAPANESE.getLanguage)

        val buffer: ListBuffer[Question] = ListBuffer.empty[Question]
        val xml: NodeSeq = XML.loadFile(file)
        xml \ "answer_section" foreach {
          answerSection: NodeSeq =>
            val id: Option[Int]           = extractId(answerSection)
            val instruction: StringOption = extractInstruction(answerSection)
            val label: StringOption       = extractLabel(answerSection)
            val answerSet: NodeSeq        = answerSection \ "answer_set" \ "answer"

            if (id.nonEmpty && instruction.nonEmpty) {
              buffer += getQuestion(
                aJCas          = aJCas,
                id             = id.get,
                label          = label.getOrElse(""),
                instruction    = instruction.get,
                characterLimit = extractCharacterLimit(answerSet),
                keywordSet     = extractKeywordSet(answerSection),
                answerSet      = extractAnswerSet(answerSet),
                xml            = answerSection)
            }
        }
        exam.setQuestionSet(buffer.result.toFSArray)
    }
    mHasNextFlag = false
  }

  private def getQuestion(aJCas: JCas,
                          id: Int,
                          label: String,
                          instruction: String,
                          characterLimit: Range,
                          keywordSet: Seq[String],
                          answerSet: Seq[Answer],
                          xml: NodeSeq): Question = {
    val question: Question = new Question(aJCas)
    question.addToIndexes()
    question.setBeginCharacterLimit(characterLimit.start)
    question.setEndCharacterLimit(characterLimit.end)
    question.setLabel(label)
    val document: Document = new Document(aJCas)
    document.addToIndexes()
    document.setId(id)
    document.setText(instruction)
    question.setDocument(document)
    val keywords: Seq[Keyword] = keywordSet map {
      k: String =>
        val keyword: Keyword = new Keyword(aJCas)
        keyword.addToIndexes()
        keyword.setIsMandatory(true)
        keyword.setText(k)
        keyword
    }
    question.setKeywordSet(keywords.toFSList)
    val answers: Seq[UAnswer] = answerSet map {
      a: Answer =>
        val answer: UAnswer = new UAnswer(aJCas)
        answer.addToIndexes()
        answer.setIsGoldStandard(a.isGoldStandard)
        if (a.writer.nonEmpty) {
          answer.setWriter(a.writer.get)
        }
        if (a.expression.nonEmpty) {
          val document: Document = new Document(aJCas)
          document.addToIndexes()
          document.setText(a.expression.get)
          answer.setDocument(document)
        }
        answer
    }
    question.setAnswerSet(answers.toFSList)
    question.setXml(xml.toString)

    question
  }

  private def extractId(answerSection: NodeSeq): Option[Int] = {
    /*
    try {
      Option((answerSection \ "@id").head.text.toInt)
    } catch {
      case e: Exception =>
        e.printStackTrace()
        None
    }
    */
    mId += 1
    Option(mId)
  }

  private def extractLabel(answerSection: NodeSeq): StringOption = {
    StringOption((answerSection \ "@label").head.text)
  }

  private def extractInstruction(answerSection: NodeSeq): StringOption = {
    val builder: StringBuilder = new StringBuilder()
    answerSection \ "instruction" \ "p" foreach {
      p: NodeSeq =>
        StringOption(p.text.trim) match {
          case StringSome(line) =>
            builder.
              append(line).
              append('\n')
          case StringNone =>
          //Do nothing
        }
    }
    StringOption(builder.result)
  }

  private def extractKeywordSet(answerSection: NodeSeq): Seq[String] = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    answerSection \ "keyword_set" \ "keyword" foreach {
      keyword: NodeSeq =>
        StringOption(keyword.text.trim) match {
          case StringSome(k) =>
            buffer += k
          case StringNone =>
          //Do nothing
        }
    }
    buffer.result
  }

  private val characterLimitBeginRegex: Regex = """([0-9]+)字以上""".r
  private val characterLimitEndRegex:   Regex = """([0-9]+)字以[下内]""".r

  //TODO: これを使用しなくて済むように、問題の字数制限を全て「M字以上N字以下」に直す。
  private val characterLimitAboutRegex: Regex = """([0-9]+)字(?:程度|前後)""".r

  private def extractCharacterLimit(answerSet: NodeSeq): Range = {
    val answer: Node = answerSet.head
    StringOption((answer \ "@length_limit").text.trim) match {
      case StringSome(lengthLimit) =>
        val begin: Int = getBegin(lengthLimit)
        val end:   Int = getEnd(lengthLimit)
        //begin to end

        //TODO: これを使用しなくて済むように、問題の字数制限を全て「M字以上N字以下」に直す。
        aboutNumberRange(lengthLimit, begin, end)

      case StringNone => 0 to 0
    }
  }

  private def getBegin(lengthLimit: String): Int = {
    getNumber(lengthLimit, characterLimitBeginRegex, 0)
  }

  private def getEnd(lengthLimit: String): Int = {
    getNumber(lengthLimit, characterLimitEndRegex, Int.MaxValue)
  }

  private def getNumber(lengthLimit: String, regex: Regex, defaultValue: Int): Int = {
    regex.findFirstMatchIn(lengthLimit) match {
      case Some(m) =>
        try {
          m.group(1).toInt
        } catch {
          case e: NumberFormatException =>
            e.printStackTrace()
            defaultValue
        }
      case None =>
        defaultValue
    }
  }

  private def aboutNumberRange(lengthLimit: String, begin: Int, end: Int): Range = {
    if ((begin == 0) && (end == Int.MaxValue)) {
      characterLimitAboutRegex.findFirstMatchIn(lengthLimit) match {
        case Some(aboutNumberMatch) =>
          aboutNumberRange(aboutNumberMatch, begin, end)
        case None =>
          if (lengthLimit == "簡潔" || lengthLimit == "短文") {
            0 to 100
          } else {
            begin to end
          }
      }
    } else {
      begin to end
    }
  }

  private def aboutNumberRange(aboutNumberMatch: Match, defaultBegin: Int, defaultEnd: Int): Range = {
    try {
      val aboutNumber: Int = aboutNumberMatch.group(1).toInt
      if (aboutNumber <= 30) {
        0 to (aboutNumber * 2)
      } else {
        (aboutNumber - 30) to (aboutNumber + 30)
      }
    } catch {
      case e: NumberFormatException =>
        e.printStackTrace()
        defaultBegin to defaultEnd
    }
  }

  private def extractAnswerSet(answerSet: NodeSeq): Seq[Answer] = {
    val expressionSet: NodeSeq = answerSet \ "expression_set" \ "expression"
    expressionSet map {
      expression: NodeSeq =>
        new Answer(
          isGoldStandard = java.lang.Boolean.parseBoolean((expression \ "@is_gold_standard").text.trim),
          writer         = StringOption((expression \ "@writer").text.trim),
          expression     = StringOption(expression.text.trim)
        )
    }
  }

  override def getProgress: Array[Progress] = {
    Array.empty[Progress]
  }

  @throws[IOException]
  @throws[CollectionException]
  override def hasNext: Boolean = {
    mHasNextFlag
  }

  @throws[IOException]
  override def close(): Unit = {
    //Do nothing
  }
}