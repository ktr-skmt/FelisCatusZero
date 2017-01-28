package text.correction

import ir.fulltext.indri.TrecText
import jeqa.types._
import org.apache.uima.jcas.JCas
import text.normalizer.SentenceNormalizer
import text.{StringOption, StringSome}
import time.{TimeExtractorForWorldHistory, TimeExtractorFromPreviousParagraphInTextbook, TimeTmp}
import uima.ae.DocumentAnnotator
import util.Config
import util.uima.FSListUtils._
import util.uima.JCasUtils
import util.uima.SeqStringUtils._
import util.uima.SeqUtils._
import util.uima.TimeUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
trait Corrector extends DocumentAnnotator {
  protected val mYamakawaWorldHistoryGlossaryDocnoPrefix: String = "YamakawaWorldHistoryGlossary"
  def correct(aJCas: JCas,
              keyword: Keyword,
              keywordCorrectionMap: Map[String, Seq[String]],
              beginTimeLimit: Option[Int],
              endTimeLimit: Option[Int],
              geographyLimit: Option[Geography],
              isBoWQuery: Boolean): Unit = {
    JCasUtils.setAJCasOpt(Option(aJCas))
    val keywordOriginalText: String = keyword.getText
    //sentenceText, sentence
    val sentenceMap: mutable.Map[String, Sentence] = mutable.Map.empty[String, Sentence]
    //passageText, passage
    val passageMap: mutable.Map[String, Passage] = mutable.Map.empty[String, Passage]

    val documentBuffer: ListBuffer[Document] = ListBuffer.empty[Document]

    keyword.getDocumentSet.toArray foreach {
      documentTmp =>
        val document: Document = documentTmp.asInstanceOf[Document]
        val keywordBuffer: ListBuffer[String] = ListBuffer.empty[String]

        keywordCorrectionMap foreach {
          case (k, expansions) =>
            val expansionList: Seq[String] = expansions.filterNot(expansion => StringOption(expansion).isEmpty).distinct
            val text: String = TrecText.correct(document.getText, StringOption(k), expansionList)
            document.setText(text)
            keywordBuffer += k
            keywordBuffer ++= expansionList
        }

        //println("start")
        //println(keyword.getText)
        //println(document.getText)
        annotate(aJCas, document, keywordBuffer.result.filterNot(expansion => StringOption(expansion).isEmpty).distinct)
        //println("annotated")

        //先頭の文の時間解析
        val sentenceList: Seq[Sentence] = document.getSentenceSet.toSeq.asInstanceOf[Seq[Sentence]]
        val sentenceArray: Array[Sentence] = sentenceList.toArray

        val initialSentence4Doc: Sentence = sentenceArray.head
        if (hasTimeRange(Option(initialSentence4Doc.getBeginTime), Option(initialSentence4Doc.getEndTime))) {
          // titleをみる。
          // titleを見てもわからないなら、教科書なら前のdocumentのtextを後方から文単位でみて時間情報が出てきたらそれに入れ替える。用語集なら諦める
          // 教科書で前のdocumentのtextを文単位で後方から見てもわからない場合は前のdocumentのtitleをみる。それでダメなら諦める。

          val timeInDocTitle: TimeTmp = TimeExtractorForWorldHistory.extractUnionTime(StringOption(document.getTitle))
          if (timeInDocTitle.beginTime.nonEmpty || timeInDocTitle.endTime.nonEmpty) {
            timeInDocTitle.beginTime match {
              case Some(beginTime) =>
                val beginTimeType: Time = new Time(aJCas)
                beginTimeType.addToIndexes()
                beginTimeType.setYear(beginTime)
                beginTimeType.setTextList(timeInDocTitle.beginTimeTextList.toStringList)
                initialSentence4Doc.setBeginTime(beginTimeType)
                Option(document.getBeginTime) match {
                  case Some(bt) =>
                    Option(bt.getYear) match {
                      case Some(beginTime4Doc) =>
                        if (beginTime < beginTime4Doc) {
                          document.setBeginTime(beginTimeType)
                        }
                      case None =>
                        document.setBeginTime(beginTimeType)
                    }
                  case None =>
                    document.setBeginTime(beginTimeType)
                }
              case None =>
              // Do nothing
            }

            timeInDocTitle.endTime match {
              case Some(endTime) =>
                val endTimeType: Time = new Time(aJCas)
                endTimeType.addToIndexes()
                endTimeType.setYear(endTime)
                endTimeType.setTextList(timeInDocTitle.endTimeTextList.toStringList)
                initialSentence4Doc.setEndTime(endTimeType)
                Option(document.getEndTime) match {
                  case Some(et) =>
                    Option(et.getYear) match {
                      case Some(endTime4Doc) =>
                        if (endTime4Doc < endTime) {
                          document.setEndTime(endTimeType)
                        }
                      case None =>
                        document.setEndTime(endTimeType)
                    }
                  case None =>
                    document.setEndTime(endTimeType)
                }
              case None =>
              // Do nothing
            }
          } else if (Config.needInitialSentenceTimeAnalysisByTextbook) {
            val docnoOpt: StringOption = StringOption(document.getDocno)
            docnoOpt match {
              case StringSome(docno) if !docno.startsWith(mYamakawaWorldHistoryGlossaryDocnoPrefix) =>
                val timeTmp: TimeTmp = TimeExtractorFromPreviousParagraphInTextbook.extractUnionTime(docnoOpt)
                if (timeTmp.beginTime.nonEmpty || timeTmp.endTime.nonEmpty) {
                  timeTmp.beginTime match {
                    case Some(beginTime) =>
                      val beginTimeType: Time = new Time(aJCas)
                      beginTimeType.addToIndexes()
                      beginTimeType.setYear(beginTime)
                      beginTimeType.setTextList(timeTmp.beginTimeTextList.toStringList)
                      initialSentence4Doc.setBeginTime(beginTimeType)
                      Option(document.getBeginTime) match {
                        case Some(bt) =>
                          Option(bt.getYear) match {
                            case Some(beginTime4Doc) =>
                              if (beginTime < beginTime4Doc) {
                                document.setBeginTime(beginTimeType)
                              }
                            case None =>
                              document.setBeginTime(beginTimeType)
                          }
                        case None =>
                          document.setBeginTime(beginTimeType)
                      }
                    case None =>
                    // Do nothing
                  }
                  timeTmp.endTime match {
                    case Some(endTime) =>
                      val endTimeType: Time = new Time(aJCas)
                      endTimeType.addToIndexes()
                      endTimeType.setYear(endTime)
                      endTimeType.setTextList(timeTmp.endTimeTextList.toStringList)
                      initialSentence4Doc.setEndTime(endTimeType)
                      Option(document.getEndTime) match {
                        case Some(et) =>
                          Option(et.getYear) match {
                            case Some(endTime4Doc) =>
                              if (endTime4Doc < endTime) {
                                document.setEndTime(endTimeType)
                              }
                            case None =>
                              document.setEndTime(endTimeType)
                          }
                        case None =>
                          document.setEndTime(endTimeType)
                      }
                    case None =>
                    // Do nothing
                  }
                }
              case _ =>
              // Do nothing
            }
          }
        }

        //document内のsentenceの文脈的な時間解析
        for (k <- 1 until sentenceArray.length) {
          val sentence: Sentence = sentenceArray(k)
          if (hasTimeRange(Option(sentence.getBeginTime), Option(sentence.getEndTime))) {
            val previousSentence: Sentence = sentenceArray(k - 1)
            sentence.setBeginTime(previousSentence.getBeginTime)
            sentence.setEndTime(previousSentence.getEndTime)
          }
        }

        val passageList: Seq[Passage] = document.getPassageSet.toSeq.asInstanceOf[Seq[Passage]]
        if (Config.usePassage) {
          //passageが時間情報を持っていなかったときは、先頭の文が「先頭の文の時間解析」で更新されているかもしれないので、それに入れ替える。
          passageList foreach {
            case passage: Passage if hasTimeRange(Option(passage.getBeginTime), Option(passage.getEndTime)) =>
              val initialSentence4Passage: Sentence = passage.getSentenceSet(0)
              passage.setBeginTime(initialSentence4Passage.getBeginTime)
              passage.setEndTime(initialSentence4Passage.getEndTime)
            case _ =>
            // Do nothing
          }
        }

        if (!(
          needToRemoveByTime(document, beginTimeLimit, endTimeLimit) ||
            needToRemoveByGeography(document, geographyLimit))) {
          documentBuffer += document

          sentenceList foreach {
            sentence: Sentence =>
              val sentenceText: String = SentenceNormalizer.normalize(StringOption(sentence.getOriginalText)).get
              sentence.setText {
                if (document.getDocno.startsWith(mYamakawaWorldHistoryGlossaryDocnoPrefix)) {
                  raw"${document.getTitle.split(',').head.trim}は、$sentenceText"
                } else {
                  sentenceText
                }
              }
              if (!(
                needToRemoveByTime(sentence, beginTimeLimit, endTimeLimit) ||
                  needToRemoveByGeography(sentence, geographyLimit))) {
                val text: String = sentence.getText
                if (!(sentenceMap contains text)) {
                  //Keyword queryならkeywordを含む
                  if ((text contains keywordOriginalText) || isBoWQuery) {
                    sentence.setText(text)
                    sentenceMap(text) = sentence
                  }
                }
              }
          }
          if (Config.usePassage) {
            passageList foreach {
              passage: Passage =>
                if (!(
                  needToRemoveByTime(passage, beginTimeLimit, endTimeLimit) ||
                    needToRemoveByGeography(passage, geographyLimit))) {
                  val text: String = passage.getText
                  if ((text contains keywordOriginalText) && !(passageMap contains text)) {
                    passage.setText(text)
                    passageMap(text) = passage
                  }
                }
            }
          }
        }

        keyword.setDocumentSet(documentBuffer.result.toFSArray)
        keyword.setSentenceSet(sentenceMap.values.toSeq.toFSList)
        if (Config.usePassage) {
          keyword.setPassageSet(passageMap.values.toSeq.toFSList)
        }
    }
  }


  private def hasTimeRange(beginTimeOpt: Option[Time], endTimeOpt: Option[Time]): Boolean = {
    (beginTimeOpt.isEmpty || Option(beginTimeOpt.get.getYear).isEmpty) &&
      (endTimeOpt.isEmpty || Option(endTimeOpt.get.getYear).isEmpty)
  }

  private def needToRemoveByTime(textAnnotation: TextAnnotation,
                                 beginTimeLimitOpt: Option[Int],
                                 endTimeLimitOpt: Option[Int]): Boolean = {
    if (beginTimeLimitOpt.isEmpty && endTimeLimitOpt.isEmpty) {
      return false
    }
    val beginTimeOpt: Option[Int] = textAnnotation.getBeginTime.toYearOpt
    val endTimeOpt: Option[Int] = textAnnotation.getEndTime.toYearOpt
    beginTimeOpt match {
      case Some(beginTime) =>
        endTimeLimitOpt match {
          case Some(endTimeLimit) if endTimeLimit < beginTime =>
            return true
          case _ =>
          // Do nothing
        }
      case None =>
      // Do nothing
    }
    endTimeOpt match {
      case Some(endTime) =>
        beginTimeLimitOpt match {
          case Some(beginTimeLimit) if endTime < beginTimeLimit =>
            return true
          case _ =>
          // Do nothing
        }
      case None =>
      // Do nothing
    }
    false
  }

  //TODO:
  private def needToRemoveByGeography(textAnnotation: TextAnnotation, geographyLimit: Option[Geography]): Boolean = {
    false
  }
}
