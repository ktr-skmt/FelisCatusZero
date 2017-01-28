package ir.web

import java.net.URL

import ir.Query

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
  * @tparam T type
 * @author K.Sakamoto
 */
abstract class Searcher[T <: WebQuery] {
  var queryOpt: Option[Query] = None
  private var searchedPageList: Option[SearchedPageList] = None
  protected val rootUrl: String

  def search(queries: Seq[T]): Seq[SearchedPageList] = {
    val list: ListBuffer[SearchedPageList] = ListBuffer[SearchedPageList]()
    queries foreach {
      case query: Query =>
        queryOpt = Option(query)
        search(query) match {
          case Some(searchResult) =>
            list += searchResult
          case None =>
        }
      case _ =>
    }
    list.result
  }

  def search(query: Query): Option[SearchedPageList]

  protected def generateUrl(query: Query): URL = {
    new URL(rootUrl.concat(query.query.toString))
  }

  def redo: Option[SearchedPageList] = {
    searchedPageList match {
      case Some(list) =>
        get(list.currentUrl)
      case None =>
        None
    }
  }

  def next: Option[SearchedPageList] = {
    searchedPageList match {
      case Some(list) =>
        get(list.nextUrl)
      case None =>
        None
    }
  }

  protected def get(url: URL): Option[SearchedPageList]

  def searchedPageList(searchedPageList_ : SearchedPageList): Unit = {
    searchedPageList = Option(searchedPageList_)
  }
}
