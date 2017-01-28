package exam.national_center_test.xml.choice

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @author K.Sakamoto
 */
object SymbolListParser {
  val delimiter: String = "→"

  def parse(choice: String): Seq[String] = {
    choice split delimiter
  }
}
