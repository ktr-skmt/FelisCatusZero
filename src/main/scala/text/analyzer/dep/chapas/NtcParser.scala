package text.analyzer.dep.chapas

import java.io.File

import scala.io.Source

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
abstract class NtcParser {
  def parse(ntcFile: File): Doc = parse(Source.fromFile(ntcFile).getLines)

  def parse(ntcText: Iterator[String]): Doc
}
