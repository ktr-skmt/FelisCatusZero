package text.correction

import jeqa.types.{BoWQuery, Geography}
import org.apache.uima.jcas.JCas

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
object BoWBasedIRDocCorrector extends Corrector {
  def correct(aJCas: JCas,
              query: BoWQuery,
              beginTimeLimit: Option[Int],
              endTimeLimit: Option[Int],
              geographyLimit: Option[Geography]): Unit = {

    correct(
      aJCas,
      query.getIndriQuery,
      Map.empty[String, Seq[String]],
      beginTimeLimit,
      endTimeLimit,
      geographyLimit,
      isBoWQuery = true
    )
    query.setAlreadyFinishedCorrecting(true)
  }
}
