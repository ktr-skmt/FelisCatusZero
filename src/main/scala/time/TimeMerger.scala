package time

import scala.collection.mutable.ListBuffer

/**
 * <pre>
 * Created on 3/14/15.
 * </pre>
  *
  * @author K.Sakamoto
 */
object TimeMerger {
  def needToUpdate(yAOpt: Option[Int], yBOpt: Option[Int], isALessThanB: Boolean): Boolean = {
    yAOpt match {
      case Some(yA) =>
        yBOpt match {
          case Some(yB) =>
            if (isALessThanB) {
              yA < yB
            } else {
              yB < yA
            }
          case None =>
            true
        }
      case None =>
        false
    }
  }

  private def merge(input: Seq[TimeTmp], isUnion: Boolean): TimeTmp = {
    var beginTimeTmp: Option[Int] = None
    var endTimeTmp:   Option[Int] = None
    val beginTimeTextBuffer: ListBuffer[String] = ListBuffer.empty[String]
    val endTimeTextBuffer:   ListBuffer[String] = ListBuffer.empty[String]
    val nonUnion: Boolean = !isUnion

    input foreach {
      time: TimeTmp =>
        val beginTimeOpt: Option[Int] = time.beginTime
        val endTimeOpt:   Option[Int] = time.endTime
        beginTimeTextBuffer ++= time.beginTimeTextList
        endTimeTextBuffer   ++= time.endTimeTextList

        if (needToUpdate(beginTimeOpt, beginTimeTmp, isALessThanB = isUnion)) {
          beginTimeTmp = beginTimeOpt
        }

        if (needToUpdate(endTimeOpt, endTimeTmp, isALessThanB = nonUnion)) {
          endTimeTmp = endTimeOpt
        }
    }

    new TimeTmp(
      beginTimeTmp,
      endTimeTmp,
      beginTimeTextBuffer.result.distinct,
      endTimeTextBuffer.result.distinct)
  }

  def union(input: Seq[TimeTmp]): TimeTmp = {
    merge(input, isUnion = true)
  }

  def intersect(input: Seq[TimeTmp]): TimeTmp = {
    merge(input, isUnion = false)
  }
}
