package text

import text.normalizer.WordExpressionNormalizer
import util.StringUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * <pre>
  * Created on 2017/01/10.
  * </pre>
  *
  * @author K.Sakamoto
  */
object TermExpander {
  private val expansions: Seq[Seq[String]] = {
    val map: mutable.Map[String, ListBuffer[String]] = mutable.Map.empty[String, ListBuffer[String]]
    WordExpressionNormalizer.getTerms foreach {
      case (source, target) =>
        if (!map.contains(target)) {
          map(target) = ListBuffer.empty[String]
        }
        map(target) += source
      case _ =>
      // Do nothing
    }
    val buffer: ListBuffer[Seq[String]] = ListBuffer.empty[Seq[String]]
    map.toMap[String, ListBuffer[String]] foreach {
      case (term, expansions) =>
        expansions += term
        buffer += expansions.result
    }
    buffer.result
  }


  def expand(termOpt: StringOption): Seq[(String, Boolean)] = {
    if (termOpt.isEmpty) {
      return Nil
    }

    val term: String = termOpt.get

    val expansionBuffer: ListBuffer[(String, Boolean)] = ListBuffer.empty[(String, Boolean)]
    expansions foreach {
      keywordExpanders: Seq[String] =>
        keywordExpanders foreach {
          keywordExpander: String =>
            (keywordExpanders diff keywordExpander) foreach {
              case k: String if !k.contains(keywordExpander) =>
                expansionBuffer += ((term.replaceAllLiteratim(keywordExpander, k), true))
              case _ =>
              // Do nothing
            }
        }
    }

    expansionBuffer.distinct.result
  }
}
