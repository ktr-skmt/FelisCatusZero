package uima.ae

import ir.fulltext.indri.{RetrievalByBoW, RetrievalByKeyword}
import jeqa.types._
import org.apache.uima.UimaContext
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase
import org.apache.uima.analysis_engine.AnalysisEngineProcessException
import org.apache.uima.cas.FSIterator
import org.apache.uima.jcas.JCas
import org.apache.uima.jcas.cas.FSArray
import org.apache.uima.resource.ResourceInitializationException
import text.correction.{BoWBasedIRDocCorrector, KeywordBasedIRDocCorrector}
import util.uima.JCasUtils
import util.uima.TimeUtils._

import scala.collection.mutable


/**
 * <p>質問解析結果により適切な知識源から解答に必要な記述を検索するプログラム</p>
 * @author K.Sakamoto
 *         Created on 15/10/30
 */
class InformationRetriever extends JCasAnnotator_ImplBase with DocumentAnnotator {
  private val mIndriScoreIndex: Int = 0
  private var mDocumentId: Long = 0L

  @throws[ResourceInitializationException]
  override def initialize(aContext: UimaContext): Unit = {
    println(">> Information Retriever Initializing")
    super.initialize(aContext)
  }
  @throws[AnalysisEngineProcessException]
  override def process(aJCas: JCas): Unit = {
    println(">> Information Retriever Processing")
    JCasUtils.setAJCasOpt(Option(aJCas))

    @SuppressWarnings(Array[String]("rawtypes"))
    val itExam: FSIterator[Nothing] = aJCas.getAnnotationIndex(Exam.`type`).iterator(true)
    while (itExam.hasNext) {
      val exam: Exam = itExam.next
      val questionSet: FSArray = exam.getQuestionSet
      for (i <- 0 until questionSet.size) {
        val question: Question = questionSet.get(i).asInstanceOf[Question]
        val beginTimeLimit: Option[Int] = question.getBeginTimeLimit.toYearOpt
        val endTimeLimit: Option[Int] = question.getEndTimeLimit.toYearOpt
        val geographyLimit: Option[Geography] = None
        val keywordCorrectionMap: mutable.Map[String, Seq[String]] = mutable.Map.empty[String, Seq[String]]

        //println(">>> Relevant Documents Retrieving")
        // Retrieve relevant documents
        val queryArray: FSArray = question.getQuerySet
        for (j <- 0 until queryArray.size) {
          queryArray.get(j) match {
            case query: KeywordQuery if !query.getAlreadyFinishedRetrieving =>
              mDocumentId = RetrievalByKeyword.retrieve(
                aJCas,
                query,
                keywordCorrectionMap,
                mIndriScoreIndex,
                mDocumentId)
            case query: BoWQuery if !query.getAlreadyFinishedRetrieving =>
              mDocumentId = RetrievalByBoW.retrieve(
                aJCas,
                query,
                mIndriScoreIndex,
                mDocumentId)
            case _ =>
            // Do nothing
          }
        }

        //println(">>> Document Correcting")
        // Re-correct document, passage and sentence in other keywords
        for (j <- 0 until queryArray.size) {
          queryArray.get(j) match {
            case query: KeywordQuery if !query.getAlreadyFinishedCorrecting =>
              KeywordBasedIRDocCorrector.correct(
                aJCas,
                query,
                keywordCorrectionMap.toMap,
                beginTimeLimit,
                endTimeLimit,
                geographyLimit)
            case query: BoWQuery if !query.getAlreadyFinishedCorrecting =>
              BoWBasedIRDocCorrector.correct(
                aJCas,
                query,
                beginTimeLimit,
                endTimeLimit,
                geographyLimit)
            case _ =>
              // Do nothing
          }
        }
      }
    }
  }
}