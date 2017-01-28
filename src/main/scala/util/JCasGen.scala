package util

import java.io.File

import org.apache.uima.UIMAFramework
import org.apache.uima.cas.CAS
import org.apache.uima.cas.impl.CASImpl
import org.apache.uima.resource.metadata.TypeSystemDescription
import org.apache.uima.tools.jcasgen.{Jg, LogThrowErrorImpl, UimaLoggerProgressMonitor}
import org.apache.uima.util.{CasCreationUtils, XMLInputSource}
import org.kohsuke.args4j.{CmdLineException, CmdLineParser}

/**
 * <pre>
 * Created on 5/10/15.
 * </pre>
 * @author K.Sakamoto
 */
object JCasGen {
  def main(args: Array[String]): Unit = {
    val option: JCasGenOption = new JCasGenOption()
    if (0 < args.length) {
      val parser: CmdLineParser = new CmdLineParser(option)
      try {
        parser.parseArgument(args: _*)
      } catch {
        case e: CmdLineException =>
          parser.printUsage(System.out)
          System.exit(0)
      }
    }

    val tsDir:     String = option.getTsDir
    val outputDir: String = option.getOutputDir

    val tsDirFile: File = new File(tsDir)
    if (tsDirFile == null) {
      return
    }
    if (!tsDirFile.isDirectory) {
      return
    }

    val typeSystemFiles: Array[File] = tsDirFile.listFiles
    typeSystemFiles foreach {
      typeSystemFile: File =>
        val jg: Jg = new Jg()
        val xmlIS: XMLInputSource = new XMLInputSource(typeSystemFile)
        val tsd: TypeSystemDescription = UIMAFramework.getXMLParser.parseTypeSystemDescription(xmlIS)
        val cas: CAS = CasCreationUtils.createCas(tsd, null, null)

        jg.mainForCde(
          null,
          new UimaLoggerProgressMonitor(),
          new LogThrowErrorImpl(),
          typeSystemFile.toString,
          outputDir,
          tsd.getTypes,
          cas.asInstanceOf[CASImpl]
        )
    }
  }
}
