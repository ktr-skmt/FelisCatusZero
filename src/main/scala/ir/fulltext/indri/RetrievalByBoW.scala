package ir.fulltext.indri

import java.io.{BufferedReader, File}
import java.nio.charset.{CodingErrorAction, StandardCharsets}
import java.nio.file.Files

import jeqa.types.{BoWQuery, Document, Keyword, Score}
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import question.KnowledgeSourceSelector
import text.{StringNone, StringOption, StringSome}
import util.Config
import util.process.ProcessBuilderUtils._
import util.uima.JCasUtils
import util.uima.SeqUtils._

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.sys.process.Process

/**
  * <pre>
  * Created on 2017/01/08.
  * </pre>
  *
  * @author K.Sakamoto
  */
object RetrievalByBoW extends Retrieval {
  private var baseline: String = "-baseline=okapi,k1:1.2,b:0.75,k3:7"
  private val docnoTextMap: Map[String, String] = initialize

  private def initialize: Map[String, String] = {
    val buffer: ListBuffer[String] = ListBuffer.empty[String]
    Config.trecTextFormatData foreach {
      dir: String =>
        new File(dir).listFiles foreach {
          case file: File if file.canRead && file.isFile && file.getName.endsWith(".xml") =>
            val reader: BufferedReader = Files.newBufferedReader(file.toPath, StandardCharsets.UTF_8)
            val iterator: java.util.Iterator[String] = reader.lines.iterator
            while (iterator.hasNext) {
              val line: String = iterator.next
              buffer += line
            }
            reader.close()
          case _ =>
          // Do nothing
        }
    }
    val map: mutable.Map[String, String] = mutable.Map.empty[String, String]
    val iterator: Iterator[IndriResult] = TrecText.toIndriResultMap(
      buffer.result.iterator,
      StringNone,
      Nil,
      mutable.Map.empty[String, IndriResult]).valuesIterator
    while (iterator.hasNext) {
      val result: IndriResult = iterator.next
      result.docno match {
        case StringSome(docno) if !map.contains(docno) =>
          val textOpt: StringOption = result.text
          if (textOpt.nonEmpty) {
            map(docno) = textOpt.get
          }
        case _ =>
        // Do nothing
      }
    }
    map.toMap
  }

  def retrieve(aJCas: JCas,
               query: BoWQuery,
               mIndriScoreIndex: Int,
               firstDocumentId: Long): Long = {
    var mDocumentId: Long = firstDocumentId
    JCasUtils.setAJCasOpt(Option(aJCas))

    query.getAlgorithm match {
      case "TFIDF" =>
        baseline = s"""-baseline=tfidf,k1${Config.indriTfidfK1},b:${Config.indriTfidfB}"""
      case "BM25" =>
        baseline = s"""-baseline=okapi,k1:${Config.indriBm25K1},b:${Config.indriBm25B},k3:${Config.indriBm25K3}"""
      case _ =>
        // Do nothing
    }

    val knowledgeSourceList: Seq[String] = KnowledgeSourceSelector.select(false)

    val keyword: Keyword = query.getIndriQuery
println(keyword.getText)
    def retrieve: Iterator[IndriResult] = {
      TrecText.toIndriResultMap(
        Process(command(Seq[String](keyword.getText), knowledgeSourceList)).
          lineStream(
            StandardCharsets.UTF_8,
            CodingErrorAction.IGNORE,
            CodingErrorAction.IGNORE,
            StringNone
          ),
        StringOption(keyword.getText), Nil, mutable.Map.empty[String, IndriResult]).valuesIterator
    }

    //docno, document
    val documentMap: mutable.Map[String, Document] = mutable.Map.empty[String, Document]

    retrieve foreach {
      case result if result.text.nonEmpty && result.docno.nonEmpty && result.title.nonEmpty =>
        val docno: String = result.docno.get
        val title: String = result.title.get
        val score: Double = result.score
        val textOpt: StringOption = docno2TextOpt(docno)
        if (!documentMap.contains(docno) && textOpt.nonEmpty) {
          val document: Document = new Document(aJCas)
          document.addToIndexes()
          document.setText(textOpt.get)
          document.setDocno(docno)
          document.setTitle(title)
          val scoreArray: FSArray = new FSArray(aJCas, Config.numOfScores)
          scoreArray.addToIndexes()
          for (i <- 0 until scoreArray.size()) {
            val scoreType: Score = new Score(aJCas)
            scoreType.addToIndexes()
            scoreArray.set(i, scoreType)
          }
          val scoreType: Score = scoreArray.get(mIndriScoreIndex).asInstanceOf[Score]
          scoreType.setScore(score)
          scoreType.setScorer("WordLevel" concat query.getAlgorithm)
          document.setScoreList(scoreArray)
          mDocumentId += 1
          document.setId(mDocumentId)
          documentMap(docno) = document
        }
      case _ =>
      // Do nothing
    }
    val documentList: Seq[Document] = documentMap.values.toSeq
    keyword.setDocumentSet(documentList.toFSArray)
    query.setAlreadyFinishedRetrieving(true)
    mDocumentId
  }

  private def docno2TextOpt(docno: String): StringOption = {
    if (docnoTextMap.contains(docno)) {
      StringOption(docnoTextMap(docno))
    } else {
      StringNone
    }
  }

  override def command(queryList: Seq[String], knowledgeSourceList: Seq[String]): Seq[String] = {
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
      baseline,
      s"-count=${Config.indriCount}") ++ queries ++ indices
  }
}
