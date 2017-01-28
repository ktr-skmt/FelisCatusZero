package text.analyzer.dep

import text.analyzer.dep.chapas.{Doc, PAS, Token}

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
case class Chunks(id: Int, chunks: Seq[Chunk]) {
  val tokens: Seq[Token] = chunks.flatMap(_.tokens)

  val text: String = chunks.map(_.text).mkString

  private var doc_ : Doc = _

  def doc: Doc = doc_

  def doc_=(doc: Doc): Unit = doc_ = doc

  private var depTree_ : String = ""

  def depTree: String = depTree_

  def depTree_=(depTree: String): Unit = depTree_ = depTree

  private var ntcText_ : String = ""

  def ntcText: String = ntcText_

  def ntcText_=(ntcText: String): Unit = ntcText_ = ntcText

  /*
  def coloredDepTree: String = {
    val i: Iterator[Int] = Stream.from(0).iterator
    // println(depTree)
    val r = depTree.lines.map { l =>
      val n: Int = i.next
      l match {
        case CaboCha.treeChunkRegExp(s, _, d) if n < chunks.length =>
          // TODO: Fix bug
          s + Color.coloring(chunks(n)) + d
        case s => s
      }
    }.mkString("\n")
    // println(r)
    r
  }
  */

  lazy val pases: Seq[PAS] = chunks.flatMap(_.pases)

  override def toString: String = text
}