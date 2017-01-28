package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceBinary {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceBinary = {
    new ChoiceBinary(choice.singleton == "正")
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceBinary(override val choice: Boolean) extends Choice[Boolean](choice) {

}

/**
  * @author K.Sakamoto
  */
object ChoicesBinary {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesBinary = {
    new ChoicesBinary(choices map {ChoiceBinary(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesBinary(override val choices: Seq[ChoiceBinary]) extends Choices[ChoiceBinary](choices) {

}
