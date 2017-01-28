package text

import java.util.regex.Pattern

import ner.NamedEntityRecognizerInGlossary
import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2015/11/27
  */
object TermNormalizerInGlossary extends TermNormalizer {
  override def normalize(textOpt: StringOption): StringOption = {
    if (textOpt.isEmpty) {
      return StringNone
    }
    val text: String = textOpt.get
    Config.useTermNormalizer = false
    NamedEntityRecognizerInGlossary.recognize(textOpt).foreach {
      case namedEntity if namedEntity.textOpt.nonEmpty && 1 < namedEntity.synonyms.size =>
        val ne: String = namedEntity.textOpt.get
        var minLengthSynonym: String = ne
        namedEntity.synonyms foreach {
          case synonym if synonym.length < minLengthSynonym.length =>
            minLengthSynonym = synonym
          case _ =>
            //Do nothing
        }
        text.replaceAll(Pattern.quote(ne), minLengthSynonym)
      case _ =>
        //Do nothing
    }
    Config.useTermNormalizer = true
    StringOption(text)
  }
}
