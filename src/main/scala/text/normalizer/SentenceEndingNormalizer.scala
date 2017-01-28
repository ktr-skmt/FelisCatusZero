package text.normalizer

import text.StringOption

/**
  * @author K.Sakamoto
  *         Created on 2016/08/06
  */
object SentenceEndingNormalizer
  extends DictionaryBasedNormalizer(
    StringOption("sentence_ending_normalization.yml")) {
  override protected def replaceAll(input: String, term: String, replacement: String): String = {
    input.replaceAll(raw"""$term$$""", replacement)
  }
}
