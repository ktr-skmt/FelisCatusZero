package util

import java.io.BufferedReader
import java.nio.charset.{Charset, StandardCharsets}
import java.nio.file.{Files, Path}

/**
 * <pre>
 * Created on 2014/11/01
 * </pre>
 * @author K.Sakamoto
 */
object StaticValue {
  val defaultCharset: Charset = StandardCharsets.UTF_8

  val lineSeparator: String = System.lineSeparator

  def reader(path: Path): BufferedReader = {
    Files.newBufferedReader(path, defaultCharset)
  }
}
