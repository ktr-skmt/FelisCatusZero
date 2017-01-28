package ir.web.bing

import ir.web

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @param keywords keywords
 * @author K.Sakamoto
 */
class BingKeywordsQuery(override val keywords: Seq[String]) extends web.WebKeywordsQuery(keywords) with BingQuery {
  override protected val delimiter: Char = ' '
}
