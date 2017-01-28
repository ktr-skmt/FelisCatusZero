package ir.web.bing

import java.io.IOException
import java.net.URL

import ir.Query
import ir.web
import ir.web.SearchedPageListBuilder

import scala.util.matching.Regex
import scala.xml.{NodeSeq, XML}

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
 * @param accountKey account key
 * @author K.Sakamoto
 */
class BingSearcher(accountKey: String) extends web.Searcher[BingQuery] {
  BingAuthenticator.setAuth(accountKey)

  override protected val rootUrl: String = "https://api.datamarket.azure.com/Bing/Search/Web?Query="
  private var option: BingSearchOption = BingSearchOption()

  def searchOption(searchOption: BingSearchOption): BingSearcher = {
    option = searchOption
    this
  }

  override def search(query: Query): Option[web.SearchedPageList] = {
    val url: URL = generateUrl(query)
    get(url) map {
      searchedPageList_ : web.SearchedPageList =>
        searchedPageList(searchedPageList_)
        searchedPageList_
    }
  }

  private def getGETResponse(url: URL): NodeSeq = {
    try {
      XML.load(url)
    } catch {
      case e: IOException =>
        e.printStackTrace()
        Nil
    }
  }
  private val skipTopRegex: Regex = """.+&amp;$skip=(\d+)&amp;$top=(\d+)""".r

  override protected def get(url: URL): Option[web.SearchedPageList] = {
    val searchResult: NodeSeq = getGETResponse(url)
    var startIndex: Int = 0
    url.toString match {
      case skipTopRegex(skip, top) =>
        try {
          startIndex = skip.toInt
        } catch {
          case e: NumberFormatException =>
            e.printStackTrace()
        }
      case _ =>
        // Do nothing
    }

    var nextUrlOpt: Option[URL] = None
    (searchResult \ "link") foreach {
      case link if (link \ "@rel").text == "next" =>
        nextUrlOpt = Option(new URL((link \ "@href").text))
      case _ =>
        // Do nothing
    }

    if (queryOpt.isEmpty || nextUrlOpt.isEmpty) {
      return None
    }

    val q: Query = queryOpt.get
    val nextUrl: URL = nextUrlOpt.get

    val builder: SearchedPageListBuilder = BingSearchedPageListBuilder().
      query(q).startIndex(startIndex).currentUrl(url).nextUrl(nextUrl)
    searchResult \ "entry" foreach {
      entry: NodeSeq =>
        val rank = (entry \ "id").text match {
          case skipTopRegex(skip, top) =>
            try {
              skip.toInt
            } catch {
              case e: NumberFormatException =>
                e.printStackTrace()
                -1
            }
          case _ =>
            -1
        }
        val m: NodeSeq = entry \ "content" \ "m.properties"
        builder += new BingSearchedPage(
          (m \ "d:Title").text,
          (m \ "d:Description").text,
          (m \ "d:Url").text,
          rank)
    }
    Option(builder.result)
  }
}
