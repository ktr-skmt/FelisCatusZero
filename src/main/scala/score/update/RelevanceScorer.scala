package score.update

import jeqa.types.{Document, Score, Sentence}
import text.similarity.{AverageSimilarityCalculator, SimilarityCalculator}
import text.vector._
import util.uima.FSListUtils._

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 3/11/15.
 * </pre>
 * @param instruction instruction
 * @param scoreIndex score index
 * @author K.Sakamoto
 */
class RelevanceScorer(instruction: Document, scoreIndex: Int) extends Scorer {
  private val SCORER_NAME: String = "WordBasedRelevanceScorer"

  private lazy val similarityCalculator: SimilarityCalculator = {
    new SimilarityCalculator(FrequencyVectorGenerator.getVectorFromAnnotation(instruction))
  }

  private lazy val averageSimilarityCalculator: AverageSimilarityCalculator = {
    new AverageSimilarityCalculator({
      for (sentence <- instruction.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]) yield {
        FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
      }
    })
  }

  override def scoreBySentence(sentence: Sentence): Double = {
    val similarity: Double = averageSimilarityCalculator.calculate(
      FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
    )
    val score: Score = sentence.getScoreList(scoreIndex)
    score.setScorer(SCORER_NAME)
    score.setScore(similarity)
    sentence.setScoreList(scoreIndex, score)
    similarity
  }

  override def scoreBySentences(document: Document): Double = {
    val score: Double = similarityCalculator.calculate(
      FrequencyVectorMerger.merge {
        val buffer: ListBuffer[FrequencyVector] = ListBuffer.empty[FrequencyVector]
        for (sentence <- document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]) {
          buffer += FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
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
