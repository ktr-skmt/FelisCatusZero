package converter

import java.io.File
import java.nio.file.Path

/**
  * <pre>
  * Created on 2017/01/13.
  * </pre>
  *
  * @author K.Sakamoto
  */
class ExtensionReviser(extension: String) {
  def revise(path: Path): Path = {
    new File(
      revise(
        path.toAbsolutePath.toString
      )
    ).toPath
  }

  def revise(fileName: String): String = {
    val period: Char = '.'
    val tokens: Array[String] = fileName.split(period).init
    val builder: StringBuilder = new StringBuilder()
    tokens.foreach {
      s: String =>
        builder.
          append(s).
          append(period)
    }
    builder.
      append(extension).
      result
  }
}
