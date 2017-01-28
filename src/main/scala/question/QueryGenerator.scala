package question

import jeqa.types._
import org.apache.uima.jcas.JCas
import text.analyzer.SentenceQuotationParser
import text.{StringNone, StringOption, StringSome, TermExpander}
import util.uima.FSListUtils._
import util.uima.SeqUtils._
import util.uima.StringListUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex

/**
  * @author K.Sakamoto
  *         Created on 2016/12/05
  */
object QueryGenerator {
  //private lazy val KATAKANA_KANJI: Regex = raw"""(\p{InKatakana}+)(${JISLevel1to4KanjiCharacter.pattern}+)""".r
  private lazy val KATAKANA_KANJI: Regex = raw"""(\p{script=Katakana}+)(\p{script=Han}+)""".r
  private lazy val DELIMITER_LIST: Seq[String] = Seq[String](
    "\u003D",//=
    "\uFF65",//・
    "\uFE66"//＝
  )
  private lazy val DELIMITER_REGEX: String = DELIMITER_LIST.mkString("[", "", "]")
  private lazy val PARTICLE_LIST: Seq[String] = Seq[String](
    "の",
    "が",
    "を",
    "に",
    "で"
  )
  private type Quotation = (String, String)

  private def expand(keywordOpt: StringOption): Seq[(String, Boolean)] = {
    val expansionBuffer: ListBuffer[(String, Boolean)] = ListBuffer.empty[(String, Boolean)]
    keywordOpt match {
      case StringSome(keyword) =>
        expansionBuffer += ((keyword, true))
        keyword match {
          case KATAKANA_KANJI(katakana, kanji) =>
            expansionBuffer += ((katakana, false))
            PARTICLE_LIST foreach {
              particle: String =>
                expansionBuffer += ((s"""$katakana$particle$kanji""", false))
            }
          case _ =>
            // Do nothing
        }
        DELIMITER_LIST foreach {
          delimiter: String =>
            val tmp: String = keyword.replaceAll(DELIMITER_REGEX, delimiter)
            if (keyword != tmp) {
              expansionBuffer += ((tmp, true))
            }
        }
        var keywordTmp: String = keyword
        var quotedSequenceOpt: Option[(Quotation, Range)] = SentenceQuotationParser.getFirstMatchOpt(StringOption(keywordTmp))
        while (quotedSequenceOpt.nonEmpty) {
          val quotedSequence: (Quotation, Range) = quotedSequenceOpt.get
          val range: Range = quotedSequence._2
          expansionBuffer += ((keywordTmp.substring(range.start + 1, range.end - 1), true))
          keywordTmp = keywordTmp.substring(0, range.start) concat keywordTmp.substring(range.end, keywordTmp.length)
          quotedSequenceOpt = SentenceQuotationParser.getFirstMatchOpt(StringOption(keywordTmp))
        }
        expansionBuffer += ((keywordTmp, true))
        expansionBuffer ++= TermExpander.expand(keywordOpt)
      case StringNone =>
        // Do nothing
    }
    expansionBuffer.result.distinct
  }

  private val keywordQueryCache: mutable.Map[String, KeywordQuery] = mutable.Map.empty[String, KeywordQuery]
  private val bowQueryCache: mutable.Map[String, BoWQuery] = mutable.Map.empty[String, BoWQuery]

  def generate(aJCas: JCas, question: Question): Seq[Query] = {
    val queryBuffer: ListBuffer[Query] = ListBuffer.empty[Query]

    val keywordList: Seq[Keyword] = question.getKeywordSet.toSeq.asInstanceOf[Seq[Keyword]]

    //mandatory keywords
    keywordList foreach {
      keyword: Keyword =>
        StringOption(keyword.getText) match {
          case StringSome(keywordText) =>
            if (keywordQueryCache.contains(keywordText)) {
              queryBuffer += keywordQueryCache(keywordText)
            } else {
              val query: KeywordQuery = new KeywordQuery(aJCas)
              query.addToIndexes()
              val expansionBuffer: ListBuffer[KeywordExpansion] = ListBuffer.empty[KeywordExpansion]
              expand(StringOption(keywordText)) foreach {
                case (expansionText, isMandatory) =>
                  val expansion: KeywordExpansion = new KeywordExpansion(aJCas)
                  expansion.addToIndexes()
                  expansion.setText(expansionText)
                  expansion.setIsMandatory(isMandatory)
                  expansionBuffer += expansion
                case _ =>
                // Do nothing
              }
              keyword.setExpansionSet(expansionBuffer.result.toFSList)
              query.setKeyword(keyword)
              query.setAlreadyFinishedRetrieving(false)
              query.setAlreadyFinishedCorrecting(false)
              //query.setKnowledgeSourceList(KnowledgeSourceSelector.select(true).toStringList)
              keywordQueryCache(keywordText) = query
              queryBuffer += query
            }
          case StringNone =>
            // Do nothing
        }
    }

    //optional keywords
    if (keywordList.isEmpty) {
      val contentWordList: Seq[String] = question.getDocument.getContentWordList.toSeq.sorted
      val indriQuery: String = contentWordList.mkString("", " ", "")
      if (bowQueryCache.contains(indriQuery)) {
        queryBuffer += bowQueryCache(indriQuery)
      } else {
        val query: BoWQuery = new BoWQuery(aJCas)
        query.addToIndexes()
        val keyword: Keyword = new Keyword(aJCas)
        keyword.addToIndexes()
        keyword.setText(indriQuery)
        query.setIndriQuery(keyword)
        query.setAlgorithm("BM25")
        query.setAlreadyFinishedRetrieving(false)
        query.setAlreadyFinishedCorrecting(false)
        //query.setKnowledgeSourceList(KnowledgeSourceSelector.select(false).toStringList)
        bowQueryCache(indriQuery) = query
        queryBuffer += query
      }
    }

    queryBuffer.result
  }
}
