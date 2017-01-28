package ir.fulltext.indri

import util.Config

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
trait Retrieval {
  protected def command(queryList: Seq[String], knowledgeSourceList: Seq[String]): Seq[String] = {
    val indices: Seq[String] = knowledgeSourceList map {
      knowledgeSource: String =>
        "-index=" concat knowledgeSource
    }
    val queries: Seq[String] = queryList map {
      query: String =>
        "-query=" concat query
    }
    Seq[String](
      "IndriRunQuery",
      "-printDocuments=true",
      s"-memory=${Config.indriMemory}",
      "-printQuery=true",
      s"-count=${Config.indriCount}") ++ queries ++ indices
  }

}
