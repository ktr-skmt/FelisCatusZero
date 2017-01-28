package exam.national_center_test.xml

import scala.xml.NodeSeq

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @param context context
 * @param smallQuestions small questions
 * @author K.Sakamoto
 */
class MiddleQuestion(val context: NodeSeq,
                     val smallQuestions: Seq[SmallQuestion]) {
  override def toString: String = {
    s"""Context: $context
       |Small Questions: $smallQuestions
    """.stripMargin
  }

}
