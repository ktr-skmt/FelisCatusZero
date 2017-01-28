package text.analyzer.dep.chapas

import text.analyzer.dep.{Chunk, Chunks, DepType}

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
object ShowcaseNtcParser extends NtcParser {
  def parse(ntcText: Iterator[String]): Doc = {
    //    val sentRegExp = """^\# S-ID:(.+) .*""".r
    val chunkRegExp: Regex =
      """^\* (\d+) (-?\d+)([A-Z]) .*""".r
    val tokenRegExp: Regex = """^([^\t]+)\t([^\t]+)\t([^\t]+)(\s*)(.*)""".r
    val pasRegExp: Regex = """^([^=]+)="(.+)"$""".r
    val blankRegExp: Regex = """\s*""".r

    var doc: Doc = null
    var sentences: ArrayBuffer[Chunks] = ArrayBuffer[Chunks]()
    var chunks: ArrayBuffer[Chunk] = ArrayBuffer[Chunk]()
    var tokens: ArrayBuffer[Token] = ArrayBuffer[Token]()

    var currentChunkId: Int = 0
    var currentHeadId: Int = 0
    var currentDepType: DepType.Value = DepType.D

    val sentenceId: Iterator[Int] = Stream.from(0).iterator
    var tokenId: Iterator[Int] = Stream.from(0).iterator
    ntcText foreach {
      case "EOD" =>
        doc = Doc(sentences)
        doc.sentences foreach {
          s: Chunks =>
            s.doc = doc
            s.chunks foreach {
              c: Chunk =>
                c.doc = doc
                c.tokens foreach(_.doc = doc)
          }
        }
      case "EOS" =>
        if (tokens.nonEmpty) {
          val chunk: Chunk = Chunk(currentChunkId, tokens, currentHeadId, currentDepType)
          chunk.tokens foreach (_.chunk = chunk)
          chunks += chunk

          val sentence: Chunks = Chunks(sentenceId.next, chunks)
          sentence.chunks foreach {
            c: Chunk =>
              c.sentence = sentence
              c.tokens foreach (_.sentence = sentence)
          }
          sentences += sentence
        }
        chunks = ArrayBuffer.empty[Chunk]
        tokens = ArrayBuffer.empty[Token]
        tokenId = Stream.from(0).iterator
      case chunkRegExp(id, headId, depType) =>
        if (tokens.nonEmpty) {
          val chunk: Chunk = Chunk(currentChunkId, tokens, currentHeadId, currentDepType)
          chunk.tokens foreach (_.chunk = chunk)
          chunks += chunk
        }

        currentChunkId = id.toInt
        tokens = ArrayBuffer.empty[Token]
        currentHeadId = headId.toInt
        currentDepType = depType match {
          case "P" => DepType.P
          case "A" => DepType.A
          case "I" => DepType.I
          case _ => DepType.D
        }
      case tokenRegExp(surface, posLine, bio, _, pasLine) =>
        val pasMap: Option[Map[String, String]] = pasLine match {
          case blankRegExp() => None
          case _ =>
            Some(pasLine.split(' ').collect {
              case pasRegExp(k, v) => (k, v)
            }.toMap)
        }
        val posList: Array[String] = posLine.split(',')
        val base: String = posList(4)
        val pos: String = posList(0)
        val detailedPos: Option[String] = posList(1) match {
          case "*" => None
          case p => Some(p)
        }
        val semantic: Option[String] = posList(6) match {
          case "*" => None
          case s => Some(s)
        }
        tokens += Token(tokenId.next, surface, base, pos, detailedPos, semantic, pasMap)
      case l => new IllegalStateException("Unexpected format: " concat l)
    }
    doc
  }
}