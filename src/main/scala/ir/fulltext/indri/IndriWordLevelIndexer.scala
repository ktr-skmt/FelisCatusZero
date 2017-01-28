package ir.fulltext.indri

import java.nio.file.{Path, Paths}

import util.Config

/**
  * <pre>
  * Created on 2017/01/13.
  * </pre>
  *
  * @author K.Sakamoto
  */
object IndriWordLevelIndexer extends App {
  private val indices: Array[String] = Config.wordLevelIndriIndices.toArray
  private val segmentations: Array[String] = Config.wordLevelIndriSegmentations.toArray
  private val resources: Array[String] = Config.trecTextFormatData.toArray
  private val reviser: TrecTextFileFormatReviser = new TrecTextFileFormatReviser(1, false)
  if (segmentations.length == indices.length && resources.length == indices.length) {
    for (i <- indices.indices) {
      val resource:     Path = Paths.get(resources(i))
      val segmentation: Path = Paths.get(segmentations(i))
      val indexPath:    Path = Paths.get(indices(i))

      reviser.reviseInDirectory(resource, segmentation)
      new IndriIndex(segmentation, indexPath).index()
      println()
    }
  }
}
