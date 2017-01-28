package text.analyzer.dep.chapas

import text.analyzer.dep.{Chunk, Chunks, DepType}

import scala.collection.mutable.ArrayBuffer
import scala.util.matching.Regex

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
object ChaPASNtcParser extends NtcParser {
  def parse(ntcText: Iterator[String]): Doc = {
    //    val sentRegExp = """^\# S-ID:(.+) .*""".r
    val chunkRegExp: Regex =
      """^\* (\d+) (-?\d+)([A-Z]) .*""".r
    val tokenRegExp: Regex = """^([^\t]+)\t([^\t]+)\t([^\t]+)(\s*)(.*)""".r
    val pasRegExp: Regex = """^([^=]+)="(.+)"$""".r
    val blankRegExp: Regex = """\s*""".r

    var sentences: ArrayBuffer[Chunks] = ArrayBuffer.empty[Chunks]
    val ntcFormatSentence: StringBuilder = new StringBuilder()
    var chunks: ArrayBuffer[Chunk] = ArrayBuffer.empty[Chunk]
    var tokens: ArrayBuffer[Token] = ArrayBuffer.empty[Token]

    var currentChunkId: Int = 0
    var currentHeadId: Int = 0
    var currentDepType: DepType.Value = DepType.D

    val sentenceId: Iterator[Int] = Stream.from(0).iterator
    var tokenId: Iterator[Int] = Stream.from(0).iterator
    ntcText foreach {
      case l@"EOS" =>
        ntcFormatSentence.append(l).append('\n')
        if (tokens.nonEmpty) {
          val chunk: Chunk = Chunk(currentChunkId, tokens, currentHeadId, currentDepType)
          chunk.tokens.foreach(_.chunk = chunk)
          chunks += chunk

          val sentence: Chunks = Chunks(sentenceId.next, chunks)
          sentence.ntcText = ntcFormatSentence.result
          sentence.chunks.foreach {
            c: Chunk =>
              c.sentence = sentence
              c.tokens.foreach(_.sentence = sentence)
          }
          sentences += sentence
        }
        ntcFormatSentence.setLength(0)
        chunks = ArrayBuffer.empty[Chunk]
        tokens = ArrayBuffer.empty[Token]
        tokenId = Stream.from(0).iterator
      case l@chunkRegExp(id, headId, depType) =>
        ntcFormatSentence.append(l).append('\n')
        if (tokens.nonEmpty) {
          val chunk: Chunk = Chunk(currentChunkId, tokens, currentHeadId, currentDepType)
          chunk.tokens.foreach(_.chunk = chunk)
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
      case l@tokenRegExp(surface, posLine, bio, _, pasLine) =>
        ntcFormatSentence.append(l).append("\n")
        val pasMap: Option[Map[String, String]] = pasLine match {
          case blankRegExp() => None
          case _ =>
            Some(pasLine.split(' ').collect {
              case pasRegExp(k, v) => (k, v)
            }.toMap)
        }
        val posList: Array[String] = posLine.split(',')
        val base: String = posList(6)
        val pos: String = posList(0)
        val detailedPos: Option[String] = posList(1) match {
          case "*" => None
          case p => Some(p)
        }
        tokens += Token(tokenId.next, surface, base, pos, detailedPos, None, pasMap)
      case l => new IllegalStateException("Unexpected format: " concat l)
    }
    val doc: Doc = Doc(sentences)
    doc.sentences.foreach {
      s: Chunks =>
        s.doc = doc
        s.chunks.foreach {
          c: Chunk =>
            c.doc = doc
            c.tokens.foreach(_.doc = doc)
        }
    }
    doc
  }
}