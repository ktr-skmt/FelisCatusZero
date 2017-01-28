package exam.national_center_test.xml.choice

import exam.national_center_test.xml.choice.collcetion.{ChoiceCollection, ChoiceSet, ChoiceSingleton}
import text.StringOption
import text.normalizer.Normalizer

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.util.matching.Regex.Match
import scala.xml.NodeSeq

/**
 * <pre>
 * Created on 8/1/15.
 * </pre>
 * @author K.Sakamoto
 */
object ChoiceParser {
  def parse(choices: NodeSeq): ChoicesImpl = {
    val buffer: ListBuffer[ChoiceCollection] = ListBuffer.empty[ChoiceCollection]

    val defaultChoice: ChoicesBinary = new ChoicesBinary(Nil)

    val hyphen: String = "\u002d\u2014\u2212"

    val choiceSetElementPattern: String = {
      s"([^$hyphen]+)[$hyphen]([^$hyphen]+)\\s"
    }

    def isChronologicalOrder(choiceText: String): Boolean = {
      (choiceText.split(' ').head split s"[$hyphen]").length > 2
    }

    def isChoiceSet(choiceText: String): Boolean = {
      if (isChronologicalOrder(choiceText)) {
        return false
      }
      val pattern = s"($choiceSetElementPattern)+".r
      (pattern findFirstIn choiceText).nonEmpty && !choiceText.contains("。")
    }

    choices \ "choice" foreach {
      choice =>
        //<cNum>①</cNum><ref comment="D000W22-003画像内">ａ</ref>
        //<cNum>①</cNum><ref comment="D000W24-002画像内">ａ</ref>—洗礼 <ref comment="D000W24-002画像内">ｂ</ref>—結婚 <ref comment="D000W24-002画像内">ｃ</ref>—埋葬
        //<cNum>①</cNum><ref comment="" target="L13">ア</ref>−<ref target="D22">ａ</ref><ref comment="" target="L14">イ</ref>−<ref target="D22">ｂ</ref>
        //<cNum>①</cNum><ref comment="" target="L11">ａ</ref>−正 <ref comment="" target="L12">ｂ</ref>−正
        //<cNum>①</cNum><ref comment="" target="L5">ア</ref>−<ref comment="画像内" target="">ａ</ref>
        //<cNum>①</cNum><ref target="L5">ａ </ref>→<ref target="L6"> ｂ </ref>→<ref target="L7"> ｃ</ref>
        //<cNum>①</cNum> <ref target="L1">ア</ref>-<ref target="L2">イ</ref>
        //<cNum>①</cNum> <ref comment="画像内" target="D17">ａ</ref>-<ref target="L6">イ </ref> <ref comment="画像内" target="D17">ｂ</ref>-<ref target="L5">ア</ref> <ref comment="画像内" target="D17">ｃ</ref>-<ref target="L8">エ </ref> <ref comment="画像内" target="D17">ｄ</ref>-<ref target="L7">ウ </ref>

        val choiceRemovedCNum: String = Normalizer.
          normalize(StringOption(choice.child.filterNot(element => element.label == "cNum").text.trim)).get.
          replaceAll("\n", "").
          replaceAll(s"[$hyphen][$hyphen]+", "\u002d").
          replaceAll("\\s\\s+", " ").
          concat(" ")

        buffer += {
          if (isChoiceSet(choiceRemovedCNum)) {
            val map: mutable.Map[String, ChoiceSingleton] = mutable.Map.empty[String, ChoiceSingleton]
            choiceSetElementPattern.r.findAllMatchIn(choiceRemovedCNum) foreach {
              matcher: Match =>
                map(matcher.group(1)) = new ChoiceSingleton(
                  matcher.group(2)
                )
            }
            new ChoiceSet(map.toMap)
          } else if (isChronologicalOrder(choiceRemovedCNum)) {
            new ChoiceSingleton(
              choiceRemovedCNum.
                replaceAll(s"[$hyphen]", SymbolListParser.delimiter)
            )
          } else {
            new ChoiceSingleton(
              choiceRemovedCNum
            )
          }
        }
    }
    val result: Seq[ChoiceCollection] = buffer.result
    if (result.nonEmpty) {
      ChoiceGenerator.generate(result) match {
        case Some(c) => c
        case None => defaultChoice
      }
    } else {
      defaultChoice
    }
  }
}
