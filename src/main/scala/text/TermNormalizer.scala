package text

/**
  * @author K.Sakamoto
  *         Created on 2015/11/27
  */
trait TermNormalizer {
  def normalize(textOpt: StringOption): StringOption
}
