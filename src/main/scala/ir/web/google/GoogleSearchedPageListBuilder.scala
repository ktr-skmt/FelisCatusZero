package ir.web.google

import ir.web.{SearchedPageListBuilder, SearchedPageListBuilderImpl}

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @author K.Sakamoto
 */
object GoogleSearchedPageListBuilder extends SearchedPageListBuilderImpl {
  override def apply(): SearchedPageListBuilder = {
    new GoogleSearchedPageListBuilder(16)
  }

  override def apply(capacity: Int): SearchedPageListBuilder = {
    new GoogleSearchedPageListBuilder(capacity)
  }

  private class GoogleSearchedPageListBuilder(capacity: Int) extends SearchedPageListBuilder(capacity)
}
