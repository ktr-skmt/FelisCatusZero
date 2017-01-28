package exam.national_center_test.xml.choice

import exam.national_center_test.xml.choice.collcetion.{ChoiceCollection, ChoiceCollectionType, ChoiceSet, ChoiceSingleton}

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @author K.Sakamoto
 */
object ChoiceType extends Enumeration {
  val None,
      Binary,
      Image,
      Sentence,
      Symbol,
      SymbolList,
      Term,
      BinaryCombo,
      ImageCombo,
      SentenceCombo,
      SymbolCombo,
      SymbolListCombo,
      TermCombo = Value

  def childChoiceType(choiceType: ChoiceType.Value): ChoiceType.Value = {
    choiceType match {
      case BinaryCombo =>
        ChoiceType.Binary
      case ImageCombo =>
        ChoiceType.Image
      case SentenceCombo =>
        ChoiceType.Sentence
      case SymbolCombo =>
        ChoiceType.Symbol
      case SymbolListCombo =>
        ChoiceType.SymbolList
      case TermCombo =>
        ChoiceType.Term
      case otherwise =>
        otherwise
    }
  }

  private def parentChoiceType(choiceType: ChoiceType.Value): ChoiceType.Value = {
    choiceType match {
      case ChoiceType.Binary =>
        ChoiceType.BinaryCombo
      case ChoiceType.Image =>
        ChoiceType.ImageCombo
      case ChoiceType.Sentence =>
        ChoiceType.SentenceCombo
      case ChoiceType.Symbol =>
        ChoiceType.SymbolCombo
      case ChoiceType.SymbolList =>
        ChoiceType.SymbolListCombo
      case ChoiceType.Term =>
        ChoiceType.TermCombo
      case otherwise =>
        otherwise
    }
  }

  def detectChoiceType(choices: Seq[ChoiceCollection]): ChoiceType.Value = {
    val headElement: ChoiceCollection = choices.head
    headElement.choiceCollectionType match {
      case ChoiceCollectionType.Set =>
        parentChoiceType(
          detectChoiceType(
            headElement.
              asInstanceOf[ChoiceSet].
              set.
              values.
              head.
              singleton
          )
        )
      case ChoiceCollectionType.Singleton =>
        detectChoiceType(
          headElement.
            asInstanceOf[ChoiceSingleton].
            singleton
        )
      case otherwise =>
        ChoiceType.None
    }
  }

  private def detectChoiceType(choice: String): ChoiceType.Value = {
    detectChoiceBinary(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    detectChoiceImage(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    detectChoiceSentence(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    detectChoiceSymbol(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    detectChoiceSymbolList(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    detectChoiceTerm(choice) match {
      case Some(choiceType) =>
        return choiceType
      case scala.None =>
    }

    ChoiceType.None
  }

  private def detectChoiceBinary(choice: String): Option[ChoiceType.Value] = {
    if (Set("正", "誤") contains choice) {
      Option(ChoiceType.Binary)
    } else {
      scala.None
    }
  }

  private def detectChoiceImage(choice: String): Option[ChoiceType.Value] = {
    if (choice endsWith ".png") {
      Option(ChoiceType.Image)
    } else {
      scala.None
    }
  }

  private def detectChoiceSentence(choice: String): Option[ChoiceType.Value] = {
    if (choice endsWith "。") {
      Option(ChoiceType.Sentence)
    } else {
      scala.None
    }
  }

  private def detectChoiceSymbol(choice: String): Option[ChoiceType.Value] = {
    if (choice matches "[a-fア-オ]") {
      Option(ChoiceType.Symbol)
    } else {
      scala.None
    }
  }

  private def detectChoiceSymbolList(choice: String): Option[ChoiceType.Value] = {
    if (choice contains SymbolListParser.delimiter) {
      Option(ChoiceType.SymbolList)
    } else {
      scala.None
    }
  }

  private def detectChoiceTerm(choice: String): Option[ChoiceType.Value] = {
    Option(ChoiceType.Term)
  }
}
