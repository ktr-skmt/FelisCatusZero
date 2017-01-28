package ir.web

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import ir.query.KeywordQuery
import text.StringNone
import text.normalizer.NormalizedString

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @author K.Sakamoto
 */
class WebKeywordQuery(override val keyword: NormalizedString) extends KeywordQuery(keyword) with WebQuery {
  override val query: NormalizedString = try {
    NormalizedString(
      keyword.toStringOption map {
        k: String =>
          URLEncoder.encode(
            k.toString,
            StandardCharsets.UTF_8.name)
    })
  } catch {
    case e: UnsupportedEncodingException =>
      e.printStackTrace()
      NormalizedString(StringNone)
  }
}
