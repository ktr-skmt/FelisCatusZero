package ir.web.google

import ir.web

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @param title title
 * @param description description
 * @param url URL
 * @param rank rank
 * @author K.Sakamoto
 */
class GoogleSearchedPage(override val title: String,
                         override val description: String,
                         override val url: String,
                         override val rank: Int) extends web.SearchedPage(title, description, url, rank)
