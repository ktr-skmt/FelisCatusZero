package score.noupdate

import jeqa.types.Sentence
import score.{Granularity, ScorerType}
import util.Config

/**
  * <pre>
  * Created on 2016/12/09.
  * </pre>
  * @param summarySentences summary sentences
  * @author K.Sakamoto
  */
class NoUpdateTypeScorer(summarySentences: Seq[Sentence]) extends Scorer {
  private val scorer: Scorer = Config.updateTypeScorer match {
    case ScorerType.Entailment =>
      new EntailmentScorer(summarySentences)
    case ScorerType.Relevance =>
      new RelevanceScorer(summarySentences)
    case _ =>
      new RelevanceScorer(summarySentences)
  }

  override def scoreBySentence(sentence: Sentence): Double = {
    scorer.scoreBySentence(sentence)
  }

  override def scoreBySentence(sentences: Seq[Sentence]): Double = {
    scorer.scoreBySentence(sentences)
  }

  override def scoreBySentences(sentences: Seq[Sentence]): Double = {
    scorer.scoreBySentences(sentences)
  }

  override def score(sentences: Seq[Sentence]): Double = {
    scorer.score(sentences)
  }
}

/**
  * @author K.Sakamoto
  */
trait Scorer {
  def score(sentences: Seq[Sentence]): Double = {
    Config.granularity match {
      case Granularity.Sentence =>
        scoreBySentence(sentences)
      case Granularity.Sentences =>
        scoreBySentences(sentences)
      case Granularity.None =>
        0D
    }
  }
  def scoreBySentence(sentence: Sentence): Double
  def scoreBySentence(sentences: Seq[Sentence]): Double = {
    var score: Double = 0D
    sentences foreach {
      sentence: Sentence =>
        score += scoreBySentence(sentence)
    }
    score / sentences.size
  }
  def scoreBySentences(sentences: Seq[Sentence]): Double
}