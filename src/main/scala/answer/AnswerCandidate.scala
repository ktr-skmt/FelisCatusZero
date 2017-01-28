package answer

import exam.CharacterCounter
import jeqa.types.Sentence
import text.StringOption

/**
 * <pre>
 * Created on 3/11/15.
 * </pre>
 * @param score score
 * @param text answer
 * @author K.Sakamoto
 */
class AnswerCandidate(val score: Double, val text: StringOption, val sentenceList: Seq[Sentence]) {
  val characterNumber: Int = CharacterCounter.count(text)
  override def toString: String = {
    """SCORE:
      |%f
      |TEXT:
      |%s
      |CHARACTER NUMBER:
      |%d
    """.stripMargin.format(
        score,
        text,
        characterNumber
      )
  }
}
