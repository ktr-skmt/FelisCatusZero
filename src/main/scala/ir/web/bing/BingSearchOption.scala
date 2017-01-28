package ir.web.bing

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
object BingSearchOption {
  def apply(): BingSearchOption = {
    new BingSearchOption(0)
  }

  def apply(skipNum: Int): BingSearchOption = {
    new BingSearchOption(skipNum)
  }
}

/**
  * @author K.Sakamoto
  * @param skipNum skipping number
  */
class BingSearchOption(val skipNum: Int)
