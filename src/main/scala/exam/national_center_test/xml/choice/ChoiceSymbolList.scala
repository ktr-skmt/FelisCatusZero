package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSymbolList {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceSymbolList = {
    new ChoiceSymbolList(SymbolListParser.parse(choice.singleton))
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceSymbolList(override val choice: Seq[String]) extends Choice[Seq[String]](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesSymbolList {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesSymbolList = {
    new ChoicesSymbolList(choices map {ChoiceSymbolList(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesSymbolList(override val choices: Seq[ChoiceSymbolList]) extends Choices[ChoiceSymbolList](choices)