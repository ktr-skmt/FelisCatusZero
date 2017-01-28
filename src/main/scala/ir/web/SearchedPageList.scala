package ir.web

import java.net.URL

import ir.Query

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
class SearchedPageList(val searchedPages: Array[SearchedPage],
                       val query: Query,
                       val startIndex: Int,
                       val currentUrl: URL,
                       val nextUrl: URL) extends Iterable[SearchedPage] {
  def find(rank: Int): Option[SearchedPage] = {
    searchedPages foreach {
      case searchedPage if searchedPage.rank == rank =>
        return Option(searchedPage)
      case _ =>
    }
    None
  }

  def contains(index: Int): Boolean = {
    (0 until size contains index) &&
      Option(searchedPages(index)).nonEmpty
  }

  @throws[ArrayIndexOutOfBoundsException]
  def apply(index: Int): SearchedPage = {
    searchedPages(index)
  }

  override def size: Int = {
    searchedPages.length
  }

  override def iterator: Iterator[SearchedPage] = {
    searchedPages.iterator
  }
}
