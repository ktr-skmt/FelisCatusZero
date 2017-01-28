package text.analyzer.mor.mecab

import converter.KanjiNumeralParser
import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable.ListBuffer
import scala.sys.process.{Process, ProcessBuilder}

/**
  * @author K.Sakamoto
  *         Created on 2016/12/02
  */
object IpadicMecab extends Mecab {
  override def analyzer(): ProcessBuilder = {
    Config.mecabIPADicUserDic match {
      case StringSome(userDic) =>
        Process(Seq("mecab", "-d", Config.mecabIPADicDir, "-u", userDic))
      case StringNone =>
        Process(Seq("mecab", "-d", Config.mecabIPADicDir))
    }
  }

  override protected val textColumn: Int = 6
  override protected val sourceColumn: Int = 9
  override protected val semanticCategoryColumn: Int = 10
  override protected val semanticTypeColumn: Int = 11



  override protected val negativePosListForContentWord: Seq[String] = Seq(
    "その他",
    "フィラー",
    "助詞",
    "助動詞",
    "感動詞",
    "空白",
    "補助記号",
    "記号,一般",
    "記号,括弧開",
    "記号,括弧閉",
    "記号,句点",
    "記号,空白",
    "記号,読点"
  )

  override protected val adverbialNouns: Seq[String] = Seq("ところ", "ため", "くらい")

  override protected val formalNouns: Seq[String] = Seq("の", "こと", "もの", "つもり", "わけ")

  override protected val functionalVerbs: Seq[String] = Seq("する", "いる", "なる", "ある")

  //TODO: assertionをあとで書く
  //数字連続をひとつにまとめている
  //記法1 六億五七八三万七六九一
  //記法2 八千万、八百万
  //記法3 一九四五
  //記法4 十三、二十、六十五
  //数字以外の名詞連続をひとつにまとめている
  override protected def extractWords(sentence: StringOption, needsContentWords: Boolean): Seq[String] = {
    val terms                : ListBuffer[String] = ListBuffer.empty[String]
    val compoundNounBuilder  : StringBuilder = new StringBuilder(0)
    val compoundAdverbBuilder: StringBuilder = new StringBuilder(0)
    val compoundVerbBuilder  : StringBuilder = new StringBuilder(0)
    val numerals             : StringBuilder = new StringBuilder(0)

    def append(term: StringOption): Unit = {
      if (nonFunctionalVerb(term)) {
        term match {
          case StringSome(t) =>
            terms += t
          case StringNone =>
          //Do nothing
        }
      }
    }

    def extractCompoundNoun(): Unit = {
      if (0 < compoundNounBuilder.length) {
        append(StringOption(compoundNounBuilder.result))
        compoundNounBuilder.clear
      }
    }

    def extractNumeral(): Unit = {
      if (0 < numerals.length) {
        KanjiNumeralParser.parseKanjiNumerals(StringOption(numerals.result)) match {
          case Some(n) =>
            append(StringOption(n.toString))
          case None =>
          // Do nothing
        }

        numerals.clear
      }
    }

    def extractCompoundAdverb(): Unit = {
      if (0 < compoundAdverbBuilder.length) {
        append(StringOption(compoundAdverbBuilder.result))
        compoundAdverbBuilder.clear
      }
    }

    def extractCompoundVerb(): Unit = {
      if (0 < compoundVerbBuilder.length) {
        append(StringOption(compoundVerbBuilder.result))
        compoundVerbBuilder.clear
      }
    }

    def compoundNoun(originalTextOpt: StringOption, textOpt: StringOption, posOpt: StringOption): Unit = {
      if (originalTextOpt.isEmpty || textOpt.isEmpty || posOpt.isEmpty) {
        return
      }

      //val originalText: String = originalTextOpt.get
      val text: String = textOpt.get
      val pos: String = posOpt.get

      def bufferNoun(): Unit = {
        compoundNounBuilder.append(text)
      }

      def bufferNumeral(): Unit = {
        numerals.append(text)
      }

      def bufferAdverb(): Unit = {
        compoundAdverbBuilder.append(text)
      }

      def bufferVerb(): Unit = {
        compoundVerbBuilder.append(text)
      }

      if (isNoun(posOpt)) {
        if (isNumeral(posOpt)) {
          extractCompoundNoun()
          bufferNumeral()
        } else {
          extractNumeral()
          bufferNoun()
        }
      } else {
        extractCompoundNoun()
        extractNumeral()
      }

      if (isVerb(posOpt)) {
        bufferVerb()
        extractCompoundVerb()
      }

      if (isAdverb(posOpt)) {
        bufferAdverb()
        extractCompoundAdverb()
      }

      if (isPrefix(posOpt)) {
        if ((pos startsWith "接頭詞,名詞接続") || (pos startsWith "接頭詞,数接続")) {
          bufferNoun()
        } else if (pos startsWith "接頭詞,動詞接続") {
          bufferVerb()
        } else if (pos startsWith "接頭詞,形容詞接続") {
          bufferAdverb()
        }
      }

      if (!(isNoun(posOpt) || isVerb(posOpt) || isAdverb(posOpt) || isPrefix(posOpt))) {
        append(textOpt)
      }
    }

    extractMorphemes(sentence) foreach {
      morpheme =>
        compoundNoun(StringOption(morpheme._1), StringOption(morpheme._2), StringOption(morpheme._3))
    }

    extractCompoundAdverb()
    extractCompoundNoun()
    extractNumeral()
    extractCompoundVerb()

    terms.result
  }
}
