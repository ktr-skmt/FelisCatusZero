package exam.national_center_test

import exam.national_center_test.xml.SmallQuestion
import exam.national_center_test.xml.choice._
import exam.{Question, QuestionFormat, QuestionFormatDetector}

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
object NationalCenterTestQuestionFormatDetector extends QuestionFormatDetector {
  override def detect(question: Question): QuestionFormat.Value = {
    question match {
      case smallQuestion: SmallQuestion =>
        detect(smallQuestion)
      case _ =>
        QuestionFormat.Other
    }
  }

  private def detect(smallQuestion: SmallQuestion): QuestionFormat.Value = {
    val choices: ChoicesImpl = smallQuestion.choices

    def judgeChoiceTerm: QuestionFormat.Value = {
      if (smallQuestion.question.text.contains("空欄") ||
        smallQuestion.question.text.contains("入れる") ||
        smallQuestion.question.text.contains("gap marked") ||
        smallQuestion.question.text.contains("insert")) {
        QuestionFormat.SlotFilling
      } else {
        QuestionFormat.Factoid
      }
    }

    if (
      choices.isInstanceOf[ChoicesBinary] |
      choices.isInstanceOf[ChoicesBinaryCombo]) {
      QuestionFormat.TrueFalseCombo
    } else if (
      choices.isInstanceOf[ChoicesImage] |
      choices.isInstanceOf[ChoicesImageCombo]) {
      QuestionFormat.Geography
    } else if (
      choices.isInstanceOf[ChoicesSentence] |
      choices.isInstanceOf[ChoicesSentenceCombo]) {
      QuestionFormat.TrueFalse
    } else if (
      choices.isInstanceOf[ChoicesSymbol] |
      choices.isInstanceOf[ChoicesSymbolCombo]) {
      QuestionFormat.Image
    } else if (
      choices.isInstanceOf[ChoicesSymbolList] |
      choices.isInstanceOf[ChoicesSymbolListCombo]) {
      QuestionFormat.Time
    } else if (
      choices.isInstanceOf[ChoicesTerm] |
      choices.isInstanceOf[ChoicesTermCombo]) {
      judgeChoiceTerm
    } else {
      QuestionFormat.Other
    }
  }
}
