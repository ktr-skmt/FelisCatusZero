package sentence

import jeqa.types.Sentence

/**
 * @author K.Sakamoto
 * @param sentences sentences
 * @param characterNumber character number
 * @param score score
 * @param linkScore link score
 * @param mergedScores merged scores
 */
class SentenceCombination(var sentences: List[Sentence],
                          var characterNumber: Int = 0,
                          var score: Double = 0D,
                          var linkScore: Double = 0D,
                          var mergedScores: Seq[Double] = Nil)