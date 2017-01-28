package text.analyzer.dep.chapas

import text.StringOption
import text.normalizer.NormalizedString
import util.Cache

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
abstract class PasAnalyzer extends Cache {
  def analyze(sentence: NormalizedString): Iterator[String]

  def cabochaLattice(sentence: NormalizedString): Iterator[String]

  def cabochaTree(sentence: NormalizedString): Iterator[String]

  protected[this] def textToDoc(normalizedSentence: NormalizedString): Doc

  val parser: NtcParser

  /*
  def parse(normalizedSentence: NormalizedString): Doc = {
    val doc: Doc = this.textToDoc(NormalizedStringOption(normalizedSentence))
    doc.sentences.foreach { s =>
      val normalizedString: NormalizedStringOption = NormalizedStringOption(NormalizedString(StringOption(s.text)))
      s.depTree = this.cabochaTree(normalizedString).mkString("\n").stripLineEnd
    }
    doc
  }
  */

  def parse(normalizedSentence: NormalizedString): Doc =
    textToDoc(normalizedSentence)

  def parse(normalizedSentences: Seq[NormalizedString]): Doc =
    parse(NormalizedString(StringOption(normalizedSentences.mkString("\n"))))
}
