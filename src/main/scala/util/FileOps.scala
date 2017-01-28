package util

import java.io.{File, FileNotFoundException, PrintStream, PrintWriter}

import scala.io.Source

/**
  * Created by nakayama.
  * @author K.Sakamoto
  */
object FileOps {
  def getFileFromDir(path: String): Array[File] = {
    getFileFromDir(new File(path))
  }

  def getFileFromDir(file: File): Array[File] = {
    val fileName: String = file.getName
    if (!file.exists()) {
      new FileNotFoundException(fileName)
    }
    assume(file.isDirectory, s"$fileName is not a directory.")
    file.listFiles()
  }

  def getText(file: File): String = {
    try {
      Source.fromFile(file).getLines.mkString("\n")
    } catch {
      case e: Throwable => throw e
    }
  }

  def withMakeParentDirs(path: String): File = {
    withMakeParentDirs(new File(path))
  }

  def withMakeParentDirs(file: File): File = {
    Option(file.getParent) match {
      case Some(p) =>
        new File(p).mkdirs()
      case None =>
    }
    file
  }

  def getPrintStream(path: String): PrintStream = {
    getPrintStream(new File(path))
  }

  def getPrintStream(file: File): PrintStream = {
    new PrintStream(withMakeParentDirs(file))
  }

  def write(pStream: PrintStream)(op: PrintWriter => Unit): Unit = {
    val writer: PrintWriter = new PrintWriter(pStream)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
}