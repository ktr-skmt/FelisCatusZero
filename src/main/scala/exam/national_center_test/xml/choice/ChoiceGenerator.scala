package exam.national_center_test.xml.choice

import exam.national_center_test.xml.choice.collcetion.{ChoiceCollection, ChoiceSet, ChoiceSingleton}

/**
 * <pre>
 * Created on 5/24/15.
 * </pre>
 * @author K.Sakamoto
 */
object ChoiceGenerator {
  def generate(choices: Seq[ChoiceCollection]): Option[ChoicesImpl] = {
    generate(choices, ChoiceType.detectChoiceType(choices))
  }

  private def generate(choices: Seq[ChoiceCollection], choiceType: ChoiceType.Value): Option[ChoicesImpl] = {
    choiceType match {
      case ChoiceType.BinaryCombo =>
        Option(ChoicesBinaryCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.ImageCombo =>
        Option(ChoicesImageCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.SentenceCombo =>
        Option(ChoicesSentenceCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.SymbolCombo =>
        Option(ChoicesSymbolCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.SymbolListCombo =>
        Option(ChoicesSymbolListCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.TermCombo =>
        Option(ChoicesTermCombo(choices map {
          _.asInstanceOf[ChoiceSet]
        }))
      case ChoiceType.Binary =>
        Option(ChoicesBinary(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case ChoiceType.Image =>
        Option(ChoicesImage(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case ChoiceType.Sentence =>
        Option(ChoicesSentence(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case ChoiceType.Symbol =>
        Option(ChoicesSymbol(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case ChoiceType.SymbolList =>
        Option(ChoicesSymbolList(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case ChoiceType.Term =>
        Option(ChoicesTerm(choices map {
          _.asInstanceOf[ChoiceSingleton]
        }))
      case otherwise =>
        None
    }
  }
}
