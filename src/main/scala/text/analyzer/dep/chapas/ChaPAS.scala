package text.analyzer.dep.chapas

import java.nio.charset.{CodingErrorAction, StandardCharsets}

import text.StringNone
import text.analyzer.dep.cabocha.CaboCha
import text.normalizer.NormalizedString
import util.{ChaPASCache, SentenceUnitReconstructorConfig}
import util.process.EchoProcess
import util.process.ProcessBuilderUtils._

import scala.sys.process.Process
import scala.util.matching.Regex

/**
 * @author Nakayama
 *         Created on 15/10/31
 */
object ChaPAS extends PasAnalyzer with EchoProcess with ChaPASCache {
  /*
  private val cmd = Paths.get("/opt/local/share/chapas-0.742", "chapas.jar")
  def analyze(sentence: NormalizedStringOption): Stream[String] = {
    (echo(sentence) #| analyzer()).lines_!
  }
  private def analyzer(): ProcessBuilder = {
    Process(Seq("java", "-Xmx1g", "-jar", cmd.toAbsolutePath.toString, "-I", "RAW"))
  }
  private def echo(sentence: NormalizedStringOption): ProcessBuilder = {
    sentence match {
      case NormalizedStringSome(s) =>
        Process(Seq("echo", s.toString))
      case NormalizedStringNone =>
        Process(Nil)
    }
  }
*/

  private val chapasCmd: String = {
    SentenceUnitReconstructorConfig.chapas match {
      case Some(c) => c
      case None    => throw new RuntimeException("ChaPAS command was not found.")
    }
  }

  override def analyze(sentence: NormalizedString): Iterator[String] = {
    println(s"analyzing ...: [${sentence.toString.take(15).mkString}...]")
    echo(sentence).#|(Process(chapasCmd)).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone)
  }

  override def cabochaLattice(sentence: NormalizedString): Iterator[String] = {
    CaboCha.cabochaLatticeIPACmd(sentence).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone)
  }

  override def cabochaTree(sentence: NormalizedString): Iterator[String] = {
    CaboCha.cabochaTreeIPACmd(sentence).lineStream(
      StandardCharsets.UTF_8,
      CodingErrorAction.IGNORE,
      CodingErrorAction.IGNORE,
      StringNone)
  }

  override final protected[this] def textToDoc(normalizedSentence: NormalizedString): Doc = {
    (if (cacheEnable) {
      getDocCache(normalizedSentence)
    } else {
      None
    }).getOrElse(
      parser.parse(
        analyze(
          normalizedSentence
        )
      )
    )
  }

  override val parser: ChaPASNtcParser.type = ChaPASNtcParser

  //(1) noneからID=""
  //それ以前の文節はother
  //(2) noneからtype=
  //typeはpredicate, それ以前の文節はother
  //(3) ID=""からID=""
  //other
  //(4) ID=""からtype=
  //IDからtypeまでがother、typeがpredicate
  //(5) type=からID=""
  //other
  //(6) type=からtype=
  //other

  private val idRegex: Regex = "ID=\"(\\d+)\"".r
  private val gaRegex: Regex = "ga=\"(\\d+)\"".r
  private val oRegex: Regex  =  "o=\"(\\d+)\"".r
  private val niRegex: Regex = "ni=\"(\\d+)\"".r

  /*
  def main(args: Array[String]): Unit = {
    val morphemeBuffer = ListBuffer[String]()
    val bunsetsuBuffer = new StringBuilder()
    val buffer = ListBuffer[PredicateArgumentStructureAnalysisResult]()
    val map = mutable.Map[Int, PredicateArgumentStructureAnalysisResult]()
    var currectIdOpt: Option[Int] = None
    analyze(NormalizedStringOption(NormalizedString(StringOption(
      "政府は、地震、津波、豪雨などの影響により被害を受けた地域を支援する計画を発表した")))) foreach {
      line =>
          println(line)
          */
        /*
        if (line startsWith "*") {
          StringOption(bunsetsuBuffer.result()) match {
            case StringSome(bunsetsu) =>

            case StringNone =>
              //Do nothing
          }
          bunsetsuBuffer.clear()
        } else {
          val tokens = line split "\t"
          if (tokens.length == 4) {
            morphemeBuffer += line
            val text = tokens.head
            //val pos  = tokens(1)
            //val bio  = tokens(2)
            tokens(3) split " " foreach {
              case "type=\"pred\"" =>
              case "type=\"noun\"" =>
              case idRegex(id)     =>
              case gaRegex(ga)     =>
              case oRegex(o)       =>
              case niRegex(ni)     =>
                currectIdOpt = try {
                  Option(ni.toInt)
                } catch {
                  case e: NumberFormatException => None
                }
              case otherwise       =>
                //Do nothing
            }

            bunsetsuBuffer.append(text)
          }
        }
        */
//    }
//  }

  /*
  public class ChaPASOptions {
    @Option(
        name = "-c",
        usage = "chapas config file path"
    )
    public String configFileName = "chapas.conf";
    @Option(
        name = "-m",
        usage = "model file path"
    )
    public String modelFile = "models/chapas.model";
    @Option(
        name = "--use-gold-predicates",
        usage = "use gold annotations of predicates"
    )
    public boolean useGoldPredicateId = false;
    @Option(
        name = "--print-sentence-id",
        usage = "print sentence id"
    )
    public boolean printSentenceID = true;
    @Option(
        name = "-N",
        usage = "number of n-best"
    )
    public int numOfNBest = 64;
    @Option(
        name = "-if",
        usage = "input format"
    )
    public CaboChaFormat inputFormat;
    @Option(
        name = "-of",
        usage = "output format"
    )
    public CaboChaFormat outputFormat;
    @Option(
        name = "-pasN",
        usage = "number of PAS n-best"
    )
    public int numOfPASNBest;
    @Option(
        name = "-iter",
        usage = "number of iterations in training"
    )
    public int numOfIter;
    @Option(
        name = "-t",
        usage = "training data"
    )
    public String trainFileStr;
    @Option(
        name = "-td",
        usage = "training data directory"
    )
    public String trainDirStr;
    @Option(
        name = "-ted",
        usage = "test data directory"
    )
    public String testDirStr;
    @Option(
        name = "-od",
        usage = "output data directory"
    )
    public String outputDir;
    @Option(
        name = "--case-restricted-mode",
        usage = "case restriction mode"
    )
    public boolean caseRestrictedMode;
    public boolean useReranking;
    @Option(
        name = "-use-coord",
        usage = "use coordination analyzer"
    )
    public boolean useCoordinationAnalyzer;
    @Option(
        name = "-cm",
        usage = "model coordination analyzer file path"
    )
    public String coordModelFile;
    @Option(
        name = "--solver-type",
        usage = "liblinear solvertype"
    )
    public SolverType solverType;
    @Option(
        name = "-I",
        usage = "input mode"
    )
    public ChaPASInputMode chaPASInputMode;
    @Option(
        name = "-d",
        usage = "database type"
    )
    public ChaPASDBType dbType;
    @Argument
    private List<String> arguments;

    public ChaPASOptions() {
        this.inputFormat = CaboChaFormat.UNK;
        this.outputFormat = CaboChaFormat.UNK;
        this.numOfPASNBest = 1;
        this.numOfIter = 10;
        this.trainFileStr = null;
        this.trainDirStr = null;
        this.testDirStr = null;
        this.outputDir = null;
        this.caseRestrictedMode = false;
        this.useReranking = false;
        this.useCoordinationAnalyzer = true;
        this.coordModelFile = "models/coord.model";
        this.solverType = SolverType.L2R_LR;
        this.chaPASInputMode = ChaPASInputMode.SYN;
        this.dbType = ChaPASDBType.MAPDB;
        this.arguments = new ArrayList();
    }
  }
   */
}
