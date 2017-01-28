package ir.query

import ir.Query
import text.normalizer.NormalizedString

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
 * @param keywords keywords
 * @author K.Sakamoto
 */
abstract class KeywordsQuery(val keywords: Seq[String]) extends Query {
  override val query: NormalizedString
}
