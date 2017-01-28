package uima.cr

import java.io.{File, FileInputStream, IOException}
import java.util.zip.GZIPInputStream

import org.apache.uima.cas.CAS
import org.apache.uima.cas.impl.XmiCasDeserializer
import org.apache.uima.collection.{CollectionException, CollectionReader_ImplBase}
import org.apache.uima.resource.{ResourceConfigurationException, ResourceInitializationException}
import org.apache.uima.util.{Progress, ProgressImpl}
import uima.GzipXMI

import scala.collection.mutable.ListBuffer
import scala.xml.SAXException

/**
  * <p>Input Gzipped XMI files</p>
 *
  * @author K.Sakamoto
  *         Created on 2016/10/27
  */
class GzipXmiCollectionReader extends CollectionReader_ImplBase with GzipXMI {
  private final val PARAM_INPUT_DIR:    String = "InputDirectory"
  private final val PARAM_FAIL_UNKNOWN: String = "FailOnUnknownType"

  private var mFailOnUnknownType: Option[Boolean] = None
  private var mFiles: ListBuffer[File] = ListBuffer.empty[File]
  private var mCurrentIndex: Option[Int] = None

  @throws[ResourceInitializationException]
  override def initialize(): Unit = {
    println(">> Gzipped XMI Collection Reader Initializing")
    mFailOnUnknownType = Option(getConfigParameterValue(PARAM_FAIL_UNKNOWN).asInstanceOf[Boolean])

    if (mFailOnUnknownType.isEmpty) {
      mFailOnUnknownType = Option(true) // default to true if not specified
    }

    val directory: File = new File(getConfigParameterValue(PARAM_INPUT_DIR).asInstanceOf[String].trim)

    mCurrentIndex = Option(0)

    if (!directory.exists || !directory.isDirectory) {
      throw new ResourceInitializationException(
        ResourceConfigurationException.DIRECTORY_NOT_FOUND,
        Array[Object](PARAM_INPUT_DIR, this.getMetaData.getName, directory.getPath)
      )
    }

    mFiles.clear
    directory.listFiles foreach {
      case file if !file.isDirectory && file.getName.endsWith(GZIP_XMI_FILE_EXTENSION) =>
        mFiles += file
        print("input CAS from Gzipped XMI ")
        println(file.toString)
      case _ =>
        //Do nothing
    }
  }

  override def hasNext: Boolean = {
    mCurrentIndex.nonEmpty && mCurrentIndex.get < mFiles.size
  }

  @throws[IOException]
  @throws[CollectionException]
  override def getNext(aCAS: CAS): Unit = {
    println(">> Gzipped XMI Collection Reader Processing")

    if (mCurrentIndex.isEmpty) {
      return
    }
    val currentIndex: Int = mCurrentIndex.get
    val currentFile: File = mFiles(currentIndex)
    mCurrentIndex = Option(currentIndex + 1)
    val input: Option[FileInputStream] = Option(new FileInputStream(currentFile))
    if (input.isEmpty) {
      return
    }
    val inputStream: FileInputStream = input.get

    val gZipInput: Option[GZIPInputStream] = Option(new GZIPInputStream(inputStream))
    if (gZipInput.isEmpty) {
      return
    }
    val gZipInputStream: GZIPInputStream = gZipInput.get

    if (mFailOnUnknownType.isEmpty) {
      return
    }
    val failOnUnknownType: Boolean = mFailOnUnknownType.get

    try {
      XmiCasDeserializer.deserialize(gZipInputStream, aCAS, !failOnUnknownType)
    } catch {
      case e: SAXException =>
        throw new CollectionException(e)
    } finally {
      if (gZipInput.nonEmpty) {
        gZipInput.get.close()
      }
      if (input.nonEmpty) {
        input.get.close()
      }
    }
  }

  @throws[IOException]
  override def close(): Unit = {
    //Do nothing
  }

  override def getProgress: Array[Progress] = {
    if (mCurrentIndex.isEmpty) {
      return Array.empty[Progress]
    }
    val currentIndex: Int = mCurrentIndex.get

    Array[Progress](new ProgressImpl(currentIndex, mFiles.size, Progress.ENTITIES))
  }
}
