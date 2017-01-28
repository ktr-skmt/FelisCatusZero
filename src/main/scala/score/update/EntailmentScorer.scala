package score.update

import jeqa.types.{Document, Score, Sentence}
import text.similarity.{AverageOverlapCalculator, OverlapCalculator}
import text.vector.{BinaryVector, BinaryVectorGenerator, BinaryVectorMerger}
import util.uima.FSListUtils._
import util.uima.StringListUtils._

import scala.collection.mutable.ListBuffer


/**
 * <pre>
 * Created on 3/11/15.
 * </pre>
 * @param instruction instruction
 * @param scoreIndex score index
 * @author K.Sakamoto
 */
class EntailmentScorer(instruction: Document, scoreIndex: Int) extends Scorer {
  private val SCORER_NAME: String = "WordBasedEntailment"

  private lazy val convergenceCalculator: OverlapCalculator = {
    new OverlapCalculator(BinaryVectorGenerator.getVectorFromAnnotation(instruction))
  }

  private lazy val averageConvergenceCalculator: AverageOverlapCalculator = {
    new AverageOverlapCalculator({
      for (sentence <- instruction.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]) yield {
        BinaryVectorGenerator.getVectorFromAnnotation(sentence)
      }
    })
  }

  override def scoreBySentence(sentence: Sentence): Double = {
    val convergence: Double = averageConvergenceCalculator.
      calculate(new BinaryVector(sentence.getContentWordList.toSeq.distinct))
    val score: Score = sentence.getScoreList(scoreIndex)
    score.setScorer(SCORER_NAME)
    score.setScore(convergence)
    sentence.setScoreList(scoreIndex, score)
    convergence
  }

  override def scoreBySentences(document: Document): Double = {
    val score: Double = convergenceCalculator.calculate(
      BinaryVectorMerger.merge {
        val buffer: ListBuffer[BinaryVector] = ListBuffer.empty[BinaryVector]
        for (sentence <- document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]) {
          buffer += new BinaryVector(sentence.getContentWordList.toSeq.distinct)
        }
        buffer.result
      }
    )
    val scoreType: Score = document.getScoreList(scoreIndex)
    scoreType.setScorer(SCORER_NAME)
    scoreType.setScore(score)
    document.setScoreList(scoreIndex, scoreType)
    score
  }
}
