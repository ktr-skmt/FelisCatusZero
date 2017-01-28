package exam.national_center_test.xml

import text.StringOption
import exam.national_center_test.NationalCenterTestQuestionFormatDetector
import exam.national_center_test.xml.choice.ChoicesImpl
import exam.{Question, QuestionFormat}

import scala.xml.NodeSeq

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 * @param id id
 * @param question question xml
 * @param underLinePart underline part
 * @param sentences sentences
 * @param explanation explanation
 * @param choices choices
 * @author K.Sakamoto
 */
class SmallQuestion(val id: Int,
                    val question: NodeSeq,
                    val underLinePart: StringOption,
                    val sentences: Option[Map[String, String]],
                    val explanation: Option[NodeSeq],
                    val choices: ChoicesImpl) extends Question {
  val questionFormat: QuestionFormat.Value = {
    NationalCenterTestQuestionFormatDetector.detect(this)
  }

  override def toString: String = {
    s"""ID: $id
        |Question Format: $questionFormat
        |Question: $question
        |Underline Part: $underLinePart
        |Sentences: $sentences
        |Explanation: $explanation
        |Choices: $choices
    """.stripMargin
  }
}
