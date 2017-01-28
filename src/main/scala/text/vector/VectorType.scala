package text.vector

import util.Config

/**
  * @author K.Sakamoto
  *         Created on 2016/05/22
  */
object VectorType extends Enumeration {
  val None,
  Binary,
  Frequency = Value

  def get: VectorType.Value = {
    if (Config.isFrequencyOtherwiseBinary) {
      Frequency
    } else {
      Binary
    }
  }
}