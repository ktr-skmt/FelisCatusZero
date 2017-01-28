package ir.fulltext.indri

import java.nio.charset.{CodingErrorAction, StandardCharsets}
import java.nio.file.Path

import text.StringNone
import util.Config

import scala.collection.mutable.ListBuffer
import scala.sys.process.Process

/**
  * <pre>
  * Created on 2017/01/13.
  * </pre>
  *
  * @author K.Sakamoto
  */
class IndriIndex(inputPath: Path, indexPath: Path) {
  private def command: Seq[String] = {
    Seq[String](
      "IndriBuildIndex",
      "-field.name=TITLE",
      "-memory=".concat(Config.indriMemory),
      "-corpus.path=".concat(inputPath.toAbsolutePath.toString),
      "-corpus.class=trectext",
      "-index=".concat(indexPath.toAbsolutePath.toString)
    )
  }

  def index(): Unit = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    command.foreach(buffer.+=)
    import util.process.ProcessBuilderUtils._
    Process(buffer.result).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone
    ).foreach(println)
  }
}
