package exam

import text.{StringNone, StringOption, StringSome}

import scala.util.matching.Regex.Match

/**
 * <pre>
 * Created on 11/19/14.
 * </pre>
 * @author K.Sakamoto
 */
object CharacterCounter {
  def count(text: StringOption): Int = {
    text.codePointCount - surplusForNumberSequence(text)
  }

  private def surplusForNumberSequence(text: StringOption): Int = {
    text match {
      case StringSome(t) =>
        var count: Int = 0
        "[0-9]{2,}".r.findAllMatchIn(t) foreach {
          m: Match =>
            count += m.group(0).length
        }
        (count + 1) / 2
      case StringNone => 0
    }
  }
}
