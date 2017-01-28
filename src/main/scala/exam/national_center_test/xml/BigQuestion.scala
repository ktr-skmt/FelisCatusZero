package exam.national_center_test.xml

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 * @param question question
 * @param middleQuestions middle questions
 * @author K.Sakamoto
 */
class BigQuestion(val question: String,
                  val middleQuestions: Seq[MiddleQuestion]) {
  override def toString: String = {
    s"""Instruction: $question
       |Middle Questions: $middleQuestions
     """.stripMargin
  }
}
