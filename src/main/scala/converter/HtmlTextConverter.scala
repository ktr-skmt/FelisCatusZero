package converter

import org.jsoup.Jsoup
import org.jsoup.nodes.{Document, TextNode}
import text.StringOption
import util.JsoupHelper

/**
 * <pre>
 * Created on 6/3/15.
 * </pre>
 * @author K.Sakamoto
 */
object HtmlTextConverter {
  private val negativeTagList: String = cssSelector(Seq(
    "applet",
    "area",
    "base",
    "basefont",
    "bgsound",
    "button",
    "caption",
    "code",
    "col",
    "colgroup",
    "comment",
    "del",
    "dir",
    "embed",
    "fieldset",
    "form",
    "frame",
    "frameset",
    "head",
    "hr",
    "iframe",
    "ilayer",
    "img",
    "input",
    "isindex",
    "label",
    "layer",
    "legend",
    "link",
    "map",
    "meta",
    "noembed",
    "noframes",
    "nolayer",
    "noscript",
    "object",
    "optgroup",
    "option",
    "param",
    "script",
    "select",
    "style",
    "table",
    "tbody",
    "td",
    "textarea",
    "tfoot",
    "th",
    "thead",
    "title",
    "tr"
  ))

  private val breakTags: String = cssSelector(Seq[String](
    "br",
    "wbr"
  ))

  private def cssSelector(tagNames: Seq[String]): String = {
    val list: Seq[String] = tagNames map {".".concat}
    val builder: StringBuilder = new StringBuilder()
    list foreach {builder.append(',').append}
    builder.deleteCharAt(0).result
  }

  def toHtml(html: String): Option[Document] = {
    Option(Jsoup.parse(html))
  }

  def toText(html: Document): StringOption = {
    import JsoupHelper.elementsToElements4Scala
    val builder: StringBuilder = new StringBuilder()
    for (element <- html.body.children.not(negativeTagList).toElementArray) {
      for (e <- element.select(breakTags).toElementArray) {
        e.replaceWith(new TextNode("\n", ""))
      }
      val text: String = element.text.trim
      if (0 < text.length) {
        builder.
          append(text).
          append('\n')
      }
    }
    StringOption(builder.result)
  }
}
