package ner

import text.StringOption

/**
  * @author K.Sakamoto
  *         Created on 2015/11/26
  */
object NamedEntityRecognizerForWorldHistory extends NamedEntityRecognizer {
  override def recognize(textOpt: StringOption): Seq[NamedEntity] = {
    NamedEntityRecognizerInGlossary.recognize(textOpt).toList :::
    NamedEntityRecognizerInEventOntology.recognize(textOpt).toList
  }

  //Do not use
  override protected val recognizerName: String = null

  //Do not use
  override protected def initialize: NEList = Nil

  //Do not use
  override protected val entityList: NEList = initialize
}
