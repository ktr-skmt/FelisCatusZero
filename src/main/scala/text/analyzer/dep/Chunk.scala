package text.analyzer.dep

import text.analyzer.dep.chapas.{Doc, PAS, Token}

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
case class Chunk(id: Int,
                 tokens: Seq[Token],
                 headId: Int,
                 depType: DepType.Value) {
  val text: String = tokens.map(_.surface).mkString("")

  val textWithBase: String = tokens.map(_.base).mkString("")

  val tokensWithoutParticlesAndPuncs: Seq[Token] =
    tokens.collect { case t if t.pos != "助詞" && t.pos != "特殊" => t }

  val textWithoutParticlesAndPuncs: String = tokensWithoutParticlesAndPuncs.map(_.surface).mkString("")

  lazy val depDest: Option[Chunk] = sentence.chunks.find(_.id == headId)

  lazy val depSrcs: Seq[Chunk] = sentence.chunks.filter(_.headId == id)

  private var doc_ : Doc = _

  def doc: Doc = doc_

  def doc_=(doc: Doc): Unit = doc_ = doc

  private var sentence_ : Chunks = _

  def sentence: Chunks = sentence_

  def sentence_=(sentence: Chunks): Unit = sentence_ = sentence

  lazy val prev: Option[Chunk] = sentence.chunks.lift(id - 1)

  lazy val next: Option[Chunk] = sentence.chunks.lift(id + 1)

  lazy val pases: Seq[PAS] = tokens.collect { case t if t.isPredicate => t.pas.get }

  lazy val hasPredicates: Boolean = tokens.exists(_.isPredicate)

  override def toString: String = text
}