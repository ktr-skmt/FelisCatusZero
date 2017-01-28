package text.analyzer.dep.chapas

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
case class PAS(predicate: Token,
               arguments: Map[ArgLabel.Value, Token])