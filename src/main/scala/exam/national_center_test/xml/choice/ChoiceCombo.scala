package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 * @param choice choice
 * @tparam T type
 * @author K.Sakamoto
 */
class ChoiceCombo[T <: ChoiceImpl](override val choice: Map[String, T]) extends Choice[Map[String, T]](choice)

/**
  * @author K.Sakamoto
  * @param choices choices
  * @tparam T type
  */
class ChoicesCombo[T <: ChoiceImpl](override val choices: Seq[ChoiceCombo[T]]) extends Choices[ChoiceCombo[T]](choices)
