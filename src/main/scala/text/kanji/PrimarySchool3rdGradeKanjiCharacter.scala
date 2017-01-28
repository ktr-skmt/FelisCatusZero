package text.kanji

/**
  * @author K.Sakamoto
  *         Created on 2016/07/26
  */
object PrimarySchool3rdGradeKanjiCharacter extends KanjiCharacter {
  override val kanji: Seq[String] = readKanjiCSV("primary_school_3rd_grade")
}
