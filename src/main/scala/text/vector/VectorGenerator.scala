package text.vector

import jeqa.types.{Sentence, TextAnnotation}

import scala.collection.mutable

/**
  * @author K.Sakamoto
  *         Created on 2016/05/22
  */
trait VectorGenerator[Vector] {
  private val cache: mutable.Map[Long, Vector] = mutable.Map[Long, Vector]()

  def getVectorFromCache(id: Long, sentence: String): Vector = {
    if (cache contains id) {
      cache(id)
    } else {
      val vector: Vector = getVectorFromSentence(sentence)
      cache(id) = vector
      vector
    }
  }

  def getVectorFromText(text: String): Vector

  def getVectorFromSentence(sentence: String): Vector

  def getVectorFromSentences(sentences: Seq[Sentence]): Vector

  def getVectorFromAnnotation(annotation: TextAnnotation): Vector
}
