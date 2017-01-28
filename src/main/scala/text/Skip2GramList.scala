package text

/**
 *
 */
object Skip2GramList {
  def apply(list: Seq[Skip2Gram]): Skip2GramList = {
    new Skip2GramList(list)
  }
}

class Skip2GramList(val list: Seq[Skip2Gram])