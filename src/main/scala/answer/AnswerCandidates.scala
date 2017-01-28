package answer

/**
 * <pre>
 * Created on 3/11/15.
 * </pre>
 * @param answerResults answer results
 * @author K.Sakamoto
 */
class AnswerCandidates(val answerResults: Seq[AnswerCandidate]) {
  override def toString: String = {
    val builder: StringBuilder = new StringBuilder()
    answerResults foreach {
      result: AnswerCandidate =>
        builder.append(
          "%1$s%2$s---%2$s".format(
            result,
            "\n"
          )
        )
    }
    builder.result
  }
}