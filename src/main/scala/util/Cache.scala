package util

import java.io.File

import text.{StringNone, StringSome}
import text.analyzer.dep.chapas.Doc
import text.normalizer.NormalizedString

/**
  * Created by nakayama.
  * @author Nakayama
  */
trait Cache {
  final val cacheEnable: Boolean = SentenceUnitReconstructorConfig.cacheIsEnable

  protected[this] val docsCacheFileName: String

  final private[this] lazy val docsCacheFile: Option[File] = {
    SentenceUnitReconstructorConfig.cacheDir map {
      d => new File(s"$d/$docsCacheFileName")
    }
  }

  private var docsCache: Option[Map[String, Doc]] = None

  def loadDocsCache(): Unit = {
    if (docsCacheFile.nonEmpty && docsCacheFile.get.exists) {
      val f: File = docsCacheFile.get
      System.err.println(s"loading cache from ${f.getAbsolutePath} ...")
      docsCache = Some(Serializer.load[Map[String, Doc]](f))
    }
  }

  // Initialize cache
  loadDocsCache()

  def getDocCache(docText: String): Option[Doc] = {
    docsCache match {
      case Some(d) =>
        d.get(docText)
      case None =>
        None
    }
  }

  def getDocCache(normalizedString: NormalizedString): Option[Doc] = {
    normalizedString.toStringOption match {
      case StringSome(s) =>
        docsCache match {
          case Some(d) =>
            d.get(s)
          case None =>
            None
        }
      case StringNone =>
        None
    }
  }

  def saveDocsCache(docs: Seq[Doc]): Unit = {
    docsCacheFile match {
      case Some(f) =>
        System.err.println(s"saving cache to ${f.getAbsolutePath} ...")
        Serializer.save(docsCache.getOrElse(Map.empty[String, Doc]) ++ docs.map(d => (d.text, d)), f)
      case None =>
        throw new RuntimeException("Cache directory setting was not found.")
    }
  }
}

/**
  * @author Nakayama
  */
trait ChaPASCache extends Cache {
  override final protected[this] val docsCacheFileName: String = {
    "chapas-docs"
  }
}

/**
  * @author Nakayama
  */
trait ShowcaseCache extends Cache {
  override final protected[this] val docsCacheFileName: String = {
    "showcase-docs"
  }
}