package evaluation

/**
  * <pre>
  * Created on 2016/12/16.
  * </pre>
  *
  * @author K.Sakamoto
  */
object MacroAveraging {
  def summaryStatistics(metrics: Array[Seq[Double]]): SummaryStatistics = {
    SummaryStatistics(metrics)
  }
}
