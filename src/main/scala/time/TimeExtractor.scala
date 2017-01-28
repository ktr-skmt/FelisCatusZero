package time

import text.StringOption

/**
 * @author K.Sakamoto
 *         Created on 15/10/19
 */
trait TimeExtractor {
  def extract(text: StringOption): Seq[TimeTmp]

  def extractUnionTime(text: StringOption): TimeTmp = {
    val timeRanges: Seq[TimeTmp] = extract(text)
    TimeMerger.union(timeRanges)
  }

  def extractIntersectTime(text: StringOption): TimeTmp = {
    val timeRanges: Seq[TimeTmp] = extract(text)
    TimeMerger.intersect(timeRanges)
  }
}
