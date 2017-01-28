package ir.web.google

import ir.web

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @param keywords keywords
 * @author K.Sakamoto
 */
class GoogleKeywordsQuery(override val keywords: Seq[String]) extends web.WebKeywordsQuery(keywords) with GoogleQuery {
  override protected val delimiter: Char = '+'
}
