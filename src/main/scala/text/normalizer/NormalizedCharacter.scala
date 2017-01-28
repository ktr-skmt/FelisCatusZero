package text.normalizer

import text.StringOption

/**
 * @author K.Sakamoto
 *         Created on 15/10/28
 */
class NormalizedCharacter(private var character: Char) {
  character = Normalizer.normalize(StringOption(character.toString)).get.head

  def toChar: Char = {
    character
  }
}
