package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceTerm {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceTerm = {
    new ChoiceTerm(choice.singleton)
  }
}

class ChoiceTerm(override val choice: String) extends Choice[String](choice) {

}

object ChoicesTerm {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesTerm = {
    new ChoicesTerm(choices map {ChoiceTerm(_)})
  }
}

class ChoicesTerm(override val choices: Seq[ChoiceTerm]) extends Choices[ChoiceTerm](choices) {

}
