package uima.cc

import java.io.{File, FileOutputStream, IOException}
import java.util.zip.GZIPOutputStream

import org.apache.uima.cas.impl.XmiCasSerializer
import org.apache.uima.cas.{CAS, CASException}
import org.apache.uima.collection.CasConsumer_ImplBase
import org.apache.uima.jcas.JCas
import org.apache.uima.resource.ResourceProcessException
import org.apache.uima.util.XMLSerializer
import org.xml.sax.SAXException
import uima.GzipXMI


/**
  * <p>Output gzipped XMI files</p>
  * @author K.Sakamoto
  *         Created on 15/10/30
  */
class GzipXmiCasConsumer extends CasConsumer_ImplBase with GzipXMI {
  private final val PARAM_OUTPUT_DIR: String = "OutputDirectory"

  private var mDocNum: Int = 0
  private var mOutputDir: Option[File] = None

  override def initialize(): Unit = {
    println(">> Gzipped XMI Cas Consumer Initializing")

    mDocNum    = 0
    mOutputDir = Option(new File(getConfigParameterValue(PARAM_OUTPUT_DIR).asInstanceOf[String]))

    mOutputDir match {
      case Some(outFile) if !outFile.exists() =>
        outFile.mkdirs()
      case _ =>
        //Do nothing
    }

    print("output CAS as Gzipped XMI into ")
    println(mOutputDir.get)
  }

  private def clear(): Unit = {
    mOutputDir match {
      case Some(outFile) =>
        for (f <- outFile.listFiles() if f.getName endsWith GZIP_XMI_FILE_EXTENSION) {
          f.delete()
        }
      case None =>
      //Do nothing
    }
  }

  @throws[ResourceProcessException]
  override def processCas(aCAS: CAS): Unit = {
    println(">> Gzipped XMI Cas Consumer Processing")

    var aJCasOption: Option[JCas] = None
    try {
      aJCasOption = Option(aCAS.getJCas)
    } catch {
      case e: CASException =>
        throw new ResourceProcessException(e)
    }

    if (aJCasOption.isEmpty) {
      return
    }
    //val aJCas: JCas = aJCasOption.get


    if (mOutputDir.isEmpty) {
      return
    }

    val outputDir: File = mOutputDir.get

    clear()

    val outputFileName: String = mDocNum.toString
    mDocNum += 1

    writerXmi(aCAS, new File(outputDir, outputFileName concat GZIP_XMI_FILE_EXTENSION))
  }

  @throws[IOException]
  @throws[SAXException]
  private def writerXmi(aCAS: CAS, outputFile: File): Unit = {
    var out:     Option[FileOutputStream] = None
    var gZipOut: Option[GZIPOutputStream] = None

    try {
      out = Option(new FileOutputStream(outputFile))
      if (out.isEmpty) {
        return
      }

      gZipOut = Option(new GZIPOutputStream(out.get))
      if (gZipOut.isEmpty) {
        return
      }
      val o: GZIPOutputStream = gZipOut.get

      val ser:    XmiCasSerializer = new XmiCasSerializer(aCAS.getTypeSystem)
      val xmlSer: XMLSerializer    = new XMLSerializer(o, false)
      ser.serialize(aCAS, xmlSer.getContentHandler)

    } finally {
      gZipOut match {
        case Some(o) =>
          o.close()
        case None =>
          //Do nothing
      }
      out match {
        case Some(o) =>
          o.close()
        case None =>
          //Do nothing
      }
    }
  }

  @throws[ResourceProcessException]
  override def destroy(): Unit = {
    //Do nothing
  }
}
