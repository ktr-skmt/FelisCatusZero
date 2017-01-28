package text.analyzer.dep.chapas

import text.normalizer.NormalizedString

import scala.beans.BeanProperty

/**
  * @author Nakayama
  *         Created on 2015/11/19
  */
class NonPredicateArgumentStructure extends PredicateArgumentStructureAnalysisResult {
  @BeanProperty var text: NormalizedString = _
}
