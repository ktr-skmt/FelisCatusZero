package ir.query

import ir.Query
import text.normalizer.NormalizedString

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
 * @param keyword keyword
 * @author K.Sakamoto
 */
abstract class KeywordQuery(val keyword: NormalizedString) extends Query {
  override val query: NormalizedString
}
