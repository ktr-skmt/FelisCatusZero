package exam

/**
 * <pre>
 * Created on 5/29/15.
 * </pre>
 * @author K.Sakamoto
 */
trait QuestionFormatDetector {
  def detect(question: Question): QuestionFormat.Value
}
