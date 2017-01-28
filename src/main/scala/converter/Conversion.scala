package converter

/**
 * <pre>
 * Created on 2014/11/24
 * </pre>
 * @param begin beginning code point
 * @param end ending code point
 * @param to diff
 * @author K.Sakamoto
 */
class Conversion(begin: Int, end: Int, private val to: Int) {
  def isInRange(target: Int): Boolean = {
    begin to end contains target
  }

  def notInRange(target: Int): Boolean = {
    !isInRange(target)
  }

  def diff: Int = {
    to - begin
  }
}
