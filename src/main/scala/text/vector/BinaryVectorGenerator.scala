package text.vector

import jeqa.types.{Sentence, TextAnnotation}
import text.StringOption
import text.analyzer.{JapaneseSentenceSplitter, Tokenizer}
import util.uima.StringListUtils._

/**
  * @author K.Sakamoto
  *         Created on 2016/05/22
  */
object BinaryVectorGenerator extends VectorGenerator[BinaryVector] {
  override def getVectorFromText(text: String): BinaryVector = {
    BinaryVectorMerger.merge(
      for (sentence <- JapaneseSentenceSplitter.split(StringOption(text))) yield {
        getVectorFromSentence(sentence.text)
      }
    )
  }

  override def getVectorFromSentence(sentence: String): BinaryVector = {
    new BinaryVector(Tokenizer.tokenize(StringOption(sentence)))
  }

  override def getVectorFromSentences(sentences: Seq[Sentence]): BinaryVector = {
    BinaryVectorMerger.merge(
      for (sentence <- sentences) yield {
        getVectorFromAnnotation(sentence)
      }
    )
  }

  override def getVectorFromAnnotation(annotation: TextAnnotation): BinaryVector = {
    new BinaryVector(annotation.getContentWordList.toSeq)
  }
}
