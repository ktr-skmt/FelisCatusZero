package util.uima

import org.apache.uima.jcas.JCas

import scala.beans.BeanProperty

/**
  * @author K.Sakamoto
  *         Created on 2016/09/23
  */
object JCasUtils extends JCasUtils(None)

/**
  * @author K.Sakamoto
  * @param aJCasOpt jCas option
  */
class JCasUtils(@BeanProperty var aJCasOpt: Option[JCas] = None)

/**
  * @author K.Sakamoto
  */
trait UimaUtilsTrait {
  protected val aJCas: JCas = JCasUtils.getAJCasOpt match {
    case Some(cas) =>
      cas
    case None =>
      throw new Exception()
  }
}