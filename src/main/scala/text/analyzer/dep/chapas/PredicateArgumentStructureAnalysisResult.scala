package text.analyzer.dep.chapas

import scala.beans.BeanProperty

/**
  * @author Nakayama
  *         Created on 2015/11/19
  */
trait PredicateArgumentStructureAnalysisResult {
  @BeanProperty var idOpt: Option[Int] = _
  @BeanProperty var morphemes: Seq[String] = _

}
