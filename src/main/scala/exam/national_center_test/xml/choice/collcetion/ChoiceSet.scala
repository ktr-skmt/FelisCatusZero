package exam.national_center_test.xml.choice.collcetion

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @param set choice set
 * @author K.Sakamoto
 */
class ChoiceSet(val set: Map[String, ChoiceSingleton]) extends ChoiceCollection {
  override def toString: String = {
    val buffer: StringBuilder = new StringBuilder()
    set.toSeq.sortWith((a, b) => a._1 < b._1) foreach {
      case (key, value) =>
        buffer.append(s"$key: $value\n")
    }
    buffer.result
  }
}
