package time

import jeqa.types.Sentence

/**
 * @author K.Sakamoto
 */
object TimeSorter {
  def sort(input: Seq[Sentence]): Seq[Sentence] = {
    for (d <- {
      for (data <- input) yield {
        val time: TimeTmp = new TimeTmp(
          if (Option(data.getBeginTime).nonEmpty) {Option(data.getBeginTime.getYear)} else None,
          if (Option(data.getEndTime).nonEmpty) {Option(data.getEndTime.getYear)} else None,
          Nil,
          Nil
        )
        val m: Int = time.beginTime match {
          case Some(b) =>
            time.endTime match {
              case Some(e) => (b + e) / 2
              case None => b
            }
          case None =>
            time.endTime match {
              case Some(e) => e
              case None => 0
            }
        }
        (m, data)
      }}.sortWith((s1, s2) => s1._1 < s2._1)) yield {
      d._2
    }
  }

}
