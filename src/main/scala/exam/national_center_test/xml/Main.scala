package exam.national_center_test.xml

/**
 * <pre>
 * Created on 2014/10/31
 * </pre>
 * @author K.Sakamoto
 */
object Main {
  def main(args : Array[String]): Unit = {
    //println("START")
//    val univ = University.UniversityOfTokyo792
//    Seq[JapaneseSchoolYear](
//      //JapaneseSchoolYear.F2005//,
//      //JapaneseSchoolYear.H2007//,
//      JapaneseSchoolYear.K2009
//    ) foreach {
//      year =>
//        //AnswerGenerator.doWorkflowNTCIR2014QALab(univ, year)
//        AnswerGenerator.doWorkflowNLP2015(univ, year)
//    }
//    println("END")
    val parser: NationalCenterTestParser = new NationalCenterTestParser()
    val centerTestQuestions: Option[NationalCenterTestQuestion] = parser.parse(1997)

  }
}
