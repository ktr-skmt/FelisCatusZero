package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceTermCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceTermCombo = {
    new ChoiceTermCombo(choice.set.mapValues(ChoiceTerm(_)))
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceTermCombo(override val choice: Map[String, ChoiceTerm]) extends ChoiceCombo[ChoiceTerm](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesTermCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesTermCombo = {
    new ChoicesTermCombo(choices map {ChoiceTermCombo(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesTermCombo(override val choices: Seq[ChoiceCombo[ChoiceTerm]]) extends ChoicesCombo[ChoiceTerm](choices)
