package util.process

import java.io.ByteArrayInputStream
import java.nio.charset.{Charset, CodingErrorAction}

import text.StringOption

import scala.io.{Codec, Source}
import scala.sys.process.ProcessBuilder

/**
  * @author K.Sakamoto
  *         Created on 2016/09/14
  */
object ProcessBuilderUtils {
  implicit def processToProcessUtils(repr: ProcessBuilder): ProcessBuilderUtils = {
    new ProcessBuilderUtils(repr)
  }
}

/**
  * @author K.Sakamoto
  * @param repr process builder
  */
class ProcessBuilderUtils(repr: ProcessBuilder) {
  def lineStream(encoding: Charset,
                 onMalformedInput: CodingErrorAction,
                 onUnmappableCharacter: CodingErrorAction,
                 replacementOpt: StringOption): Iterator[String] = {
    implicit val codec = Codec(encoding).
      onMalformedInput(onMalformedInput).
      onUnmappableCharacter(onUnmappableCharacter)
    if (replacementOpt.nonEmpty) {
      codec.decodingReplaceWith(replacementOpt.get)
    }
    Source.fromInputStream(
      new ByteArrayInputStream(
        repr.
          lineStream_!.
          iterator.
          mkString("\n").
          getBytes
      )
    ).getLines
  }
}
