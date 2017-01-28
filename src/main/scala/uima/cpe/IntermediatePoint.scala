package uima.cpe

/**
  * <pre>
  * Created on 2016/12/18.
  * </pre>
  *
  * @author K.Sakamoto
  */
object IntermediatePoint {
  case object EssayQuestionReader extends IntermediatePoint(0)
  case object QuestionAnalyzer extends IntermediatePoint(1)
  case object InformationRetriever extends IntermediatePoint(2)
  case object EssayGenerator extends IntermediatePoint(3)
  case object EssayWriter extends IntermediatePoint(4)
  case object EssayEvaluator extends IntermediatePoint(5)
}

sealed abstract class IntermediatePoint(val id: Int)
