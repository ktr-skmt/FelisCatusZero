package text.analyzer.dep.chapas

import text.analyzer.dep.Chunks

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
case class Doc(sentences: Seq[Chunks]) {
  val text: String = sentences.map(_.text).mkString("\n")

  val tokens: Seq[Token] = sentences.flatMap(_.tokens)

  lazy val pases: Seq[PAS] = sentences.flatMap(_.pases)

  override def toString: String = text
}