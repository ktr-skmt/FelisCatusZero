package ir.web

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

import ir.query.KeywordsQuery
import text.{StringNone, StringOption}
import text.normalizer.NormalizedString

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
 * @param keywords keywords
 * @author K.Sakamoto
 */
class WebKeywordsQuery(override val keywords: Seq[String]) extends KeywordsQuery(keywords) with WebQuery {
  override val query: NormalizedString = try {
    NormalizedString(StringOption(URLEncoder.encode(generate, StandardCharsets.UTF_8.name)))
  } catch {
    case e: UnsupportedEncodingException =>
      e.printStackTrace()
      NormalizedString(StringNone)
  }

  protected val delimiter: Char = ' '

  private def generate: String = {
    val builder: StringBuilder = new StringBuilder(keywords.size * 2 - 1)
    keywords foreach {
      query: String =>
        builder.append(delimiter).
                append(query)
    }
    builder.deleteCharAt(0).result
  }
}
