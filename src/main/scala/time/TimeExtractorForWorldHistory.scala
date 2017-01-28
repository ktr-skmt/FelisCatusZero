package time

import text.StringOption

/**
 * @author K.Sakamoto
 *         Created on 15/10/19
 */
object TimeExtractorForWorldHistory extends TimeExtractor {
  override def extract(text: StringOption): Seq[TimeTmp] = {
    TimeExtractorInTimeExpression.extract(text).toList :::
    TimeExtractorInGlossaryEntries.extract(text).toList :::
    TimeExtractorInEventOntologyEntries.extract(text).toList
  }
}
