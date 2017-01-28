package util.uima

import jeqa.types.Time

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
object TimeUtils {
  implicit def time2TimeUtils(repr: Time): TimeUtils = {
    new TimeUtils(repr)
  }
}

class TimeUtils(repr: Time) {
  def toYearOpt: Option[Int] = {
    Option(repr) map {
      time => time.getYear
    }
  }
}
