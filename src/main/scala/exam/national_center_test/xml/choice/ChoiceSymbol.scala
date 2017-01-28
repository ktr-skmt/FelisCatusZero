package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSymbol {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceSymbol = {
    new ChoiceSymbol(choice.singleton)
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceSymbol(override val choice: String) extends Choice[String](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesSymbol {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesSymbol = {
    new ChoicesSymbol(choices map {ChoiceSymbol(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesSymbol(override val choices: Seq[ChoiceSymbol]) extends Choices[ChoiceSymbol](choices)