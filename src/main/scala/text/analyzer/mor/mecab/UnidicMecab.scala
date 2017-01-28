package text.analyzer.mor.mecab

import converter.KanjiNumeralParser
import text.{StringNone, StringOption, StringSome}
import util.Config

import scala.collection.mutable.ListBuffer
import scala.sys.process.{Process, ProcessBuilder}

/**
 * <pre>
 * Created on 4/19/15.
 * </pre>
 * @author K.Sakamoto
 */
object UnidicMecab extends Mecab {
  override def analyzer(): ProcessBuilder = {
    Config.mecabUnidicUserDic match {
      case StringSome(userDic) =>
        Process(Seq("mecab", "-d", Config.mecabUnidicDir, "-u", userDic))
      case StringNone =>
        Process(Seq("mecab", "-d", Config.mecabUnidicDir))
    }
  }

  override protected val textColumn: Int = 7
  override protected val sourceColumn: Int = 14
  override protected val semanticCategoryColumn: Int = 15
  override protected val semanticTypeColumn: Int = 16

  override protected val negativePosListForContentWord: Seq[String] = Seq(
    "助詞",
    "助動詞",
    "感動詞",
    "空白",
    "補助記号",
    "記号,一般"
  )

  override protected val adverbialNouns: Seq[String] = Seq("所", "為", "くらい")

  override protected val formalNouns: Seq[String] = Seq("の", "事", "物", "積り", "訳")

  override protected val functionalVerbs: Seq[String] = Seq("為る", "居る", "成る", "有る")

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

    def append(term: StringOption, originalTextOpt: StringOption, posOpt: StringOption): Unit = {
      term match {
        case StringSome(t) =>
          if (needsContentWords) {
            if (isContentWord(originalTextOpt, posOpt)) {
              terms += t
            }
          } else {
            terms += t
          }
        case StringNone =>
        //Do nothing
      }
    }

    def extractCompoundNoun(): Unit = {
      if (0 < compoundNounBuilder.length) {
        val nounOpt: StringOption = StringOption(compoundNounBuilder.result)
        append(nounOpt, nounOpt, StringOption("名詞"))
        //println(compoundNounBuilder.result)
        compoundNounBuilder.clear
      }
    }

    def extractNumeral(): Unit = {
      if (0 < numerals.length) {
        //println(numerals.result)
        KanjiNumeralParser.parseKanjiNumerals(StringOption(numerals.result)) match {
          case Some(n) =>
            //println(n)
            val nounOpt: StringOption = StringOption(n.toString)
            append(nounOpt, nounOpt, StringOption("名詞"))
          case None =>
          // Do nothing
        }

        numerals.clear
      }
    }

    def extractCompoundAdverb(): Unit = {
      if (0 < compoundAdverbBuilder.length) {
        val adverbOpt: StringOption = StringOption(compoundAdverbBuilder.result)
        append(adverbOpt, adverbOpt, StringOption("形容詞"))
        compoundAdverbBuilder.clear
      }
    }

    def extractCompoundVerb(): Unit = {
      if (0 < compoundVerbBuilder.length) {
        val verbOpt: StringOption = StringOption(compoundVerbBuilder.result)
        append(verbOpt, verbOpt, StringOption("動詞"))
        compoundVerbBuilder.clear
      }
    }

    def compound(originalTextOpt: StringOption, textOpt: StringOption, posOpt: StringOption): Unit = {
      if (originalTextOpt.isEmpty || textOpt.isEmpty || posOpt.isEmpty) {
        return
      }

      //val originalText: String = originalTextOpt.get
      val text: String = textOpt.get
      val pos: String = posOpt.get

      def bufferNoun(): Unit = {
        extractNumeral()
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

      if (isNoun(posOpt) && originalTextOpt.get != ",") {
        if (isNumeral(posOpt) && !(textOpt.get contains "幾") && !(textOpt.get contains "うん") && !(textOpt.get contains "数") && !(textOpt.get contains "何")) {
          extractCompoundNoun()
          bufferNumeral()
        } else {
          bufferNoun()
        }
      } else if (isPrefix(posOpt)) {
        bufferNoun()
      } else if ((pos startsWith "連体詞") || (pos startsWith "代名詞")) {
        bufferNoun()
      } else if (pos startsWith "接尾辞,名詞的") {
        bufferNoun()
      } else {
        extractCompoundNoun()
        extractNumeral()
      }

      if (isVerb(posOpt)) {
        bufferVerb()
      } else if (pos startsWith "接尾辞,動詞的") {
        bufferVerb()
      } else {
        extractCompoundVerb()
      }

      if (isAdverb(posOpt)) {
        bufferAdverb()
      } else if (pos startsWith "接尾辞,形容詞的") {
        bufferAdverb()
      } else {
        extractCompoundAdverb()
      }

      if ((!(isNoun(posOpt) || isVerb(posOpt) || isAdverb(posOpt)) || originalTextOpt.get == ",") && !(pos startsWith "連体詞") && !(pos startsWith "代名詞") && !(pos startsWith "接尾辞,名詞的") && !(pos startsWith "接尾辞,動詞的") && !(pos startsWith "接尾辞,形容詞的")) {
        if (originalTextOpt.get == ",") {
          append(textOpt, originalTextOpt, StringOption("補助記号,読点,*,*,*,*,,、,、,,、,,記号,*,*,*,*,,,,,*,*,*,*,*"))
        } else {
          append(textOpt, originalTextOpt, posOpt)
        }
      }
    }


    extractMorphemes(sentence) foreach {
      morpheme =>
        compound(StringOption(morpheme._1), StringOption(morpheme._2), StringOption(morpheme._3))
    }

    extractCompoundAdverb()
    extractCompoundNoun()
    extractNumeral()
    extractCompoundVerb()

    terms.result
  }
}
