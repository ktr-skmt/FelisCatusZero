package converter

import info.bliki.wiki.model.WikiModel
import org.jsoup.nodes.Document
import text.{StringNone, StringOption, StringSome}

/**
 * <pre>
 * Created on 6/3/15.
 * </pre>
 * @author K.Sakamoto
 */
object WikiArticleConverter {
  def toHtml(article: StringOption): Option[Document] = {
    article match {
      case StringSome(a) =>
        HtmlTextConverter.toHtml(WikiModel.toHtml(a))
      case StringNone =>
        None
    }
  }

  def toText(article: StringOption): StringOption = {
    toHtml(article) match {
      case Some(document) =>
        HtmlTextConverter.toText(document)
      case None =>
        StringNone
    }
  }
}
