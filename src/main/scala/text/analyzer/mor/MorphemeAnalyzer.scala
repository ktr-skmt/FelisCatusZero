package text.analyzer.mor


import java.nio.charset.{CodingErrorAction, StandardCharsets}

import text.{StringNone, StringOption, StringSome}

import scala.sys.process.{Process, ProcessBuilder}

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
trait MorphemeAnalyzer {
  def analyze(sentence: StringOption): Iterator[String] = {
    import util.process.ProcessBuilderUtils._
    (echo(sentence) #| analyzer()).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone)
  }

  def analysisResult(sentence: StringOption): StringOption = {
    val it: Iterator[String] = analyze(sentence)
    val builder: StringBuilder = new StringBuilder()
    while (it.hasNext) {
      val line: String = it.next
      builder.
        append(line).
        append('\n')
    }
    StringOption(builder.result)
  }

  def analyzer(): ProcessBuilder

  def echo(sentence: StringOption): ProcessBuilder = {
    Process(sentence match {
      case StringSome(s) =>
        Seq("echo", s.toString)
      case StringNone =>
        Nil
    })
  }

  protected val adverbialNouns: Seq[String] = Nil

  protected val formalNouns: Seq[String] = Nil

  protected val functionalVerbs: Seq[String] = Nil

  protected val textColumn: Int = 6
  protected val sourceColumn: Int = 9
  protected val semanticCategoryColumn: Int = 10
  protected val semanticTypeColumn: Int = 11

  protected val negativePosListForContentWord: Seq[String] = Seq.empty[String]

  def isContentWord(originalTextOpt: StringOption, posOpt: StringOption): Boolean = {
    isContentWord(posOpt) && {
      if (originalTextOpt.isEmpty) {
        return false
      }

      val text: String = originalTextOpt.get
      if (adverbialNouns contains text) {
        return false
      }

      if (formalNouns contains text) {
        return false
      }

      if (functionalVerbs contains text) {
        return false
      }

      true
    }
  }

  private def isContentWord(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    val pos: String = posOpt.get
    negativePosListForContentWord foreach {
      case p if pos startsWith p =>
        return false
      case _ =>
        // Do nothing
    }
    true
  }

  def isVerb(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    posOpt.get startsWith "動詞"
  }

  def isAdverb(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    posOpt.get startsWith "形容詞"
  }

  def isPrefix(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    posOpt.get startsWith "接頭"
  }

  def isSuffix(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    posOpt.get startsWith "接尾"
  }

  def extractContentWords(sentence: StringOption): Seq[String] = {
    extractWords(sentence, needsContentWords = true)
  }

  def extractWords(sentence: StringOption): Seq[String] = {
    extractWords(sentence, needsContentWords = false)
  }

  protected def extractWords(sentence: StringOption, needsContentWords: Boolean): Seq[String]

  protected type OriginalTextTextPOS = (String, String, String, StringOption, Seq[String])
  //return originalText, text, pos
  def extractMorphemes(sentence: StringOption): Seq[OriginalTextTextPOS]
}
