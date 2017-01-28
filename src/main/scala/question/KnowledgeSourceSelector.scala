package question

import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2016/12/05
  */
object KnowledgeSourceSelector {
  def select(isKeywordQuery: Boolean): Seq[String] = {
    if (isKeywordQuery) {
      Config.characterLevelIndriIndices
    } else {
      Config.wordLevelIndriIndices
    }
  }
}
