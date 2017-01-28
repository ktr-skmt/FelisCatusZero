package time

import text.StringOption
import ner.NamedEntityRecognizerInEventOntology

/**
  * @author K.Sakamoto
  *         Created on 2015/11/26
  */
object TimeExtractorInEventOntologyEntries extends TimeExtractor {
  override def extract(text: StringOption): Seq[TimeTmp] = {
    NamedEntityRecognizerInEventOntology.recognize(text).map {
      _.time
    }
  }
}
