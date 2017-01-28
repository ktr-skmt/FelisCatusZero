package text

/**
 * @author K.Sakamoto
 */
object Skip2Gram {
  def apply(firstGram: StringOption, lastGram: StringOption, gap: Int, frequency: Int): Skip2Gram = {
    new Skip2Gram(firstGram, lastGram, gap, frequency)
  }
}

/**
  * @author K.Sakamoto
  * @param firstGram first uni-gram
  * @param lastGram last uni-gram
  * @param gap gap
  * @param frequency frequency
  */
class Skip2Gram(val firstGram: StringOption,
                val lastGram: StringOption,
                val gap: Int,
                val frequency: Int)