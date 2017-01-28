package sentence

import exam.CharacterCounter
import jeqa.types.Sentence
import text.StringOption

import scala.collection.mutable.ListBuffer

/**
 * @author K.Sakamoto
 * @param scoreIndex score index
 */
class SentenceCombinationGenerator(scoreIndex: Int) {
  private var characterEndLimit: Int = 0
  private var combos: ListBuffer[SentenceCombination] = ListBuffer.empty[SentenceCombination]

  def generate(input: Seq[SentenceGroup], characterEndLimit: Int): Seq[SentenceCombination] = {
    combos = ListBuffer.empty[SentenceCombination]
    this.characterEndLimit = characterEndLimit
    pickUp(input, input.size - 1, new SentenceCombination(Nil, 0, 0D))
    combos.seq
  }

  private def pickUp(input: Seq[SentenceGroup], level: Int, combo: SentenceCombination): Unit = {
    if (0 <= level) {
      val data: SentenceGroup = input(level)
      if (0 < data.sentences.size) {
        data.sentences foreach {
          datum: Sentence =>
            val number: Int = combo.characterNumber + CharacterCounter.count(StringOption(datum.getText))
            if (number <= characterEndLimit) {
              pickUp(input, level - 1, new SentenceCombination(
                combo.sentences ::: List[Sentence](datum),
                number,
                combo.score + datum.getScoreList(scoreIndex).getScore))
            }
        }
      } else {
        pickUp(input, level - 1, combo)
      }
    } else {
      combos += combo
    }
  }
}
