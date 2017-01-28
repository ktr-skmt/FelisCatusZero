package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceImageCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceImageCombo = {
    new ChoiceImageCombo(choice.set.mapValues(ChoiceImage(_)))
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceImageCombo(override val choice: Map[String, ChoiceImage]) extends ChoiceCombo[ChoiceImage](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesImageCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesImageCombo = {
    new ChoicesImageCombo(choices map {ChoiceImageCombo(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesImageCombo(override val choices: Seq[ChoiceCombo[ChoiceImage]]) extends ChoicesCombo[ChoiceImage](choices)
