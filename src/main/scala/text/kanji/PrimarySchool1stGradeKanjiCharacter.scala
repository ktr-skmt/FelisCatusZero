package text.kanji

/**
  * @author K.Sakamoto
  *         Created on 2016/07/26
  */
object PrimarySchool1stGradeKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = readKanjiCSV("primary_school_1st_grade")
}
