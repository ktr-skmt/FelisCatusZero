package ir.web

import java.net.URL

import ir.Query

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
trait SearchedPageListBuilderImpl {
  def apply(): SearchedPageListBuilder
  def apply(capacity: Int): SearchedPageListBuilder
}

object SearchedPageListBuilder extends SearchedPageListBuilderImpl {
  override def apply(): SearchedPageListBuilder = {
    new SearchedPageListBuilder(16)
  }

  override def apply(capacity: Int): SearchedPageListBuilder = {
    new SearchedPageListBuilder(capacity)
  }
}

class SearchedPageListBuilder(capacity: Int) {
  private val searchedPageList: ListBuffer[SearchedPage] = ListBuffer.empty[SearchedPage]
  private var query: Query    = _
  private var startIndex: Int = -1
  private var currentUrl: URL = _
  private var nextUrl: URL    = _

  def query(query: Query): SearchedPageListBuilder = {
    this.query = query
    this
  }

  def startIndex(startIndex: Int): SearchedPageListBuilder = {
    this.startIndex = startIndex
    this
  }

  def currentUrl(currentUrl: URL): SearchedPageListBuilder = {
    this.currentUrl = currentUrl
    this
  }

  def nextUrl(nextUrl: URL): SearchedPageListBuilder = {
    this.nextUrl = nextUrl
    this
  }

  def +=(searchedPage: SearchedPage): SearchedPageListBuilder = {
    searchedPageList += searchedPage
    this
  }

  def result: SearchedPageList = {
    new SearchedPageList(searchedPageList.result().toArray[SearchedPage],
                         query,
                         startIndex,
                         currentUrl,
                         nextUrl)
  }
}

