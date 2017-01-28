package score.noupdate

import jeqa.types.Sentence
import text.similarity.{AverageSimilarityCalculator, SimilarityCalculator}
import text.vector.{FrequencyVector, FrequencyVectorGenerator, FrequencyVectorMerger}

import scala.collection.mutable.ListBuffer

/**
  * <pre>
  * Created on 2016/12/09.
  * </pre>
  *
  * @author K.Sakamoto
  */
class RelevanceScorer(summarySentences: Seq[Sentence]) extends Scorer {
  private lazy val similarityCalculator: SimilarityCalculator = {
    new SimilarityCalculator(FrequencyVectorGenerator.getVectorFromSentences(summarySentences))
  }

  private lazy val averageSimilarityCalculator: AverageSimilarityCalculator = {
    new AverageSimilarityCalculator({
      for (sentence <- summarySentences) yield {
        FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
      }
    })
  }

  override def scoreBySentence(sentence: Sentence): Double = {
    averageSimilarityCalculator.calculate(
      FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
    )
  }

  override def scoreBySentences(sentences: Seq[Sentence]): Double = {
    similarityCalculator.calculate(
      FrequencyVectorMerger.merge {
        val buffer: ListBuffer[FrequencyVector] = ListBuffer.empty[FrequencyVector]
        for (sentence <- sentences) {
          buffer += FrequencyVectorGenerator.getVectorFromAnnotation(sentence)
        }
        buffer.result
      }
    )
  }
}
