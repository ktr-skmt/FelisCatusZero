package ir.web.google

import java.net.URL
import java.nio.charset.StandardCharsets

import ir.{Query, web}
import ir.web.SearchedPageListBuilder

import scala.io.Source
import scala.util.parsing.json.{JSON, JSONArray, JSONObject}

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @param key key
 * @param cx cx
 * @author K.Sakamoto
 */
class GoogleSearcher(key: String, cx: String) extends web.Searcher[GoogleQuery] {
  override protected val rootUrl: String = "https://www.googleapis.com/customsearch/v1?key=%s&cx=%s&q=%s"
  private val startIndexGETRequest: String = "&start=%d"

  override protected def generateUrl(query: Query): URL = {
    new URL(rootUrl.format(key, cx, query.query))
  }

  private def generateUrl(query: Query, startIndex: Int): URL = {
    new URL(generateUrl(query).toString.concat(startIndexGETRequest.format(startIndex)))
  }

  override def search(query: Query): Option[web.SearchedPageList] = {
    get(generateUrl(query))
  }

  override protected def get(url: URL): Option[web.SearchedPageList] = {
    queryOpt map {
      query: Query =>
        val json: StringBuilder = new StringBuilder()
        Source.fromURL(url, StandardCharsets.UTF_8.name).getLines foreach {
          line: String =>
            json.append(line)
        }
        JSON.parseRaw(json.result).map {
          searchResult =>
            val searchedPageList_ : web.SearchedPageList = parseSearchResult(query, url, searchResult.asInstanceOf[JSONObject])
            searchedPageList(searchedPageList_)
            searchedPageList_
        }.get
    }
  }

  private def parseSearchResult(query: Query, currentUrl: URL, searchResult: JSONObject): web.SearchedPageList = {
    val builder: SearchedPageListBuilder = GoogleSearchedPageListBuilder()

    val queries: Map[String, Any] = searchResult.obj.get("queries").asInstanceOf[JSONObject].obj
    val nextPage: Map[String, Any] = queries.get("nextPage").asInstanceOf[JSONArray].list.head.asInstanceOf[JSONObject].obj
    val nextStartIndex: Int = nextPage.get("startIndex").toString.toInt
    val nextUrl: URL = generateUrl(query, nextStartIndex)

    val currentPage: Map[String, Any] = queries.get("request").asInstanceOf[JSONArray].list.head.asInstanceOf[JSONObject].obj
    val currentStartIndex: Int = currentPage.get("startIndex").toString.toInt

    builder.query(query).startIndex(currentStartIndex).currentUrl(currentUrl).nextUrl(nextUrl)

    var counter: Int = 0
    searchResult.obj.get("items").asInstanceOf[JSONArray].list foreach {
      case item: JSONObject =>
        builder += new GoogleSearchedPage(
          item.obj.get("title").toString,
          item.obj.get("snippet").toString,
          item.obj.get("link").toString,
          currentStartIndex + counter
        )
        counter += 1
      case _ =>
    }
    builder.result
  }
}
