package util

import java.io.File

import com.typesafe.config.{Config => TConfig, ConfigFactory}
import net.ceedubs.ficus.Ficus._

/**
  * Created by nakayama.
  * @author Nakayama
  */
object SentenceUnitReconstructorConfig {
  final private[this] var config: TConfig = ConfigFactory.load()

  def set(configFile: File): Unit = {
    config = ConfigFactory.load(ConfigFactory.parseFile(configFile))
  }

  final lazy val inputFile: Option[String] = {
    config.as[Option[String]]("input-file")
  }
  final lazy val outputFile: Option[String] = {
    config.as[Option[String]]("output-file")
  }

  final private[this] lazy val cabochaConfig: Option[TConfig] = {
    config.as[Option[TConfig]]("dep-parser.cabocha")
  }

  final private[this] lazy val cabocha: Option[String] = {
    cabochaConfig map {
      c => c.as[Option[String]]("cmd").get
    }
  }

  final private[this] val cabochaTreeOpt: String = "-f 0"
  final private[this] val cabochaLatticeOpt: String = "-f 1"
  final private[this] val cabochaNeOpt: String = "-n 1"

  final lazy val cabochaTreeIPA: Option[String] = {
    for {
      cfg <- cabochaConfig
      cbc <- cabocha
      opt <- cfg.as[Option[String]]("opt.ipa-naist-dic")
    } yield {
      Seq[String](cbc, cabochaTreeOpt, cabochaNeOpt, opt).mkString(" ")
    }
  }

  final lazy val cabochaLatticeIPA: Option[String] = {
    for {
      cfg <- cabochaConfig
      cbc <- cabocha
      opt <- cfg.as[Option[String]]("opt.ipa-naist-dic")
    } yield {
      Seq[String](cbc, cabochaLatticeOpt, cabochaNeOpt, opt).mkString(" ")
    }
  }

  final lazy val cabochaTreeJuman: Option[String] = {
    for {
      cfg <- cabochaConfig
      cbc <- cabocha
      opt <- cfg.as[Option[String]]("opt.juman")
    } yield {
      Seq[String](cbc, cabochaTreeOpt, cabochaNeOpt, opt).mkString(" ")
    }
  }

  final lazy val cabochaLatticeJuman: Option[String] = {
    for {
      cfg <- cabochaConfig
      cbc <- cabocha
      opt <- cfg.as[Option[String]]("opt.juman")
    } yield {
      Seq[String](cbc, cabochaLatticeOpt, cabochaNeOpt, opt).mkString(" ")
    }
  }

  final lazy val chapas: Option[String] = {
    config.as[Option[String]]("pas.chapas")
  }

  final lazy val showcase: Option[String] = {
    config.as[Option[String]]("pas.showcase")
  }

  final lazy val color: Boolean = {
    config.as[Boolean]("color")
  }

  final lazy val cacheIsEnable: Boolean = {
    config.as[Boolean]("cache.enable")
  }

  final lazy val cacheSave: Boolean = {
    config.as[Boolean]("cache.save")
  }

  final lazy val cacheDir: Option[String] = {
    config.as[Option[String]]("cache.dir")
  }
}