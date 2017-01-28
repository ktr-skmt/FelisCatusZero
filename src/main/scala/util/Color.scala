package util

import text.analyzer.dep.chapas.{PAS, Token}
import text.analyzer.dep.{Chunk, Chunks}

/**
  * Created by nakayama.
  * @author Nakayama
  */
object Color {
  final val RESET: String = "\u001B[0m"
  final val UNDERLINE: String = "\u001B[4m"
  final val BLACK: String = "\u001B[30m"
  final val RED: String = "\u001B[31m"
  final val GREEN: String = "\u001B[32m"
  final val YELLOW: String = "\u001B[33m"
  final val BLUE: String = "\u001B[34m"
  final val PURPLE: String = "\u001B[35m"
  final val CYAN: String = "\u001B[36m"
  final val WHITE: String = "\u001B[37m"

  def coloring(sentence: Chunks): String = {
    sentence.chunks.flatMap(c => coloring(c)).mkString
  }

  def coloring(chunk: Chunk): String = {
    coloring(chunk.tokens)
  }

  def coloring(tokens: Seq[Token]): String = {
    tokens.map {
      t: Token =>
        val s = new StringBuilder()
        if (t.isPredicate) {
          s.append(BLUE)
        }
        if (t.argId.isInstanceOf[Some[Int]]) {
          s.append(UNDERLINE)
        }
        s.append(t).
          append(RESET).
          result
    }.mkString
  }

  def coloring(chunk: Chunk, pas: PAS): String = {
    coloring(chunk.tokens, pas)
  }

  def coloring(tokens: Seq[Token], pas: PAS): String = {
    tokens.map(coloring(_, pas)).mkString
  }

  def coloring(token: Token, pas: PAS): String = {
    val s: StringBuilder = new StringBuilder()
    if (token == pas.predicate) {
      s.append(BLUE)
    }
    if (pas.arguments.values.exists(_ == token)) {
      s.append(UNDERLINE)
    }
    s.append(token).
      append(RESET).
      result
  }

}