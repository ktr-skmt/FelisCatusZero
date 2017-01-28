package evaluation

import text.similarity.Divider

/**
  * <pre>
  * Created on 2016/12/16.
  * </pre>
  *
  * @author K.Sakamoto
  */
object SummaryStatistics {
  def apply(metrics: Seq[Double], sizeOfDataSets: Int): SummaryStatistics = {
    val size: Int = metrics.size

    val mean: Double = {
      var summation: Double = 0D
      metrics foreach {
        score: Double =>
          summation += score
      }
      Divider.divide(summation, size)
    }

    val max: Double = {
      metrics.max
    }

    val min: Double = {
      metrics.min
    }

    val median: Double = {
      val scores: Seq[Double] = metrics.sorted
      scores(size / 2)
    }

    val variance: Double = {
      var summation: Double = 0D
      metrics foreach {
        score: Double =>
          summation += math.pow(score - mean, 2)
      }
      Divider.divide(summation, size)
    }

    val standardDeviation: Double = {
      math.sqrt(variance)
    }

    new SummaryStatistics(
      sizeOfDataSet = sizeOfDataSets,
      sizeOfScores = size,
      mean = mean,
      max = max,
      min = min,
      median = median,
      variance = variance,
      standardDeviation = standardDeviation
    )
  }

  def apply(metrics: Array[Seq[Double]]): SummaryStatistics = {
    val sizeOfDataSet: Int = metrics.length
    var sizeOfScores: Int = 0
    var totalMean: Double = 0D
    var totalMax: Double = 0D
    var totalMin: Double = 0D
    var totalMedian: Double = 0D
    var totalVariance: Double = 0D
    var totalStandardDeviation: Double = 0D
    metrics foreach {
      scores: Seq[Double] =>
        val summaryStatistics: SummaryStatistics = SummaryStatistics(scores, 1)
        sizeOfScores += summaryStatistics.sizeOfScores
        totalMean += summaryStatistics.mean
        totalMax += summaryStatistics.max
        totalMin += summaryStatistics.min
        totalMedian += summaryStatistics.median
        totalVariance += summaryStatistics.variance
        totalStandardDeviation += summaryStatistics.standardDeviation
    }
    if (sizeOfDataSet <= 0) {
      return new SummaryStatistics(
        sizeOfDataSet = 0,
        sizeOfScores = sizeOfScores,
        mean = 0D,
        max = 0D,
        min = 0D,
        median = 0D,
        variance = 0D,
        standardDeviation = 0D
      )
    }
    new SummaryStatistics(
      sizeOfDataSet = sizeOfDataSet,
      sizeOfScores = sizeOfScores,
      mean = totalMean / sizeOfDataSet,
      max = totalMax / sizeOfDataSet,
      min = totalMin / sizeOfDataSet,
      median = totalMedian / sizeOfDataSet,
      variance = totalVariance / sizeOfDataSet,
      standardDeviation = totalStandardDeviation / sizeOfDataSet
    )
  }
}

class SummaryStatistics(val sizeOfDataSet: Int,
                        val sizeOfScores: Int,
                        val mean: Double,
                        val max: Double,
                        val min: Double,
                        val median: Double,
                        val variance: Double,
                        val standardDeviation: Double) {
  override def toString: String = {
    f"""<td>$sizeOfDataSet</td><td>$sizeOfScores</td><td>$mean%.5f</td><td>$max%.5f</td><td>$median%.5f</td><td>$min%.5f</td><td>$variance%.5f</td><td>$standardDeviation%.5f</td>"""
  }
}