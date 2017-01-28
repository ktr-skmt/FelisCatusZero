package exam.national_center_test

import exam.national_center_test.xml.NationalCenterTestParser

/**
 * <pre>
 * Created on 7/17/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object Main {
  def main(args: Array[String]): Unit = {
    val parser: NationalCenterTestParser = new NationalCenterTestParser()
    parser.parse(1997) match {
      case Some(nct) =>
        //println(nct)
        nct.questions foreach {
          _.middleQuestions foreach {
            _.smallQuestions foreach {
              sq =>
              //println(sq)
            }
          }
        }
      case None =>
    }
  }
}
