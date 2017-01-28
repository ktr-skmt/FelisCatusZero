package exam.national_center_test.xml

import exam.QuestionFormat
import exam.national_center_test.xml.model.CSVQuestionFormat
import org.junit.experimental.theories.{DataPoints, Theories, Theory}
import org.junit.runner.RunWith
import org.scalatest.junit.AssertionsForJUnit

import scala.collection.mutable
import scala.io.{BufferedSource, Source}


/**
 * Created by kosasa on 15/07/24.
 * http://www.scalatest.org/getting_started_with_junit_4_in_scala
 * http://seratch.hatenablog.jp/entry/20110807/1312726957
 * @author Kosasa
 */

object ParserTestSuite {
  @DataPoints
  def years(): Array[Int] = Array[Int](1997, 2001, 2003, 2005, 2007, 2009)
}

@RunWith(classOf[Theories])
class ParserTestSuite() extends AssertionsForJUnit() {
  @Theory def testQuestionFormat(year : Int): Unit = {
    val formats: mutable.HashMap[String, CSVQuestionFormat] = mutable.HashMap.empty[String, CSVQuestionFormat]
    val file: BufferedSource = Source.fromFile(s"./test_res/format/QuestionFormat_NationalCenterTest_$year.csv")
    for (line <- file.getLines) {

      val cols: Array[String] = line.split(',')

      val qf: CSVQuestionFormat = new CSVQuestionFormat(cols(1), cols(2))

      formats.put(cols.head, qf)
    }

    val parser:  NationalCenterTestParser = new NationalCenterTestParser()
    val result: Option[NationalCenterTestQuestion] = parser.parse(year)
    println(year)

    var counter: Int = 0

    for (big <- result.get.questions) {
      for (middle <- big.middleQuestions) {
        for (small <- middle.smallQuestions) {
          val anscol: String = "A%d".format(small.id)
          val qf: Option[CSVQuestionFormat] = formats.get(anscol)
          val questionFormatForSmallQuestion: String = QuestionFormat.toString(small.questionFormat)
          val realQuestionFormat: String = qf.get.questionFormat
          if (questionFormatForSmallQuestion != realQuestionFormat) {
            counter += 1
            println(anscol)
            println(questionFormatForSmallQuestion)
            println(realQuestionFormat)
            println()
          }
          //assert(qf.get.questionFormat.startsWith(questionFormatForSmallQuestion))
        }
      }
    }

    print("COUNTER ")
    println(counter)
  }

}
