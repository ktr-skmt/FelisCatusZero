package ir.fulltext.indri

import text.StringOption

/**
 * <pre>
 * Created on 2014/11/11
 * </pre>
 * @param text text
 * @param docno document number
 * @param title title
 * @param score score
 * @author K.Sakamoto
 */
class IndriResult(val text: StringOption,
                  val docno: StringOption,
                  val title: StringOption,
                  val score: Double) {
  override def toString: String = {
    """DOCNO: %s
      |TEXT : %s
      |TITLE: %s
      |Score: %f
      |""".stripMargin.format(
        docno,
        text,
        title,
        score
      )
  }
}