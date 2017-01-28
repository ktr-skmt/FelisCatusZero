package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSymbolCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceSymbolCombo = {
    new ChoiceSymbolCombo(choice.set.mapValues(ChoiceSymbol(_)))
  }
}

class ChoiceSymbolCombo(override val choice: Map[String, ChoiceSymbol]) extends ChoiceCombo[ChoiceSymbol](choice) {

}

object ChoicesSymbolCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesSymbolCombo = {
    new ChoicesSymbolCombo(choices map {ChoiceSymbolCombo(_)})
  }
}

class ChoicesSymbolCombo(override val choices: Seq[ChoiceCombo[ChoiceSymbol]]) extends ChoicesCombo[ChoiceSymbol](choices) {

}
