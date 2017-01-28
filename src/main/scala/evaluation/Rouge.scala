package evaluation

/**
  * <pre>
  * Created on 2016/12/15.
  * </pre>
  *
  * @author K.Sakamoto
  */
trait Rouge {
  def evaluate(termList: Seq[Seq[String]], modelList: Seq[Seq[Seq[String]]]): Double
}
