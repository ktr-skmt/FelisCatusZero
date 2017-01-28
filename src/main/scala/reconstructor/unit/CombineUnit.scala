package reconstructor.unit

import text.analyzer.dep.chapas.Token
import text.analyzer.dep.{Chunk, Chunks, DepType}

import scala.collection.immutable.IndexedSeq

/**
  * Created by nakayama.
  * @author Nakayama
  */
case class CombineUnit(sentence: Chunks) {
    val units: Seq[DirectDependentsUnit] = sentence.pases.map(new DirectDependentsUnit(_))

    val unitCombinations: IndexedSeq[Seq[DirectDependentsUnit]] =
        (1 to units.size).flatMap(units.combinations)

    val representatives: Seq[String] =
        unitCombinations.map { u =>
            val chunks: Seq[Chunk] = u.flatMap(_.unit)
            CombineUnit.withComplement(sentence.chunks.filter(chunks.contains(_)))
        }.distinct

    val representativesWithoutPredicate: Seq[String] =
        unitCombinations.map { u =>
            val chunks: Seq[Chunk] = u.flatMap(_.unitWithoutPredicate)
            CombineUnit.withComplement(sentence.chunks.filter(chunks.contains(_)))
        }.distinct

    val representativesWithoutHeadVerb: Seq[String] = {
        unitCombinations.map { u =>
            val chunks: Seq[Chunk] = u.flatMap(_.unitWithoutHeadVerb)
            val chunksWithParallels = sentence.chunks.withFilter(chunks.contains(_))
                .flatMap(CombineUnit.complementParallelChunk).distinct
            DirectDependentsUnit.eliminateHeadVerbChunk(chunksWithParallels)
                .flatMap(CombineUnit.complementNounPhrase)
                .distinct.sortBy(_.id)
                .map(_.surface).mkString
        }.distinct
    }
}

/**
  * @author Nakayama
  */
object CombineUnit {
    def isNounPhrasePos(token: Token): Boolean =
        token.pos == "名詞" || (token.pos == "接頭詞" && (token.detailedPos.orNull == "名詞接続" || token.detailedPos.orNull == "数接続")) || (token.pos == "記号" && token.detailedPos.orNull == "アルファベット")

    def getPrevTokens(token: Token): Seq[Token] = {
        token.prev match {
            case Some(t) if isNounPhrasePos(t) => getPrevTokens(t) ++ Seq(t)
            case _ => Nil
        }
    }

    def getNextTokens(token: Token): Seq[Token] = {
        token.next match {
            case Some(t) if isNounPhrasePos(t) => Seq(t) ++ getNextTokens(t)
            case _ => Nil
        }
    }

    def complementNounPhrase(chunk: Chunk): Seq[Token] = {
        val prevTokens: Seq[Token] = chunk.tokens.headOption match {
            case Some(t) if isNounPhrasePos(t) =>
                getPrevTokens(t)
            case _ => Nil
        }
        val nextTokens: Seq[Token] = chunk.tokens.lastOption match {
            case Some(t) if isNounPhrasePos(t) =>
                getNextTokens(t)
            case _ => Nil
        }
        prevTokens ++ chunk.tokens ++ nextTokens
    }

    def getPrevParallelChunksWithoutPredicate(chunk: Chunk): Seq[Chunk] =
        chunk.depSrcs.flatMap { c =>
            if (c.depType == DepType.P && !c.hasPredicates) {
                getPrevParallelChunksWithoutPredicate(c) ++ Seq(c)
            } else Nil
        }

    def getNextParallelChunksWithoutPredicate(chunk: Chunk): Seq[Chunk] =
        chunk.depDest match {
            case Some(c) if c.depType == DepType.P && !c.hasPredicates =>
                Seq(c) ++ getNextParallelChunksWithoutPredicate(c)
            case Some(c) if c.depType == DepType.D && !c.hasPredicates => Seq(c)
            case _ => Nil
        }

    def complementParallelChunk(chunk: Chunk): Seq[Chunk] = {
        val prevChunk: Seq[Chunk] = getPrevParallelChunksWithoutPredicate(chunk)
        val nextChunk: Seq[Chunk] = chunk.depType match {
            case dt if dt == DepType.P =>
                getNextParallelChunksWithoutPredicate(chunk)
            case _ => Seq()
        }
        prevChunk ++ Seq(chunk) ++ nextChunk
    }

    def getPrevNoChunks(chunk: Chunk): Seq[Chunk] =
        chunk.prev match {
            case Some(c) if {
                val lastToken = c.tokens.last
                lastToken.surface == "の" && lastToken.pos == "助詞"
            } => getPrevNoChunks(c) ++ Seq(c)
            case _ => Nil
        }

    def getNextNoChunks(chunk: Chunk): Seq[Chunk] = {
        val lastToken = chunk.tokens.last
        if (lastToken.surface == "の" && lastToken.pos == "助詞") {
            chunk.next match {
                case Some(c) => Seq(c) ++ getNextNoChunks(c)
                case None => Nil
            }
        } else Nil
    }

    def complementNoChunk(chunk: Chunk): Seq[Chunk] =
        getPrevNoChunks(chunk) ++ Seq(chunk) ++ getNextNoChunks(chunk)

    def withComplement(chunks: Seq[Chunk]): String =
        chunks.flatMap(complementParallelChunk).distinct
            .flatMap(complementNoChunk).distinct
            .flatMap(complementNounPhrase).distinct
            .sortBy(_.id)
            .map(_.surface).mkString
}
