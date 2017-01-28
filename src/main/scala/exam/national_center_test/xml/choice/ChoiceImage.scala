package exam.national_center_test.xml.choice

import java.awt.Image
import java.io.{File, IOException}
import javax.imageio.ImageIO

/**
 * <pre>
 * Created on 5/23/15.
 * </pre>
 *
 * @author K.Sakamoto
 */
object ChoiceImage {
  def apply(choice: collcetion.ChoiceSingleton): ChoiceImage = {
    new ChoiceImage(
      try {
        ImageIO.read(
          new File(
            choice.singleton
          )
        )
      } catch {
        case e: IOException =>
          e.printStackTrace()
          null
      }
    )
  }
}

/**
  * @author K.Sakamoto
  * @param choice choice
  */
class ChoiceImage(override val choice: Image) extends Choice[Image](choice) {

}

/**
  * @author K.Sakamoto
  */
object ChoicesImage {
  def apply(choices: Seq[collcetion.ChoiceSingleton]): ChoicesImage = {
    new ChoicesImage(choices map {ChoiceImage(_)})
  }
}

/**
  * @author K.Sakamoto
  * @param choices choices
  */
class ChoicesImage(override val choices: Seq[ChoiceImage]) extends Choices[ChoiceImage](choices) {

}
