package reconstructor.unit

import text.analyzer.dep.chapas.{ArgLabel, PAS, Token}
import util.Color

/**
  * Created by nakayama.
  * @author Nakayama
  */
final case class PAUnit(pas: PAS) {
    val representative: String = {
        val arguments: Map[ArgLabel.Value, Token] = pas.arguments
        List[(ArgLabel.Value, String)]((ArgLabel.Ga, "が"), (ArgLabel.Wo, "を"), (ArgLabel.Ni, "に")).map {
          case (label, punc) =>
            arguments.get(label) match {
                case Some(t) => t.chunk.textWithoutParticlesAndPuncs + punc
                case None => ""
            }
          case _ =>
            // Do nothing
        }.mkString + pas.predicate.base
    }

    val coloredRepresentative: String = {
        val arguments: Map[ArgLabel.Value, Token] = pas.arguments
        List[(ArgLabel.Value, String)]((ArgLabel.Ga, "が"), (ArgLabel.Wo, "を"), (ArgLabel.Ni, "に")).map {
          case (label, punc) =>
            arguments.get(label) match {
                case Some(t) => Color.coloring(t.chunk.tokensWithoutParticlesAndPuncs, pas) + punc
                case None => ""
            }
          case _ =>
            // Do nothing
        }.mkString concat s"${Color.BLUE}${pas.predicate.base}${Color.RESET}"
    }
}