package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/25/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceBinaryCombo {
  def apply(choice: collcetion.ChoiceSet): ChoiceBinaryCombo = {
    new ChoiceBinaryCombo(choice.set.mapValues(ChoiceBinary(_)))
  }
}

class ChoiceBinaryCombo(override val choice: Map[String, ChoiceBinary]) extends ChoiceCombo[ChoiceBinary](choice) {

}

object ChoicesBinaryCombo {
  def apply(choices: Seq[collcetion.ChoiceSet]): ChoicesBinaryCombo = {
    new ChoicesBinaryCombo(choices map {ChoiceBinaryCombo(_)})
  }
}

class ChoicesBinaryCombo(override val choices: Seq[ChoiceCombo[ChoiceBinary]]) extends ChoicesCombo[ChoiceBinary](choices) {

}
