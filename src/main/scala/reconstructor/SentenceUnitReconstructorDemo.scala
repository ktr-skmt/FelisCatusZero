package reconstructor

import java.io.{File, PrintStream}

import org.apache.commons.cli.{CommandLine, DefaultParser, HelpFormatter, Options}
import reconstructor.unit.{CombineUnit, DirectDependentsUnit, PAUnit}
import text.StringOption
import text.analyzer.JapaneseSentenceSplitter
import text.analyzer.dep.chapas.{ChaPAS, Doc, PasAnalyzer, Showcase}
import text.analyzer.dep.{Chunks, DepType}
import text.normalizer.NormalizedString
import util.{Color, FileOps, SentenceUnitReconstructorConfig}

/**
  * Created by nakayama.
  * @author Nakayama
  */
object SentenceUnitReconstructorDemo {
  def main(args: Array[String]): Unit = {
    val cl: CommandLine = parseArg(args)

    // Read configurations
    if (cl.hasOption("c")) SentenceUnitReconstructorConfig.set(new File(cl.getOptionValue("config")))

    // Parse args
    val pasAnalyzer: PasAnalyzer = if (cl.hasOption("showcase")) Showcase else ChaPAS
    val withColor: Boolean = cl.hasOption("color") || SentenceUnitReconstructorConfig.color
    val outputFile: Option[String] =
      if (cl.hasOption("o")) Some(cl.getOptionValue("output"))
      else SentenceUnitReconstructorConfig.outputFile match {
        case Some(d) if d != "" => Some(d)
        case _ => None
      }
    val outputStream: PrintStream = outputFile match {
      case Some(f) => FileOps.getPrintStream(f)
      case None => System.out
    }

    // Normalize sentences
    val originalText: String = ""//FileOps.getText(new File("res/nakayama/sample.txt"))
    val normalizedSentences: Seq[NormalizedString] = {
      for (sentence <- JapaneseSentenceSplitter.split(StringOption(originalText))) yield {
        NormalizedString(StringOption(sentence.text))
      }
    }

    // Parse sentences
    val pasDocs: Seq[Doc] = normalizedSentences.map(pasAnalyzer.parse)

    val dDependentsUnitsBySentence: Seq[Seq[DirectDependentsUnit]] =
      pasDocs.flatMap(_.sentences.map(_.pases.map(pas => new DirectDependentsUnit(pas))))
    val pasUnitsBySentence: Seq[Seq[PAUnit]] =
      pasDocs.flatMap(_.sentences.map(_.pases.map(pas => PAUnit(pas))))
    val filteredSentences: Seq[CombineUnit] =
      pasDocs.flatMap(_.sentences.map(new CombineUnit(_)))

    // Save cache
    if (SentenceUnitReconstructorConfig.cacheSave) pasAnalyzer.saveDocsCache(pasDocs)

    // Output
    FileOps.write(outputStream) { writer =>
      val filteredSentencesMap: Map[Chunks, CombineUnit] =
        filteredSentences.map { fs => fs.sentence -> fs }.toMap
      dDependentsUnitsBySentence zip pasUnitsBySentence foreach { case (dDependentsUnits, paUnits) =>
        writer.println("------------")
        val sentence: Chunks = dDependentsUnits.head.pas.predicate.sentence
        writer.println(if (withColor) Color.coloring(sentence) else sentence.text)
        // CaboCha output
        val normalizedSentence: NormalizedString =
          NormalizedString(StringOption(sentence.text))
        sentence.depTree = pasAnalyzer.cabochaTree(normalizedSentence).mkString("\n").stripLineEnd
        writer.println(sentence.depTree)
        writer.println()
        // ChaPAS output
        writer.println(sentence.ntcText)
        writer.println()

        writer.println("[pas]")
        paUnits.foreach(pa =>
          writer.println(if (withColor) pa.coloredRepresentative else pa.representative))
        writer.println()

        writer.println("[ddep]")
        dDependentsUnits.foreach(d =>
          writer.println(if (withColor) d.coloredRepresentative else d.representative))
        writer.println()

        val parallelChunks: Seq[String] = sentence.chunks.collect {
          case c if c.depType == DepType.P && !c.hasPredicates => "P: " + c
        }
        if (parallelChunks.nonEmpty) {
          writer.println("[parallel chunks]")
          parallelChunks.foreach(writer.println)
          writer.println()
        }

        writer.println("[ddep (complemented)]")
        dDependentsUnits.foreach(d =>
          writer.println(if (withColor) d.coloredRepresentative else d.complementedRepresentative))
        writer.println()

        writer.println("[combination]")
        filteredSentencesMap.get(sentence) match {
          case Some(fs) => fs.representatives.foreach(writer.println)
          case None =>
        }
        writer.println()

        writer.println("------------")
        writer.println()
      }
    }
  }

  final private[this] def parseArg(args: Array[String]): CommandLine = {
    val parser: DefaultParser = new DefaultParser()
    val options: Options = new Options()
    options.addOption("c", "config", true, "Configuration file path.")
    options.addOption("chapas", false, "Use ChaPAS as predicate argument structure analyzer. (default)")
    options.addOption("showcase", false, "Use Showcase as predicate argument structure analyzer.")
    options.addOption("color", false, "Coloring the output text for console.")
    options.addOption("o", "output", true, "Path for the output file.")

    val cl: CommandLine = parser.parse(options, args)
    if (cl.hasOption("h")) {
      new HelpFormatter().printHelp("SentenceReconstructor", options, true)
      System.exit(0)
    }

    cl
  }
}