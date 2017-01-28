package exam.national_center_test.xml.choice.collcetion

import text.normalizer.Normalizer
import text.{StringNone, StringOption, StringSome}
import util.StringUtils._

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @param singleton_ single choice
 * @author K.Sakamoto
 */
class ChoiceSingleton(private val singleton_ : String) extends ChoiceCollection {
  val singleton: String = removeHyphen(singleton_).map(_.replaceAllLiteratim(" ", "")) match {
    case StringSome(s) => s
    case StringNone => ""
  }

  override def toString: String = {
    singleton
  }

  private def removeHyphen(value: String): StringOption = {
    val delimiter: String = "-"
    Normalizer.normalize(StringOption(value)) map {
      nValue: String =>
        var v: String = nValue
        if (v startsWith delimiter) {
          v = value.tail.trim
        }
        if (v startsWith delimiter) {
          v = value.init.trim
        }
        v
    }
  }
}
