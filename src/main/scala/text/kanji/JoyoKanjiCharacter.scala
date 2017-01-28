package text.kanji

/**
  * @author K.Sakamoto
  *         Created on 2016/07/26
  */
object JoyoKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = readKanjiCSV("joyo_kanji")
}
