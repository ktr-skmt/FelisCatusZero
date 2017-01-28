package converter

/**
 * <pre>
 * Created on 2014/11/24
 * </pre>
 * @version 1.0
 * @author K.Sakamoto
 */
object Conversions {
  case object FROM_KATAKANA_TO_HIRAGANA extends Conversions(Seq[Conversion](
    new Conversion('\u30A1', '\u30F6', '\u3041'),//'ァ', 'ヶ', 'ぁ'
    new Conversion('\u30FD', '\u30FE', '\u309D') //'ヽ', 'ヾ', 'ゝ'
  ))
  case object FROM_HIRAGANA_TO_KATAKANA extends Conversions(Seq[Conversion](
    new Conversion('\u3041', '\u3096', '\u30A1'),//'ぁ', 'ゖ', 'ァ'
    new Conversion('\u309D', '\u309E', '\u30FD') //'ゝ', 'ゞ', 'ヽ'
  ))

  val values: Array[Conversions] = Array[Conversions](
    FROM_KATAKANA_TO_HIRAGANA,
    FROM_HIRAGANA_TO_KATAKANA
  )
}

/**
  * @param conversions conversions
  * @author K.Sakamoto
  */
sealed abstract class Conversions(conversions: Seq[Conversion]) {
  val seq: Seq[Conversion] = conversions
}