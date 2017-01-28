package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 * @param choice choice
 * @tparam T type
 * @author K.Sakamoto
 */
class Choice[T](val choice: T) extends ChoiceImpl {
  override def toString: String = {
    choice.toString
  }
}

/**
  * @author K.Sakamoto
  */
trait ChoiceImpl

/**
  * @author K.Sakamoto
  * @param choices choices
  * @tparam T type
  */
class Choices[T <: ChoiceImpl](val choices: Seq[T]) extends ChoicesImpl {
  override def size: Int = {
    choices.size
  }

  def apply(number: Int): Option[T] = {
    if ((1 to size).contains(number)) {
      Option(choices(number - 1))
    } else {
      None
    }
  }

  override def toString: String = {
    choices.toString
  }
}

/**
  * @author K.Sakamoto
  */
trait ChoicesImpl {
  def size: Int
}