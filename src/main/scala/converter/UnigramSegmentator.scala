package converter

/**
  * <pre>
  * Created on 15/10/29
  * </pre>
 * @author K. Sakamoto
 */
object UnigramSegmentator {
  val segmentator: NgramSegmentator = new NgramSegmentator(1)
}
