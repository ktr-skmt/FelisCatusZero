package text.analyzer.mor.mecab

import text.analyzer.mor.MorphemeAnalyzer
import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable.ListBuffer
import scala.sys.process.{Process, ProcessBuilder}

/**
 * @author K.Sakamoto
 */
abstract class Mecab extends MorphemeAnalyzer {
  override def analyzer(): ProcessBuilder = {
    Process(Seq("mecab", "-d", Config.mecabUnidicDir))
  }

  override protected val negativePosListForContentWord: Seq[String] = Nil

  //TODO: 括弧「」は別の語に置き換えて解析し、括弧内も別途解析し、それら解析結果をマージする

  protected def isNoun(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    val pos: String = posOpt.get
    pos startsWith "名詞"
  }

  protected def isNumeral(posOpt: StringOption): Boolean = {
    if (posOpt.isEmpty) {
      return false
    }
    posOpt.get startsWith "名詞,数"
  }

  protected def isFunctionalVerb(morpheme: StringOption): Boolean = {
    morpheme match {
      case StringSome(m) =>
        functionalVerbs contains m
      case StringNone =>
        false
    }
  }

  protected def nonFunctionalVerb(morpheme: StringOption): Boolean = {
    !isFunctionalVerb(morpheme)
  }

  override def extractMorphemes(sentence: StringOption): Seq[OriginalTextTextPOS] = {
    val buffer: ListBuffer[(String, String, String, StringOption, Seq[String])] = ListBuffer.empty[(String, String, String, StringOption, Seq[String])]
    analyze(sentence) foreach {
      case line if line != "EOS" =>
        val tokens: Array[String] = line.split('\t')
        //if (line startsWith "分") {
        //  println(line)
        //}
        if (tokens.length == 2) {
          val subTokens: Array[String] = tokens.last.split(',')
          val originalTextOpt: StringOption = StringOption(tokens.head.trim)
          //if (originalTextOpt.get == "。") {
          //  println(line)
          //}
          if (textColumn < subTokens.length) {
            val textOpt: StringOption = StringOption(subTokens(textColumn).trim)
            val posOpt: StringOption = StringOption(subTokens.take(textColumn).mkString(",").trim)
            if (originalTextOpt.nonEmpty &&
              textOpt.nonEmpty &&
              posOpt.nonEmpty) {
              buffer += {
                if ((sourceColumn < subTokens.length) && (subTokens(sourceColumn) == "世界史用語辞書")) {
                  if (semanticTypeColumn < subTokens.length) {
                    (originalTextOpt.get, textOpt.get, posOpt.get, StringOption(subTokens(semanticCategoryColumn)), subTokens(semanticTypeColumn).split('@').toSeq.filter(StringOption(_).nonEmpty))
                  } else {
                    (originalTextOpt.get, textOpt.get, posOpt.get, StringOption(subTokens(semanticCategoryColumn)), Nil)
                  }
                } else {
                  (originalTextOpt.get, textOpt.get, posOpt.get, StringNone, Nil)
                }
              }
            }
          } else {
            buffer += ((originalTextOpt.get, originalTextOpt.get, tokens.last, StringNone, Nil))
          }
        } else {
          // Do nothing
        }
      case _ =>
      // Do nothing
    }
    buffer.result
  }
}

