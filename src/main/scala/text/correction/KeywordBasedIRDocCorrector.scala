package text.correction

import jeqa.types._
import org.apache.uima.jcas.JCas

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
object KeywordBasedIRDocCorrector extends Corrector {
  def correct(aJCas: JCas,
    query: KeywordQuery,
    keywordCorrectionMap: Map[String, Seq[String]],
    beginTimeLimit: Option[Int],
    endTimeLimit: Option[Int],
    geographyLimit: Option[Geography]): Unit = {

    correct(
      aJCas,
      query.getKeyword,
      keywordCorrectionMap,
      beginTimeLimit,
      endTimeLimit,
      geographyLimit,
      isBoWQuery = false
    )
    query.setAlreadyFinishedCorrecting(true)
  }
}
