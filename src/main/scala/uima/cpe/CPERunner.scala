package uima.cpe

import java.io.{BufferedReader, File, InputStreamReader}

import ir.fulltext.indri.{IndriCharactereLevelIndexer, IndriWordLevelIndexer}
import org.apache.uima.UIMAFramework
import org.apache.uima.cas.CAS
import org.apache.uima.collection._
import org.apache.uima.collection.metadata.CpeDescription
import org.apache.uima.util.XMLInputSource
import org.kohsuke.args4j.{CmdLineException, CmdLineParser}
import text.vector.wordembedding.fastText.FastTextVectorGenerator
import text.{StringNone, StringOption, StringSome}
import uima.fc.{EssayGeneratorFlowController, FlowController, InformationRetrieverFlowController, QuestionAnalyzerFlowController}
import util.{Config, JCasGen}

import scala.util.control.Breaks

/**
  * @author K.Sakamoto
  *         Created on 15/10/30
  */
object CPERunner extends Thread {
  private var CpeOption: Option[CollectionProcessingEngine] = None
  private var startTimeOption:        Option[Long] = None
  private var initCompleteTimeOption: Option[Long] = None

  private def startPoint(code: StringOption): IntermediatePoint = {
    intermediatePoint(code, IntermediatePoint.EssayQuestionReader)
  }

  private def endPoint(code: StringOption): IntermediatePoint = {
    intermediatePoint(code, IntermediatePoint.EssayEvaluator)
  }

  private def intermediatePoint(code: StringOption, default: IntermediatePoint): IntermediatePoint = {
    code match {
      case StringSome(intermediatePoint) =>
        intermediatePoint.toLowerCase match {
          case "cr" | "essayquestionreader" =>
            IntermediatePoint.EssayQuestionReader
          case "qa" | "questionanalyzer" =>
            IntermediatePoint.QuestionAnalyzer
          case "ir" | "informationretriever" =>
            IntermediatePoint.InformationRetriever
          case "eg" | "essaygenerator" =>
            IntermediatePoint.EssayGenerator
          case "w" | "essaywriter" =>
            IntermediatePoint.EssayWriter
          case "e" | "essayevaluator" =>
            IntermediatePoint.EssayEvaluator
          case _ =>
            default
        }
      case StringNone =>
        default
    }
  }

  private def preProcess(option: CPERunnerOption): Unit = {
    if (option.getDoJCasGen) {
      Config.doJCasGenAsPreprocess = true
    }

    if (option.getDoCharacterLevelIndriIndex) {
      Config.doCharacterLevelIndriIndexAsPreprocess = true
    }

    if (option.getDoWordLevelIndriIndex) {
      Config.doWordLevelIndriIndexAsPreprocess = true
    }

    if (option.getDoFastText) {
      Config.doFastTestAsPreprocess = true
    }

    if (Config.doJCasGenAsPreprocess) {
      JCasGen.main(Array.empty[String])
    }

    if (Config.doCharacterLevelIndriIndexAsPreprocess) {
      IndriCharactereLevelIndexer.run()
    }

    if (Config.doWordLevelIndriIndexAsPreprocess) {
      IndriWordLevelIndexer.run()
    }

    if (Config.doFastTestAsPreprocess) {
      FastTextVectorGenerator.main(Array.empty[String])
    }
  }

  @throws[Exception]
  def main(args: Array[String]): Unit = {
    val option: CPERunnerOption = new CPERunnerOption()
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

    preProcess(option)

    startTimeOption = Option(System.nanoTime)
    println(">> CPE Processing")
    val startPointOpt: StringOption = StringOption(option.getStartPoint)
    val startPointValue: IntermediatePoint = startPoint(startPointOpt)

    val endPointOpt: StringOption = StringOption(option.getEndPoint)
    val endPointValue: IntermediatePoint = endPoint(endPointOpt)

    if (endPointValue.id < startPointValue.id) {
      System.err.println("Error: The start point was after the end point. They must be in regular order.")
      System.exit(1)
    }

    // Flow Controller
    FlowController.analysisEngineList.clear

    def needFlow(intermediatePoint: IntermediatePoint): Boolean = {
      startPointValue.id <= intermediatePoint.id && intermediatePoint.id <= endPointValue.id
    }

    val needQuestionAnalyzer:     Boolean = needFlow(IntermediatePoint.QuestionAnalyzer)
    val needInformationRetriever: Boolean = needFlow(IntermediatePoint.InformationRetriever)
    val needEssayGenerator:       Boolean = needFlow(IntermediatePoint.EssayGenerator)
    val needEssayWriter:          Boolean = needFlow(IntermediatePoint.EssayWriter)
    val needEssayEvaluator:       Boolean = needFlow(IntermediatePoint.EssayEvaluator)

    if (needQuestionAnalyzer) {
      FlowController.analysisEngineList += "questionAnalyzerAAEDescriptor"
      QuestionAnalyzerFlowController.analysisEngineList += "questionAnalyzerAEDescriptor"
    } else {
      QuestionAnalyzerFlowController.analysisEngineList.clear
    }

    if (needInformationRetriever) {
      FlowController.analysisEngineList += "informationRetrieverAAEDescriptor"
      InformationRetrieverFlowController.analysisEngineList += "informationRetrieverAEDescriptor"
    } else {
      InformationRetrieverFlowController.analysisEngineList.clear
    }

    if (needEssayGenerator || needEssayWriter || needEssayEvaluator) {
      FlowController.analysisEngineList += "essayGeneratorAAEDescriptor"

      if (needFlow(IntermediatePoint.EssayGenerator)) {
        EssayGeneratorFlowController.analysisEngineList += "essayGeneratorAEDescriptor"
      }

      if (needFlow(IntermediatePoint.EssayWriter)) {
        EssayGeneratorFlowController.analysisEngineList += "essayWriterCCDescriptor"
      }

      if (needFlow(IntermediatePoint.EssayEvaluator)) {
        EssayGeneratorFlowController.analysisEngineList += "essayEvaluatorCCDescriptor"
      }
    } else {
      EssayGeneratorFlowController.analysisEngineList.clear
    }

    val gzipXmiCasConsumerDescriptor: String = "gzipXmiCasConsumerDescriptor"

    if (option.unSave != "all") {
      val unSavedStates: Array[String] = option.unSave.split(',').map(_.trim.toLowerCase)
      if (!(unSavedStates.contains("qa") || unSavedStates.contains("questionanalyzer"))) {
        QuestionAnalyzerFlowController.analysisEngineList += gzipXmiCasConsumerDescriptor
      }
      if (!(unSavedStates.contains("ir") || unSavedStates.contains("informationretriever"))) {
        InformationRetrieverFlowController.analysisEngineList += gzipXmiCasConsumerDescriptor
      }
      if (!(unSavedStates.contains("eg") || unSavedStates.contains("essaygenerator"))) {
        EssayGeneratorFlowController.analysisEngineList += gzipXmiCasConsumerDescriptor
      }
    }

    val useIntermediatePoint: Boolean = {
      startPointValue match {
        case IntermediatePoint.InformationRetriever |
             IntermediatePoint.EssayGenerator |
             IntermediatePoint.EssayWriter |
             IntermediatePoint.EssayEvaluator =>
          true
        case _ =>
          endPointValue match {
            case IntermediatePoint.QuestionAnalyzer |
                 IntermediatePoint.InformationRetriever =>
              true
            case _ =>
              false
          }
      }
    }

    val filePath: String = {
      new File(
        raw"src/main/resources/desc/cpe/cpe${if (useIntermediatePoint) "FromIntermediatePoint" else ""}Descriptor.xml"
      ).toPath.toAbsolutePath.toString
    }

    val cpeDesc: CpeDescription = UIMAFramework.getXMLParser.parseCpeDescription(new XMLInputSource(filePath))
    CpeOption = Option(UIMAFramework.produceCollectionProcessingEngine(cpeDesc))
    CpeOption match {
      case Some(cpe) =>
        /*
        val casProcessors: Array[CasProcessor] = cpe.getCasProcessors
        val aae: AnalysisEngine = casProcessors(0).asInstanceOf[AnalysisEngine]
        val essayEvaluator: CasConsumer = casProcessors(1).asInstanceOf[CasConsumer]
        */
        if (useIntermediatePoint) {
          val collectionReader: CollectionReader = cpe.getCollectionReader.asInstanceOf[CollectionReader]
          collectionReader.setConfigParameterValue(
            "InputDirectory",
            s"out/xmi/${
              startPointValue match {
                case IntermediatePoint.InformationRetriever =>
                  "qa"
                case IntermediatePoint.EssayGenerator =>
                  "ir"
                case IntermediatePoint.EssayWriter | IntermediatePoint.EssayEvaluator =>
                  "eg"
                case _ =>
                  "qa"
              }
            }"
          )
          println(">> Collection Reader Reconfiguration Started")
          collectionReader.reconfigure()
        }

        cpe.addStatusCallbackListener(new StatusCallbackListenerImpl())
        cpe.process()

        val loop: Breaks = new Breaks()
        loop.breakable {
          Iterator.continually(Option(
            new BufferedReader(
              new InputStreamReader(
                System.in)).readLine)) foreach {
            line: Option[String] =>
              if (line.isDefined && (line.get == "abort") && cpe.isProcessing) {
                println("Aborting...")
                cpe.stop()
                loop.break()
              }
          }
        }
      case None =>
      //Do nothing
    }
  }

  class StatusCallbackListenerImpl extends StatusCallbackListener {
    private var entityCount: Int = 0
    private var size: Int = 0

    override def entityProcessComplete(aCAS: CAS, aStatus: EntityProcessStatus): Unit = {
      if (aStatus.isException) {
        val exceptions: java.util.List[Exception] = aStatus.getExceptions
        for (i <- 0 until exceptions.size) {
          exceptions.get(i).asInstanceOf[Throwable].printStackTrace()
        }
        return
      }
      entityCount += 1
      val docText: Option[String] = Option(aCAS.getDocumentText)
      docText match {
        case Some(dt) =>
          size += dt.codePointCount(0, dt.length)
        case None =>
        //Do nothing
      }
    }

    override def resumed(): Unit = {
      println("Resumed")
    }

    override def initializationComplete(): Unit = {
      println("CPM Initialization Complete")
      initCompleteTimeOption = Option(System.nanoTime)
    }

    override def paused(): Unit = {
      println("Paused")
    }

    private def printNumberOfDocumentsAndCharacters(): Unit = {
      printf("Completed %d documents", entityCount)
      if (0 < size) {
        printf("; %d characters", size)
      }
      println()
    }

    override def collectionProcessComplete(): Unit = {
      val time: Option[Long] = Option(System.nanoTime)
      printNumberOfDocumentsAndCharacters()
      val initTime:       Long = initCompleteTimeOption.getOrElse(0L) - startTimeOption.getOrElse(0L)
      val processingTime: Long = time.getOrElse(0L) - initCompleteTimeOption.getOrElse(0L)
      val elapsedTime:    Long = initTime + processingTime

      printf("Total Time Elapsed: %d nano seconds%n", elapsedTime)
      printf("Initialization Time: %d nano seconds%n", initTime)
      printf("Processing Time: %d nano seconds%n", processingTime)

      CpeOption match {
        case Some(cpe) =>
          printf("%n%n ------------------ PERFORMANCE REPORT ------------------%n%n")
          println(cpe.getPerformanceReport.toString)
        case None =>
        //Do nothing
      }

      // exit safely
      System.exit(0)
    }

    override def batchProcessComplete(): Unit = {
      printNumberOfDocumentsAndCharacters()
      printf("Time Elapsed: %d nano seconds%n", System.nanoTime - startTimeOption.getOrElse(0L))

      // exit safely
      System.exit(0)
    }

    override def aborted(): Unit = {
      println("Aborted")
      System.exit(1)
    }
  }
}