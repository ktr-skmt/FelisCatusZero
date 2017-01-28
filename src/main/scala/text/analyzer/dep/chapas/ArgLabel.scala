package text.analyzer.dep.chapas

/**
  * @author Nakayama
  *         Created on 2015/12/20
  */
object ArgLabel extends Enumeration {
  val Ga, Wo, Ni = Value

  def extendedWithName(name: String): ArgLabel.Value =
    name.toLowerCase match {
      case "ga" => Ga
      case "wo" | "o" => Wo
      case "ni" => Ni
    }

}