package text.kanji

/**
  * @author K.Sakamoto
  *         Created on 2016/07/26
  */
object JISLevel1to2KanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] =
    JISLevel1KanjiCharacter.kanji ++
    JISLevel2KanjiCharacter.kanji
}
