package uima.cpe

import org.kohsuke.args4j.Option

import scala.beans.BeanProperty

/**
  * <pre>
  * Created on 2016/12/18.
  * </pre>
  *
  * @author K.Sakamoto
  */
class CPERunnerOption() {
  @Option(name = "-doJCasGen", aliases = Array[String]("--generateJCas"), usage = "do JCasGen as pre-processing", required = false)
  @BeanProperty
  var doJCasGen: Boolean = false

  @Option(name = "-doCharacterLevelIndriIndex", aliases = Array[String]("--generateCharacterLevelIndriIndex"), usage = "do IndriIndex with character as pre-processing", required = false)
  @BeanProperty
  var doCharacterLevelIndriIndex: Boolean = false

  @Option(name = "-doWordLevelIndriIndex", aliases = Array[String]("--generateWordLevelIndriIndex"), usage = "do IndriIndex with word as pre-processing", required = false)
  @BeanProperty
  var doWordLevelIndriIndex: Boolean = false

  @Option(name = "-doFastText", aliases = Array[String]("--generateFastTextModel"), usage = "do fastText as pre-processing", required = false)
  @BeanProperty
  var doFastText: Boolean = false

  @Option(name = "-from", aliases = Array[String]("--startPoint"), usage = "start point: cr (EssayQuestionReader) = default, qa (QuestionAnalyzer), ir (InformationRetriever), eg (EssayGenerator), w (EssayWriter), e (EssayEvaluator)", required = false)
  @BeanProperty
  var startPoint: String = "cr"

  @Option(name = "-to", aliases = Array[String]("--endPoint"), usage = "end point: cr (EssayQuestionReader), qa (QuestionAnalyzer), ir (InformationRetriever), eg (EssayGenerator), w (EssayWriter), e (EssayEvaluator) = default", required = false)
  @BeanProperty
  var endPoint: String = "e"

  @Option(name = "-unSave", aliases = Array[String]("--unSaveIntermediateState"), usage = "unsave intermediate state. If you want to unsave all state, use '-unSave=all' or '-unSave=qa,ir,eg'. If you want to unsave the qa state, use '-unSave=qa'. If you want to unsave the qa and eg states, use '-unSave=qa,eg'.", required = false)
  @BeanProperty
  var unSave: String = ""
}
