package evaluation

/**
  * <pre>
  * Created on 2016/12/16.
  * </pre>
  *
  * @author K.Sakamoto
  */
object MicroAveraging {
  def summaryStatistics(metrics: Array[Seq[Double]]): SummaryStatistics = {
    SummaryStatistics(metrics.flatten, metrics.length)
  }

  def summaryStatistics(metrics: Seq[Double]): SummaryStatistics = {
    SummaryStatistics(metrics, 1)
  }
}
