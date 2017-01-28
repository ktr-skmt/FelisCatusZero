package time

/**
 * @param beginTime beginning time
 * @param endTime ending time
 * @param beginTimeTextList beginning time text list
 * @param endTimeTextList ending time text list
 * @author K.Sakamoto
 */
class TimeTmp(val beginTime: Option[Int],
              val endTime:   Option[Int],
              val beginTimeTextList: Seq[String],
              val endTimeTextList:   Seq[String]) {
  private val delimiter: String = ", "
  override def toString: String = {
    """BeginTime: %s (%s)
      |EndTime: %s (%s)
      |""".stripMargin.format(
        beginTime, beginTimeTextList.mkString(delimiter),
        endTime, endTimeTextList.mkString(delimiter)
    )
  }
}
