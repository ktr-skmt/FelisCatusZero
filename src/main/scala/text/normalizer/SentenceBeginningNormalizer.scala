package text.normalizer

import text.StringOption

/**
  * @author K.Sakamoto
  *         Created on 2016/08/06
  */
object SentenceBeginningNormalizer
  extends DictionaryBasedNormalizer(
    StringOption("sentence_beginning_normalization.yml")) {

  override protected def replaceAll(input: String, term: String, replacement: String): String = {
    input.replaceAll(raw"""^$term""", replacement)
  }
}
