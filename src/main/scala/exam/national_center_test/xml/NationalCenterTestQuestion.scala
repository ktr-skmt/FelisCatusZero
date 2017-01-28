package exam.national_center_test.xml

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @param year year
 * @param questions questions
 * @author K.Sakamoto
 */
class NationalCenterTestQuestion(val year: Int,
                                 val questions: Seq[BigQuestion]) {
  override def toString: String = {
    s"""Year: $year
       |Big Questions: $questions
     """.stripMargin
  }
}
