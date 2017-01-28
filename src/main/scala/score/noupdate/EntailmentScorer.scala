package score.noupdate

import jeqa.types.Sentence
import text.similarity.{AverageOverlapCalculator, OverlapCalculator}
import text.vector._

import scala.collection.mutable.ListBuffer

/**
  * <pre>
  * Created on 2016/12/09.
  * </pre>
  * @param summarySentences summary sentences
  * @author K.Sakamoto
  */
class EntailmentScorer(summarySentences: Seq[Sentence]) extends Scorer {
  private lazy val convergenceCalculator: OverlapCalculator = {
    new OverlapCalculator(BinaryVectorGenerator.getVectorFromSentences(summarySentences))
  }

  private lazy val averageConvergenceCalculator: AverageOverlapCalculator = {
    new AverageOverlapCalculator({
      for (sentence <- summarySentences) yield {
        BinaryVectorGenerator.getVectorFromAnnotation(sentence)
      }
    })
  }

  override def scoreBySentence(sentence: Sentence): Double = {
    averageConvergenceCalculator.calculate(
      BinaryVectorGenerator.getVectorFromAnnotation(sentence)
    )
  }

  override def scoreBySentences(sentences: Seq[Sentence]): Double = {
    convergenceCalculator.calculate(
      BinaryVectorMerger.merge {
        val buffer: ListBuffer[BinaryVector] = ListBuffer.empty[BinaryVector]
        for (sentence <- sentences) {
          buffer += BinaryVectorGenerator.getVectorFromAnnotation(sentence)
        }
        buffer.result
      }
    )
  }
}
