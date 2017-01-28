package text

/**
  * @author K.Sakamoto
  *         Created on 2015/11/27
  */
object TermNormalizerForWorldHistory extends TermNormalizer {
  override def normalize(textOpt: StringOption): StringOption = {
    TermNormalizerInGlossary.normalize(
      TermNormalizerInEventOntology.normalize(textOpt))
  }
}
