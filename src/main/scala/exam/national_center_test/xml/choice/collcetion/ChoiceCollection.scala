package exam.national_center_test.xml.choice.collcetion

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @author K.Sakamoto
 */
trait ChoiceCollection {
  def choiceCollectionType: ChoiceCollectionType.Value = {
    this match {
      case _: ChoiceSet =>
        ChoiceCollectionType.Set
      case _: ChoiceSingleton =>
        ChoiceCollectionType.Singleton
      case _ =>
        ChoiceCollectionType.None
    }
  }
}

/**
  * @author K.Sakamoto
  */
object ChoiceCollectionType extends Enumeration {
  val None, Set, Singleton = Value
}