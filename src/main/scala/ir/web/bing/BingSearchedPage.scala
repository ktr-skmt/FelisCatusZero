package ir.web.bing

import ir.web

/**
 * <pre>
 * Created on 6/1/15.
 * </pre>
 * @param title title
 * @param description description
 * @param url URL
 * @param rank rank
 * @author K.Sakamoto
 */
class BingSearchedPage(override val title: String,
                       override val description: String,
                       override val url: String,
                       override val rank: Int) extends web.SearchedPage(title, description, url, rank)