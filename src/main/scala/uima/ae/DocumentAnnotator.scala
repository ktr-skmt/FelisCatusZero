package uima.ae

import java.util.regex.{Matcher, Pattern}

import jeqa.types._
import jeqa.types.ja.{Morpheme, MorphemeAnalysis}
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import text.analyzer.JapaneseSentenceSplitter
import text.{StringNone, StringOption, StringSome}
import time.{TimeExtractorForWorldHistory, TimeMerger, TimeTmp}
import util.Config
import util.uima.ArrayUtils._
import util.uima.FSListUtils._
import util.uima.JCasUtils
import util.uima.SeqStringUtils._
import util.uima.SeqUtils._
import util.uima.StringListUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
  * @author K.Sakamoto
  *         Created on 2016/11/30
  */
trait DocumentAnnotator extends DocumentAnalyzer {
  private def indexOfMorpheme(list: Seq[Morpheme], morpheme: Morpheme): Int = {
    for (i <- list.indices) {
      list(i) match {
        case m if m.getOriginalText == morpheme.getOriginalText &&
          m.getText == morpheme.getText &&
          m.getPos == morpheme.getPos =>
          return i
        case _ =>
        // Do nothing
      }
    }
    -1
  }

  private def annotatePassage(aJCas: JCas, sentenceList: Seq[Sentence], document: Document, scoreArrayOpt: Option[FSArray], keywordSet: Seq[String]): Unit = {
    val sentencePassageList: mutable.Map[Sentence, ListBuffer[Passage]] = mutable.Map.empty[Sentence, ListBuffer[Passage]]
    val passageBuffer: ListBuffer[Passage] = ListBuffer.empty[Passage]
    val sentenceTypeListSize: Int = sentenceList.size
    val passageWindow: Int = Config.passageWindow
    for (i <- 0 to sentenceTypeListSize - passageWindow) {
      val passage: Passage = new Passage(aJCas)
      passage.addToIndexes()

      val contentWordList4Passage: ListBuffer[String] = ListBuffer.empty[String]
      val morphemeList4Passage: ListBuffer[Morpheme] = ListBuffer.empty[Morpheme]

      scoreArrayOpt match {
        case Some(scoreArray) =>
          passage.setScoreList(scoreArray)
        case None =>
        // Do nothing
      }

      val geographyBuffer4Passage: ListBuffer[String] = ListBuffer.empty[String]

      var beginTimeOpt4Passage: Option[Int] = None
      var endTimeOpt4Passage: Option[Int] = None
      val beginTimeTextBuffer4Passage: ListBuffer[String] = ListBuffer.empty[String]
      val endTimeTextBuffer4Passage: ListBuffer[String] = ListBuffer.empty[String]
      val sentenceArray: Array[Sentence] = new Array[Sentence](passageWindow)
      for (j <- 0 until passageWindow) {
        val sentenceType: Sentence = sentenceList(i + j)
        sentenceArray(j) = sentenceType
        if (sentencePassageList contains sentenceType) {
          sentencePassageList(sentenceType) += passage
        } else {
          sentencePassageList(sentenceType) = ListBuffer.empty[Passage]
          sentencePassageList(sentenceType) += passage
        }
        sentenceType.getMorphemeList.toSeq foreach {
          case morpheme: Morpheme =>
            val index: Int = indexOfMorpheme(morphemeList4Passage, morpheme)
            morphemeList4Passage += {
              if (0 <= index && index < morphemeList4Passage.size) {
                morphemeList4Passage(index)
              } else {
                morpheme
              }
            }
          case _ =>
          // Do nothing
        }
        contentWordList4Passage ++= sentenceType.getContentWordList.toSeq

        val beginTimeOpt4Sentence: Option[Int] = Option(sentenceType.getBeginTime.getYear)
        beginTimeOpt4Sentence match {
          case Some(beginTime4Sentence) =>
            beginTimeOpt4Passage match {
              case Some(beginTime4Passage) =>
                if (beginTime4Sentence < beginTime4Passage) {
                  beginTimeOpt4Passage = beginTimeOpt4Sentence
                }
              case None =>
                beginTimeOpt4Passage = beginTimeOpt4Sentence
            }
          case None =>
          // Do nothing
        }
        val beginTimeTextList4Sentence: Seq[String] = sentenceType.getBeginTime.getTextList.toSeq
        beginTimeTextBuffer4Passage ++= beginTimeTextList4Sentence

        val endTimeOpt4Sentence: Option[Int] = Option(sentenceType.getEndTime.getYear)
        endTimeOpt4Sentence match {
          case Some(endTime4Sentence) =>
            endTimeOpt4Passage match {
              case Some(endTime4Passage) =>
                if (endTime4Passage < endTime4Sentence) {
                  endTimeOpt4Passage = endTimeOpt4Sentence
                }
              case None =>
                endTimeOpt4Passage = endTimeOpt4Sentence
            }
          case None =>
          // Do nothing
        }
        val endTimeTextList4Sentence: Seq[String] = sentenceType.getEndTime.getTextList.toSeq
        endTimeTextBuffer4Passage ++= endTimeTextList4Sentence

        val geography4Sentence: Geography = sentenceType.getGeography
        geographyBuffer4Passage ++= geography4Sentence.getArea.toSeq
      }

      passage.setBegin(sentenceArray.head.getBegin)
      passage.setEnd(sentenceArray.last.getEnd)

      passage.setSentenceSet(sentenceArray.toFSArray)
      passage.setDocumentSet(Seq[Document](document).toFSList)

      passage.setContentWordList(contentWordList4Passage.result.toStringList)
      passage.setMorphemeList(morphemeList4Passage.result.toFSList)

      beginTimeOpt4Passage match {
        case Some(beginYear) =>
          val beginYearType4Passage: Time = new Time(aJCas)
          beginYearType4Passage.addToIndexes()
          beginYearType4Passage.setYear(beginYear)
          beginYearType4Passage.setTextList(beginTimeTextBuffer4Passage.result.distinct.toStringList)
          passage.setBeginTime(beginYearType4Passage)
        case None =>
        // Do nothing
      }
      endTimeOpt4Passage match {
        case Some(endYear) =>
          val endYearType4Passage: Time = new Time(aJCas)
          endYearType4Passage.addToIndexes()
          endYearType4Passage.setYear(endYear)
          endYearType4Passage.setTextList(endTimeTextBuffer4Passage.result.distinct.toStringList)
          passage.setEndTime(endYearType4Passage)
        case None =>
        // Do nothing
      }

      val geographyType: Geography = new Geography(aJCas)
      geographyType.addToIndexes()
      geographyType.setArea(geographyBuffer4Passage.result.distinct.toStringList)
      passage.setGeography(geographyType)

      correct(passage, keywordSet)
      passageBuffer += passage
    }

    sentencePassageList foreach {
      case (sentence, passageListBuffer) =>
        sentence.setPassageSet(passageListBuffer.result.toFSList)
      case _ =>
      // Do nothing
    }

    document.setPassageSet(passageBuffer.result.toFSList)
  }

  //document.addToIndexesとdocument.setTextを事前に行ったdocumentが対象
  def annotate(aJCas: JCas, document: Document, keywordSet: Seq[String]): Document = {
    //println(">> Document Annotator Processing")
    JCasUtils.setAJCasOpt(Option(aJCas))
//println(document.getText)
    val contentWordList4Doc: ListBuffer[String] = ListBuffer.empty[String]
    val morphemeList4Doc: ListBuffer[Morpheme] = ListBuffer.empty[Morpheme]

    val sentenceList: Seq[String] = JapaneseSentenceSplitter.split(StringOption(document.getText)).map(_.text)
    val sentenceBuffer: ListBuffer[Sentence] = ListBuffer.empty[Sentence]
    val scoreArrayOpt: Option[FSArray] = Option(document.getScoreList)

    var beginIndex: Int = 0
    var endIndex: Int = -1
    var beginTimeOpt4Doc: Option[Int] = None
    var endTimeOpt4Doc: Option[Int] = None
    val beginTimeTextBuffer4Doc: ListBuffer[String] = ListBuffer.empty[String]
    val endTimeTextBuffer4Doc: ListBuffer[String] = ListBuffer.empty[String]

    val geographyBuffer4Doc: ListBuffer[String] = ListBuffer.empty[String]

    val b: Breaks = new Breaks()
    b.breakable {
      for (i <- sentenceList.indices) {
        val sentenceOpt: StringOption = StringOption(sentenceList(i))
        if (sentenceOpt.isEmpty) {
          b.break
        }

        val sentenceType: Sentence = new Sentence(aJCas)
        sentenceType.addToIndexes()

        val originalText: String = sentenceOpt.get
        sentenceType.setOriginalText(originalText)

        beginIndex = endIndex + 1
        sentenceType.setBegin(beginIndex)

        endIndex = beginIndex + originalText.length - 1
        sentenceType.setEnd(endIndex)

        scoreArrayOpt match {
          case Some(scoreArray) =>
            sentenceType.setScoreList(scoreArray)
          case None =>
          // Do nothing
        }

        sentenceType.setText(sentenceOpt.get)

        analyze(aJCas, sentenceType, sentenceOpt)

        val contentWords: Seq[String] = extractContentWords(sentenceOpt)
        sentenceType.setContentWordList(contentWords.toStringList)
        contentWordList4Doc ++= contentWords

        val geographyBuffer4Sentence: ListBuffer[String] = ListBuffer.empty[String]

        val time: TimeTmp = TimeMerger.union(TimeExtractorForWorldHistory.extract(sentenceOpt))
        val beginTimeOpt4Sentence: Option[Int] = time.beginTime
        val endTimeOpt4Sentence: Option[Int] = time.endTime
        val beginTimeTextBuffer4Sentence: ListBuffer[String] = ListBuffer.empty[String]
        beginTimeTextBuffer4Sentence ++= time.beginTimeTextList
        val endTimeTextBuffer4Sentence: ListBuffer[String] = ListBuffer.empty[String]
        endTimeTextBuffer4Sentence ++= time.endTimeTextList


        beginTimeOpt4Sentence match {
          case Some(beginTime4Sentence) =>
            val beginTime: Time = new Time(aJCas)
            beginTime.addToIndexes()
            beginTime.setYear(beginTime4Sentence)
            val beginTimeText4Sentence: Seq[String] = beginTimeTextBuffer4Sentence.result.distinct
            beginTime.setTextList(beginTimeText4Sentence.toStringList)
            sentenceType.setBeginTime(beginTime)

            beginTimeOpt4Doc match {
              case Some(beginTime4Doc) =>
                if (beginTime4Sentence < beginTime4Doc) {
                  beginTimeOpt4Doc = beginTimeOpt4Sentence
                }
              case None =>
                beginTimeOpt4Doc = beginTimeOpt4Sentence
            }
            beginTimeTextBuffer4Doc ++= beginTimeText4Sentence
          case None =>
          // Do nothing
        }
        endTimeOpt4Sentence match {
          case Some(endTime4Sentence) =>
            val endTime: Time = new Time(aJCas)
            endTime.addToIndexes()
            endTime.setYear(endTime4Sentence)
            val endTimeText4Sentence: Seq[String] = endTimeTextBuffer4Sentence.result.distinct
            endTime.setTextList(endTimeText4Sentence.toStringList)
            sentenceType.setEndTime(endTime)

            endTimeOpt4Doc match {
              case Some(endTime4Doc) =>
                if (endTime4Doc < endTime4Sentence) {
                  endTimeOpt4Doc = endTimeOpt4Sentence
                }
              case None =>
                endTimeOpt4Doc = endTimeOpt4Sentence
            }
            endTimeTextBuffer4Doc ++= endTimeText4Sentence
          case None =>
          // Do nothing
        }

        val geographyType: Geography = new Geography(aJCas)
        geographyType.addToIndexes()
        val geographyList4Sentence: Seq[String] = geographyBuffer4Sentence.result.distinct
        geographyType.setArea(geographyList4Sentence.toStringList)
        geographyBuffer4Doc ++= geographyList4Sentence
        sentenceType.setGeography(geographyType)

        sentenceType.getMorphemeAnalysisList.toSeq foreach {
          case morphemeAnalysis: MorphemeAnalysis if morphemeAnalysis.getAnalyzer equalsIgnoreCase Config.mainMorphemeAnalyzer =>
            morphemeAnalysis.getMorphemeList.toSeq foreach {
              case morpheme: Morpheme =>
                val index: Int = indexOfMorpheme(morphemeList4Doc, morpheme)
                morphemeList4Doc += {
                  if (0 <= index && index < morphemeList4Doc.size) {
                    morphemeList4Doc(index)
                  } else {
                    morpheme
                  }
                }
              case _ =>
              // Do nothing
            }
          case _ =>
          // Do nothing
        }

        //照応解析
        if (0 < i) {
          StringOption(sentenceList(i - 1)) match {
            case StringSome(previousSentence) =>
            //TODO: 照応解析
            case StringNone =>
            // Do nothing
          }
        }

        correct(sentenceType, keywordSet)
        sentenceBuffer += sentenceType
      }
    }

    val sentenceTypeList: Seq[Sentence] = sentenceBuffer.result

    if (Config.usePassage) {
      annotatePassage(aJCas, sentenceTypeList, document, scoreArrayOpt, keywordSet)
    }

    document.setBegin(0)
    document.setEnd(sentenceTypeList.last.getEnd)

    document.setSentenceSet(sentenceTypeList.toFSList)

    document.setContentWordList(contentWordList4Doc.result.toStringList)
    document.setMorphemeList(morphemeList4Doc.result.toFSList)

    beginTimeOpt4Doc match {
      case Some(beginYear4Doc) =>
        val beginYearType4Doc: Time = new Time(aJCas)
        beginYearType4Doc.addToIndexes()
        beginYearType4Doc.setTextList(beginTimeTextBuffer4Doc.result.distinct.toStringList)
        beginYearType4Doc.setYear(beginYear4Doc)
        document.setBeginTime(beginYearType4Doc)
      case None =>
      // Do nothing
    }

    endTimeOpt4Doc match {
      case Some(endYear4Doc) =>
        val endYearType4Doc: Time = new Time(aJCas)
        endYearType4Doc.addToIndexes()
        endYearType4Doc.setTextList(endTimeTextBuffer4Doc.result.distinct.toStringList)
        endYearType4Doc.setYear(endYear4Doc)
        document.setEndTime(endYearType4Doc)
      case None =>
      // Do nothing
    }

    val geographyType: Geography = new Geography(aJCas)
    geographyType.addToIndexes()
    geographyType.setArea(geographyBuffer4Doc.result.distinct.toStringList)
    document.setGeography(geographyType)

    correct(document, keywordSet)

    document
  }

  private def correct(text: TextAnnotation, keywordSet: Seq[String]): Unit = {
    val builder: StringBuilder = new StringBuilder(text.getText.length)
    text.getMorphemeList.toSeq.asInstanceOf[Seq[Morpheme]] foreach {
      morpheme: Morpheme =>
        if (!morpheme.getPos.startsWith("接続詞")) {
          builder.append(morpheme.getOriginalText)
        }
    }
    text.setText(recursivelyRemoveNoise(builder.result, keywordSet, 0))
  }

  private def recursivelyRemoveNoise(text: String, keywordSet: Seq[String], index: Int): String = {
    val p: Pattern = Pattern.compile("""\([^\)]*\)""")
    val m: Matcher = p.matcher(text)
    if (m.find(index)) {
      if (keywordSet.contains(m.group)) {
        recursivelyRemoveNoise(
          text,
          keywordSet,
          m.end)
      } else {
        recursivelyRemoveNoise(
          text.substring(0, m.start) concat text.substring(m.end),
          keywordSet,
          m.start)
      }
    } else {
      text
    }
  }
}
