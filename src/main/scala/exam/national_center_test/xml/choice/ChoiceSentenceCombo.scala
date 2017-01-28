package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSentenceCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceSentenceCombo = {
    new ChoiceSentenceCombo(choice.set.mapValues(ChoiceSentence(_)))
  }
}

class ChoiceSentenceCombo(override val choice: Map[String, ChoiceSentence]) extends ChoiceCombo[ChoiceSentence](choice) {

}

object ChoicesSentenceCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesSentenceCombo = {
    new ChoicesSentenceCombo(choices map {ChoiceSentenceCombo(_)})
  }
}

class ChoicesSentenceCombo(override val choices: Seq[ChoiceCombo[ChoiceSentence]]) extends ChoicesCombo[ChoiceSentence](choices) {

}
