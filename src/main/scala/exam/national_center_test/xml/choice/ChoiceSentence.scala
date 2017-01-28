package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceSentence {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceSentence = {
    new ChoiceSentence(choice.singleton)
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceSentence(override val choice: String) extends Choice[String](choice)

/**
  * @author K.Sakamoto
  */
object ChoicesSentence {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesSentence = {
    new ChoicesSentence(choices map {ChoiceSentence(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesSentence(override val choices: Seq[ChoiceSentence]) extends Choices[ChoiceSentence](choices)
