package util

import org.kohsuke.args4j.Option

import scala.beans.BeanProperty

/**
  * @author K.Sakamoto
  *         Created on 2016/11/05
  */
class JCasGenOption() {
  @Option(name = "-ts", aliases = Array[String]("--typeSystem"), usage = "type system descriptor directory", required = false)
  @BeanProperty
  var tsDir: String = Config.jCasGenTypeSystemDir

  @Option(name = "-o", aliases = Array[String]("--output"), usage = "output directory", required = false)
  @BeanProperty
  var outputDir: String = Config.jCasGenOutputDir
}
