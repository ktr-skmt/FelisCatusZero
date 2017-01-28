package reconstructor.unit

import text.analyzer.dep.Chunk
import text.analyzer.dep.chapas.{PAS, Token}
import util.Color

/**
  * Created by nakayama.
  * @author Nakayama
  */
final case class DirectDependentsUnit(pas: PAS) {
    val predicate: Token = pas.predicate
    val predicateChunk: Chunk = predicate.chunk

    val unit: Seq[Chunk] =
        predicateChunk.depSrcs ++ Seq(predicateChunk)

    val unitWithoutPredicate: Seq[Chunk] =
        unit.filter(c => c == predicate.chunk || !c.hasPredicates)

    val unitWithoutHeadVerb: Seq[Chunk] = DirectDependentsUnit.eliminateHeadVerbChunk(unit)

    val representative: String = unit.map(_.text).mkString

    val complementedRepresentative: String = CombineUnit.withComplement(unit)

    val coloredRepresentative: String =
        (unit.dropRight(1).map(_.tokens) :+ unit.last.tokensWithoutParticlesAndPuncs)
            .flatMap(c => Color.coloring(c, pas)).mkString

    val representativeWithoutPredicate: String =
        unitWithoutPredicate.dropRight(1).map(_.text).mkString + unitWithoutPredicate.last.textWithoutParticlesAndPuncs

    val coloredRepresentativeWithoutPredicate: String =
        (unitWithoutPredicate.dropRight(1).map(_.tokens) :+ unitWithoutPredicate.last.tokensWithoutParticlesAndPuncs)
            .flatMap(c => Color.coloring(c, pas)).mkString

    val representativeWithoutHeadVerb: String =
        unitWithoutHeadVerb.map(_.text).mkString

    val coloredRepresentativeWithoutHeadVerb: String =
        unitWithoutHeadVerb.map(_.tokens).flatMap(c => Color.coloring(c, pas)).mkString
}

/**
  * @author Nakayama
  */
object DirectDependentsUnit {
    def eliminateHeadVerbChunk(chunks: Seq[Chunk]): Seq[Chunk] = chunks.headOption match {
        case Some(h) if h.tokens.exists(_.pos == "動詞") => chunks.drop(1)
        case _ => chunks
    }
}
