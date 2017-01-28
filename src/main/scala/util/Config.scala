package util

import java.io.File
import java.nio.file.{Path, Paths}
import java.time.format.DateTimeFormatter
import java.time.{ZoneId, ZonedDateTime}

import com.typesafe.config.{ConfigFactory, Config => TypeSafeConfig}
import net.ceedubs.ficus.Ficus._
import score.{Granularity, ScorerType}
import text.{StringNone, StringOption}
import text.similarity.{Dissimilarity, Overlap, Similarity}

/**
  * @author K.Sakamoto
  *         Created on 2016/07/25
  */
object Config {
  val timestamp: String = {
    val now: ZonedDateTime = ZonedDateTime.now(ZoneId.systemDefault)
    DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(now)
  }

  final private[this] var config: TypeSafeConfig = ConfigFactory.load()

  def set(configFile: File): Unit = {
    config = ConfigFactory.load(ConfigFactory.parseFile(configFile))
  }

  lazy val systemName: String = config.as[Option[String]]("systemName").getOrElse("Baseline")

  var doJCasGenAsPreprocess: Boolean = config.as[Option[Boolean]]("preprocess.doJCasGen").getOrElse(false)

  var doCharacterLevelIndriIndexAsPreprocess: Boolean = config.as[Option[Boolean]]("preprocess.doCharacterLevelIndriIndex").getOrElse(false)

  var doWordLevelIndriIndexAsPreprocess: Boolean = config.as[Option[Boolean]]("preprocess.doWordLevelIndriIndex").getOrElse(false)

  var doFastTestAsPreprocess: Boolean = config.as[Option[Boolean]]("preprocess.doFastText").getOrElse(false)

  lazy val numOfScores: Int = config.as[Option[Int]]("numOfScores").getOrElse(3)

  var useTermNormalizer: Boolean = true

  lazy val wantToBrowse: Boolean = config.as[Option[Boolean]]("wantToBrowse").getOrElse(false)

  lazy val fastTextResource: String = config.as[Option[String]]("wordEmbedding.fastText.resource").getOrElse("out/fastText/data.txt")

  lazy val fastTextModel: String = config.as[Option[String]]("wordEmbedding.fastText.model").getOrElse("out/fastText/model")

  lazy val fastTextModelBin: String = fastTextModel concat ".bin"

  lazy val fastTextModelVec: String = fastTextModel concat ".vec"

  lazy val fastTextQuery: String = config.as[Option[String]]("wordEmbedding.fastText.query").getOrElse("out/fastText/query.txt")

  lazy val indriCount: Int = config.as[Option[Int]]("indri.count").getOrElse(100)

  lazy val indriMemory: String = config.as[Option[String]]("indri.memory").getOrElse("1200m")

  lazy val characterLevelIndriSegmentations: Seq[String] =
    config.as[Option[Seq[String]]]("indri.segmentation.characterLevel.textbook.ja").getOrElse(Nil) ++
    config.as[Option[Seq[String]]]("indri.segmentation.characterLevel.glossary.ja").getOrElse(Nil)

  lazy val wordLevelIndriSegmentations: Seq[String] =
    config.as[Option[Seq[String]]]("indri.segmentation.wordLevel.textbook.ja").getOrElse(Nil) ++
    config.as[Option[Seq[String]]]("indri.segmentation.wordLevel.glossary.ja").getOrElse(Nil)

  lazy val characterLevelIndriIndices: Seq[String] =
    config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.characterLevel.textbook.ja").getOrElse(Nil) ++
    config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.characterLevel.glossary.ja").getOrElse(Nil)

  lazy val wordLevelIndriIndices: Seq[String] =
    config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.wordLevel.textbook.ja").getOrElse(Nil) ++
    config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.wordLevel.glossary.ja").getOrElse(Nil)

  lazy val trecTextFormatData: Seq[String] = {
    config.as[Option[Seq[String]]]("knowledgeSource.trecTextFormat.textbook.ja").getOrElse(Nil) ++
    config.as[Option[Seq[String]]]("knowledgeSource.trecTextFormat.glossary.ja").getOrElse(Nil)
  }

  lazy val trecTextFormatGlossary: Seq[Path] = {
    config.as[Option[Seq[String]]]("knowledgeSource.trecTextFormat.glossary.ja").getOrElse(Nil).map(path => Paths.get(path, "yamakawa_world_history_glossary.xml"))
  }

  lazy val centerTestJaXmlDir: String = Paths.get(config.as[Option[String]]("exam.ja.nationalCenterTestDir").getOrElse("res/national_center_test/question/ja")).toAbsolutePath.toString

  lazy val centerTestEnXmlDir: String = Paths.get(config.as[Option[String]]("exam.en.nationalCenterTestDir").getOrElse("res/national_center_test/question/en")).toAbsolutePath.toString

  lazy val indriGeoCharacterlevelIndices: Seq[String] = config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.characterLevel.geo.ja").getOrElse(Nil)

  lazy val geoNameList: String = config.as[Option[String]]("knowledgeSource.geo.ja.nameList").getOrElse("wh/res/geo/list.txt")

  lazy val indriWikipediaCharacterLevelIndices: Seq[String] = config.as[Option[Seq[String]]]("knowledgeSource.indriIndex.characterLevel.wikipedia.ja").getOrElse(Nil)

  lazy val glossaryEntriesForTimeExtractor: String = config.as[Option[String]]("knowledgeSource.time.ja.listFromGlossary").getOrElse("res/geotime/time_extracted_from_glossary.csv")

  lazy val eventOntologyClass: Path = Paths.get(config.as[Option[String]]("knowledgeSource.eventOntology.class").getOrElse("src/main/resources/ontology/class/"))

  lazy val secondExamXmlDir: String = Paths.get(config.as[Option[String]]("exam.ja.secondStageExaminationDir").getOrElse("res/second_stage_examination/ja")).toAbsolutePath.toString

  final lazy val limitOfSentenceSelection: Int = config.as[Option[Int]]("sentenceSelection.limit").getOrElse(3)

  final lazy val needInitialSentenceTimeAnalysisByTextbook: Boolean = config.as[Option[Boolean]]("needInitialSentenceTimeAnalysisByTextbook").getOrElse(false)

  final lazy val essayExamDirOpt: Option[String] = config.as[Option[String]]("exam.ja.essayExamDir")

  final lazy val jCasGenTypeSystemDir: String = config.as[Option[String]]("jCasGen.typeSystemDir").getOrElse("../../src/main/resources/desc/ts")

  final lazy val jCasGenOutputDir: String = config.as[Option[String]]("jCasGen.outputDir").getOrElse("../../src/main/java")

  final lazy val resourcesDir: String = config.as[Option[String]]("resourcesDir").getOrElse("../../src/main/resources/")

  final lazy val usePassage: Boolean = config.as[Option[Boolean]]("passage.use").getOrElse(false)

  final lazy val passageWindow: Int = config.as[Option[Int]]("passage.window").getOrElse(3)

  private def dicDir(defaultDir: String, dir: String): String = {
    config.as[Option[String]](dir) match {
      case Some(s) if s != "" =>
        val f: File = new File(s)
        if (f.canRead) {
          f.toPath.toAbsolutePath.toString
        } else {
          defaultDir
        }
      case _ =>
        defaultDir
    }
  }

  final lazy val mainMorphemeAnalyzer: String = config.as[Option[String]]("analyzer.mainMorphemeAnalyzer").getOrElse("IPADicMeCab")

  final lazy val useIpadicMecab: Boolean = config.as[Option[Boolean]]("analyzer.mecab.ipadic.use").getOrElse(false)

  final lazy val useIpadicCabocha: Boolean = {
    if (useIpadicMecab) {
      config.as[Option[Boolean]]("analyzer.mecab.ipadic.cabocha.use").getOrElse(false)
    } else {
      false
    }
  }

  final lazy val useIpadicChaPAS: Boolean = {
    if (useIpadicMecab && useIpadicCabocha) {
      config.as[Option[Boolean]]("analyzer.mecab.ipadic.use").getOrElse(false)
    } else {
      false
    }
  }

  final lazy val useUnidicMecab: Boolean = config.as[Option[Boolean]]("analyzer.mecab.unidic.use").getOrElse(false)

  final lazy val useUnidicCabocha: Boolean = config.as[Option[Boolean]]("analyzer.mecab.unidic.cabocha.use").getOrElse(false)

  final lazy val useJumandicMecab: Boolean = config.as[Option[Boolean]]("analyzer.mecab.jumandic.use").getOrElse(false)

  final lazy val useJumandicCabocha: Boolean = config.as[Option[Boolean]]("analyzer.mecab.jumandic.cabocha.use").getOrElse(false)

  final lazy val mecabIPADicDir: String = dicDir("/usr/local/lib/mecab/dic/ipadic", "analyzer.mecab.ipadic.dir")

  final lazy val mecabIPADicUserDic: StringOption = config.as[Option[String]]("analyzer.mecab.ipadic.userDic") match {
    case Some(userDic) => StringOption(userDic)
    case None => StringNone
  }

  final lazy val mecabUnidicDir: String = dicDir("/usr/local/lib/mecab/dic/unidic", "analyzer.mecab.unidic.dir")

  final lazy val mecabUnidicUserDic: StringOption = config.as[Option[String]]("analyzer.mecab.unidic.userDic") match {
    case Some(userDic) => StringOption(userDic)
    case None => StringNone
  }

  final lazy val mecabJumandicDir: String = dicDir("/usr/local/lib/mecab/dic/jumandic", "analyzer.mecab.jumandic.dir")

  final lazy val useIpadicChasen: Boolean = config.as[Option[Boolean]]("analyzer.chasen.ipadic.use").getOrElse(false)

  final lazy val useUnidicChasen: Boolean = config.as[Option[Boolean]]("analyzer.chasen.unidic.use").getOrElse(false)

  final lazy val useJumandicChasen: Boolean = config.as[Option[Boolean]]("analyzer.chasen.jumandic.use").getOrElse(false)

  final lazy val useNaistdicChasen: Boolean = config.as[Option[Boolean]]("analyzer.chasen.naistdic.use").getOrElse(false)

  final lazy val chasenIPADicDir: String = dicDir("/usr/local/lib/chasen/dic/ipadic", "analyzer.chasen.ipadic.dir")

  final lazy val chasenUnidicDir: String = dicDir("/usr/local/lib/chasen/dic/unidic", "analyzer.chasen.unidic.dir")

  final lazy val chasenNAISTDicDir: String = dicDir("/usr/local/lib/chasen/dic/naistdic", "analyzer.chasen.naistdic.dir")

  final lazy val useJuman: Boolean = config.as[Option[Boolean]]("analyzer.juman.use").getOrElse(false)

  final lazy val useJumanPlusPlus: Boolean = config.as[Option[Boolean]]("analyzer.jumanplusplus.use").getOrElse(false)

  final lazy val useKNP: Boolean = {
    if (useJuman) {
      config.as[Option[Boolean]]("analyzer.knp.use").getOrElse(false)
    } else {
      false
    }
  }

  final lazy val useKyTea: Boolean = config.as[Option[Boolean]]("analyzer.kytea.use").getOrElse(false)

  final lazy val nGram: Int = config.as[Option[Int]]("concept.nGram.n") match {
    case Some(n) if 1 <= n => n
    case _                 => 1
  }

  final lazy val nGramGap: Int = config.as[Option[Int]]("concept.nGram.gap") match {
    case Some(gap) if 0 <= gap => gap
    case Some(gap) if gap < 0  => Int.MaxValue
    case _                     => 0
  }

  final lazy val granularity: Granularity.Value = config.as[Option[String]]("vector.granularity") match {
    case Some(g) =>
      g.toLowerCase() match {
        case "sentence" =>
          Granularity.Sentence
        case "sentences" =>
          Granularity.Sentences
        case _ =>
          Granularity.None
      }
    case None =>
      Granularity.None
  }

  final lazy val updateTypeScorer: ScorerType.Value = {
    config.as[Option[String]]("vector.updateTypeScorer") match {
      case Some(s) =>
        s.toLowerCase match {
          case "entailment" =>
            ScorerType.Entailment
          case "relevance" =>
            ScorerType.Relevance
          case _ =>
            ScorerType.None
        }
      case None =>
        ScorerType.None
    }
  }

  final lazy val noUpdateTypeScorer: ScorerType.Value = {
    config.as[Option[String]]("vector.noUpdateTypeScorer") match {
      case Some(s) =>
        s.toLowerCase match {
          case "entailment" =>
            ScorerType.Entailment
          case "relevance" =>
            ScorerType.Relevance
          case _ =>
            ScorerType.None
        }
      case None =>
        ScorerType.None
    }
  }

  final lazy val indriTfidfK1: String = config.as[Option[String]]("indri.tfidf.k1").getOrElse("1.2")

  final lazy val indriTfidfB: String = config.as[Option[String]]("indri.tfidf.b").getOrElse("0.75")

  final lazy val indriBm25K1: String = config.as[Option[String]]("indri.bm25.k1").getOrElse("1.2")

  final lazy val indriBm25B: String = config.as[Option[String]]("indri.bm25.b").getOrElse("0.75")

  final lazy val indriBm25K3: String = config.as[Option[String]]("indri.bm25.k3").getOrElse("7")

  final lazy val lambdaOfMMR: Double = {
    config.as[Option[Double]]("mmr.lambda").getOrElse(0.5D)
  }

  final lazy val isFrequencyOtherwiseBinary: Boolean = config.as[Option[Boolean]]("vector.isFrequencyOtherwiseBinary").getOrElse(false)

  final lazy val tokenizer: String = config.as[Option[String]]("concept.tokenizer").getOrElse("CharacterNGram")

  final lazy val sentenceSplitter: String = config.as[Option[String]]("vector.concept.sentenceSplitter").getOrElse("none")

  final lazy val tverskyA: Double = config.as[Option[Double]]("vector.tverskyA").getOrElse(1D)

  final lazy val tverskyB: Double = config.as[Option[Double]]("vector.tverskyA").getOrElse(1D)

  final lazy val minkowskyQ: Double = config.as[Option[Double]]("vector.minkowskyQ").getOrElse(2D)

  final lazy val fScoreBeta: Double = config.as[Option[Double]]("vector.fScoreBeta").getOrElse(1D)

  final lazy val jaroWinklerThreshold: Double = config.as[Option[Double]]("vector.jaroWinklerThreshold").getOrElse(0.7D)

  final lazy val jaroWinklerScalingFactor: Double = config.as[Option[Double]]("vector.jaroWinklerScalingFactor").getOrElse(0.1D)

  final lazy val damerauLevenshteinDeleteCost: Double = config.as[Option[Double]]("vector.damerauLevenshteinDeleteCost").getOrElse(1D)

  final lazy val damerauLevenshteinInsertCost: Double = config.as[Option[Double]]("vector.damerauLevenshteinInsertCost").getOrElse(1D)

  final lazy val damerauLevenshteinReplaceCost: Double = config.as[Option[Double]]("vector.damerauLevenshteinReplaceCost").getOrElse(1D)

  final lazy val damerauLevenshteinSwapCost: Double = config.as[Option[Double]]("vector.damerauLevenshteinSwapCost").getOrElse(1D)

  final lazy val similarity: Similarity.Value = {
    config.as[Option[String]]("vector.similarity") match {
      case Some(sim) if sim equalsIgnoreCase "AverageTwoWayConversions" =>
        Similarity.AverageTwoWayConversions
      case Some(sim) if sim equalsIgnoreCase "Cosine" =>
        Similarity.Cosine
      case Some(sim) if sim equalsIgnoreCase "Covariance" =>
        Similarity.Covariance
      case Some(sim) if sim equalsIgnoreCase "Dice" =>
        Similarity.Dice
      case Some(sim) if sim equalsIgnoreCase "InnerProduct" =>
        Similarity.InnerProduct
      case Some(sim) if sim equalsIgnoreCase "Jaccard" =>
        Similarity.Jaccard
      case Some(sim) if sim equalsIgnoreCase "JaccardSimpson" =>
        Similarity.JaccardSimpson
      case Some(sim) if sim equalsIgnoreCase "Lin98" =>
        Similarity.Lin98
      case Some(sim) if sim equalsIgnoreCase "Mihalcea04" =>
        Similarity.Mihalcea04
      case Some(sim) if sim equalsIgnoreCase "PearsonProductMomentCorrelationCoefficient" =>
        Similarity.PearsonProductMomentCorrelationCoefficient
      case Some(sim) if sim equalsIgnoreCase "ReciprocalChebyshev" =>
        Similarity.ReciprocalChebyshev
      case Some(sim) if sim equalsIgnoreCase "ReciprocalEuclidean" =>
        Similarity.ReciprocalEuclidean
      case Some(sim) if sim equalsIgnoreCase "ReciprocalManhattan" =>
        Similarity.ReciprocalManhattan
      case Some(sim) if sim equalsIgnoreCase "ReciprocalMinkowsky" =>
        Similarity.ReciprocalMinkowsky
      case Some(sim) if sim equalsIgnoreCase "Simpson" =>
        Similarity.Simpson
      case Some(sim) if sim equalsIgnoreCase "Tversky" =>
        Similarity.Tversky
      case _ =>
        Similarity.Cosine
    }
  }

  final lazy val dissimilarity: Dissimilarity.Value = {
    config.as[Option[String]]("vector.dissimilarity") match {
      case Some(d) =>
        d.toLowerCase match {
          case "chebyshev" =>
            Dissimilarity.Chebyshev
          case "euclidean" =>
            Dissimilarity.Euclidean
          case "manhattan" =>
            Dissimilarity.Manhattan
          case "minkowsky" =>
            Dissimilarity.Minkowsky
          case _ =>
            Dissimilarity.None
        }
      case None =>
        Dissimilarity.None
    }
  }

  final lazy val convergence: Overlap.Value = {
    config.as[Option[String]]("vector.convergence") match {
      case Some(con) if con equalsIgnoreCase "Rus05" =>
        Overlap.Rus05
      case Some(con) if con equalsIgnoreCase "F" =>
        Overlap.F
      case Some(con) if con equalsIgnoreCase "F1" =>
        Overlap.F1
      case Some(con) if con equalsIgnoreCase "Precision" =>
        Overlap.Precision
      case Some(con) if con equalsIgnoreCase "Recall" =>
        Overlap.Recall
      case _ =>
        Overlap.Rus05
    }
  }
}
