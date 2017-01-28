package text.analyzer.dep.chapas

import text.analyzer.dep.{Chunk, Chunks}

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
case class Token(id: Int,
                 surface: String,
                 base: String,
                 pos: String,
                 detailedPos: Option[String],
                 semantic: Option[String],
                 private val pasMap: Option[Map[String, String]]) {
  private var doc_ : Doc = _

  def doc: Doc = doc_

  def doc_=(doc: Doc): Unit = doc_ = doc

  private var sentence_ : Chunks = _

  def sentence: Chunks = sentence_

  def sentence_=(sentence: Chunks): Unit = sentence_ = sentence

  private var chunk_ : Chunk = _

  def chunk: Chunk = chunk_

  def chunk_=(chunk: Chunk): Unit = chunk_ = chunk

  lazy val prev: Option[Token] = sentence.tokens.lift(id - 1)

  lazy val next: Option[Token] = sentence.tokens.lift(id + 1)

  lazy val isPredicate: Boolean = pasMap match {
    case Some(map) if map.get("type").exists(e => e == "pred" || e == "noun") => true
    case None => false
  }

  lazy val argId: Option[Int] = pasMap map {
    map: Map[String, String] =>
      {map.get("id") orElse map.get("ID") map {
        x: String => x.toInt
      }}.get
  }

  lazy val pas: Option[PAS] = pasMap map {
    map: Map[String, String] =>
      val arguments: Map[ArgLabel.Value, Token] = map.collect {
        case (labelName, argIdStr) if labelName.matches( """(ga|o|ni)""") =>
          val argToken: Option[Token] = doc.tokens.find(_.argId match {
            case Some(x) if x == argIdStr.toInt => true
            case _ => false
          })
          assume(argToken.isInstanceOf[Some[Token]], s"${this.surface} refers unknown argument ID: $argIdStr")
          ArgLabel.extendedWithName(labelName) -> argToken.get
      }
      PAS(this, arguments)
  }

  override val toString: String = surface
}