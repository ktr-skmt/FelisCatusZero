package exam

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
object QuestionFormat extends Enumeration {
  val Other,
      TrueFalse,
      TrueFalseCombo,
      Factoid,
      SlotFilling,
      Time,
      Image,
      Geography,
      EssayWithoutKeywords,
      EssayWithKeywords = Value

  def toString(questionFormat: QuestionFormat.Value): String = {
      questionFormat match {
          case TrueFalse =>
              "True-or-False"
          case TrueFalseCombo =>
              "True-or-False(Combo)"
          case Factoid =>
              "Factoid"
          case SlotFilling =>
              "Slot-Filling"
          case Time =>
              "Time"
          case Image =>
              "Image"
          case Geography =>
              "Geography"
          case EssayWithoutKeywords =>
              "Essay without Keywords"
          case EssayWithKeywords =>
              "Essay with Keywords"
          case _ =>
              "Unique"
      }
  }
}
