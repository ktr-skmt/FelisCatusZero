package ir.web.google

import ir.web
import text.normalizer.NormalizedString

/**
 * <pre>
 * Created on 6/2/15.
 * </pre>
 * @author K.Sakamoto
 */
class GoogleKeywordQuery(override val keyword: NormalizedString) extends web.WebKeywordQuery(keyword) with GoogleQuery
