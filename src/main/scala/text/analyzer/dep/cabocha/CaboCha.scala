package text.analyzer.dep.cabocha

import text.normalizer.NormalizedString
import util.SentenceUnitReconstructorConfig
import util.process.EchoProcess

import scala.sys.process.{Process, ProcessBuilder}
import scala.util.matching.Regex

/**
  * Created by nakayama.
  * @author Nakayama
  */
object CaboCha extends EchoProcess {
  final private[this] val cabochaLatticeIPA: String = {
    SentenceUnitReconstructorConfig.cabochaLatticeIPA match {
      case Some(c) => c
      case None    => throw new RuntimeException("CaboCha lattice with IPA command was not found.")
    }
  }

  def cabochaLatticeIPACmd(sentence: NormalizedString): ProcessBuilder =
    echo(sentence) #| Process(cabochaLatticeIPA)

  final private[this] val cabochaTreeIPA: String = {
    SentenceUnitReconstructorConfig.cabochaTreeIPA match {
      case Some(c) => c
      case None    => throw new RuntimeException("CaboCha tree with IPA command was not found.")
    }
  }

  def cabochaTreeIPACmd(sentence: NormalizedString): ProcessBuilder =
    echo(sentence) #| Process(cabochaTreeIPA)

  final private[this] val cabochaLatticeJuman: String = {
    SentenceUnitReconstructorConfig.cabochaLatticeJuman match {
      case Some(c) => c
      case None    => throw new RuntimeException("CaboCha lattice with Juman command was not found.")
    }
  }

  def cabochaLatticeJumanCmd(sentence: NormalizedString): ProcessBuilder =
    echo(sentence) #| Process(cabochaLatticeJuman)

  final private[this] val cabochaTreeJuman: String = {
    SentenceUnitReconstructorConfig.cabochaTreeJuman match {
      case Some(c) => c
      case None    => throw new RuntimeException("CaboCha tree with Juman command was not found.")
    }
  }

  def cabochaTreeJumanCmd(sentence: NormalizedString): ProcessBuilder =
    echo(sentence) #| Process(cabochaTreeJuman)

  final val treeChunkRegExp: Regex = """(\s*)([^-D]*)(-*D?.*)""".r
}
