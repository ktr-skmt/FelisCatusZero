package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSymbolListCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceSymbolListCombo = {
    new ChoiceSymbolListCombo(choice.set.mapValues(ChoiceSymbolList(_)))
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceSymbolListCombo(override val choice: Map[String, ChoiceSymbolList]) extends ChoiceCombo[ChoiceSymbolList](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesSymbolListCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesSymbolListCombo = {
    new ChoicesSymbolListCombo(choices map {ChoiceSymbolListCombo(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesSymbolListCombo(override val choices: Seq[ChoiceCombo[ChoiceSymbolList]]) extends ChoicesCombo[ChoiceSymbolList](choices)
