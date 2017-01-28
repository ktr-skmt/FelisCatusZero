package text.analyzer.dep.chapas

import text.normalizer.NormalizedString

import scala.beans.BeanProperty

/**
  * @author Nakayama
  *         Created on 2015/11/19
  */
class PredicateArgumentStructure extends PredicateArgumentStructureAnalysisResult {
  @BeanProperty var phraseType: PhraseType.Value = _
  @BeanProperty var predicate: NormalizedString = _
  @BeanProperty var ga:        NormalizedString = _
  @BeanProperty var o:         NormalizedString = _
  @BeanProperty var ni:        NormalizedString = _
  @BeanProperty var other:     NormalizedString = _
}

/**
  * @author Nakayama
  */
object PhraseType extends Enumeration {
  val None, Noun, Pred = Value
}
