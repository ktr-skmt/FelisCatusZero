package util

import java.io.PrintWriter
import java.nio.file.{Files, Paths}

/**
 * <pre>
 * Created on 2014/11/07
 * </pre>
 * @author K.Sakamoto
 */
object Logger {
  private val logPath = Paths.get(
    System getProperty "user.home",
    ".essay",
    "log"
  )

  def logWriter: PrintWriter = {
    new PrintWriter(Files.newBufferedWriter(logPath, StaticValue.defaultCharset))
  }
}
