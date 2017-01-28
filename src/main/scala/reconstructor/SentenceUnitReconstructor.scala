package reconstructor

import reconstructor.unit.CombineUnit
import text.StringOption
import text.analyzer.JapaneseSentenceSplitter
import text.analyzer.dep.chapas.{ChaPAS, Doc, PasAnalyzer}
import text.normalizer.NormalizedString


/**
  * Created by nakayama.
  * @author Nakayama
  */
object SentenceUnitReconstructor {
  def main(args: Array[String]): Unit = {
    val results: Seq[String] = sentenceUnitReconstructorWithChaPAS(
      """ウェストファリア条約で帝国内の領邦がほぼ完全な主権を認められたことにより、一つの帝国としての機能は失われた。""").sorted
    var counter: Int = 0
    results foreach {
      line: String =>
        counter += 1
        printf("%d=%s%n", counter, line)
    }
    println(results.size)

  }

  def sentenceUnitReconstructorWithChaPAS(text: String): Seq[String] = {
    val pasAnalyzer: PasAnalyzer = ChaPAS

    // Normalize sentences
    val normalizedSentences: Seq[NormalizedString] = {
      for (sentence <- JapaneseSentenceSplitter.split(StringOption(text))) yield {
        NormalizedString(StringOption(sentence.text))
      }
    }

    // Parse sentences
    val pasDocs: Seq[Doc] = {
      normalizedSentences.map(pasAnalyzer.parse)
    }

    // Generate combination units
    val filteredSentences: Seq[CombineUnit] =
      pasDocs.flatMap(_.sentences.map(new CombineUnit(_)))

    // Output
    filteredSentences.flatMap(_.representatives)
  }
}
