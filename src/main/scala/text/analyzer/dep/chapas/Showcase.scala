package text.analyzer.dep.chapas

import java.nio.charset.{CodingErrorAction, StandardCharsets}

import text.StringNone
import text.analyzer.dep.cabocha.CaboCha
import text.normalizer.NormalizedString
import util.{SentenceUnitReconstructorConfig, ShowcaseCache}
import util.process.EchoProcess
import util.process.ProcessBuilderUtils._


import scala.sys.process.Process

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
object Showcase extends PasAnalyzer with EchoProcess with ShowcaseCache {
  private val showcaseCmd: String = {
    SentenceUnitReconstructorConfig.showcase match {
      case Some(c) => c
      case None => throw new RuntimeException("Showcase command was not found.")
    }
  }

  override def analyze(sentence: NormalizedString): Iterator[String] = {
    CaboCha.cabochaLatticeJumanCmd(sentence).#|(Process(showcaseCmd)).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone
    )
  }

  override def cabochaLattice(sentence: NormalizedString): Iterator[String] = {
    CaboCha.cabochaLatticeJumanCmd(sentence).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone
    )
  }

  override def cabochaTree(sentence: NormalizedString): Iterator[String] = {
    CaboCha.cabochaTreeJumanCmd(sentence).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone
    )
  }

  override final protected[this] def textToDoc(normalizedSentencesOption: NormalizedString): Doc =
    (if (cacheEnable) getDocCache(normalizedSentencesOption) else None) getOrElse
      this.parser.parse(analyze(normalizedSentencesOption))

  override val parser: ShowcaseNtcParser.type = ShowcaseNtcParser
}