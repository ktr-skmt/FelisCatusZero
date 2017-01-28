package util

/**
  * @author K.Sakamoto
  */
object ExecutionTime {
  /**
    * 処理の平均実行時間をミリ秒単位で標準出力
    */
  def printlnAverageExecutionTime(executionTime: Long, n: Int): Unit = {
    printf("%,d [nano seconds] (the average of %,d times)%n", executionTime, n)
  }
}

/**
  * 処理の実行時間計測
  *
  * @param n 試行回数
  * @author K.Sakamoto
  */
class ExecutionTime(val n: Int) {
  /**
    * 処理の平均実行時間をミリ秒単位で標準出力
    *
    * @param proc 処理
    */
  def printlnAverageExecutionTime(proc : => Unit): Unit = {
    ExecutionTime.printlnAverageExecutionTime(getAverageExecutionTime(proc), n)
  }

  /**
    * 処理の平均実行時間を測定
    * 引数のproc : => Unitは、call-by-name (delayed evaluation)なので複数回呼び出し可能
		* 
    * @param proc 処理
    * @return 処理の平均実行時間
    */
  def getAverageExecutionTime(proc : => Unit): Long = {
    //オーバーフロー対策としてBigIntを使用
    var total: BigInt = BigInt(0)
    Iterator.range(0, n + 10).foreach {
      i =>
        if (10 < i) {
          total += getExecutionTime(proc)
        }
    }
    //平均化で浮動小数を使用するためにBigDecimalに変換し、平均化後、Doubleに変換
    math.round((BigDecimal(total) / n).toDouble)
  }

  /**
    * 実行時間を測定
    *
    * @param proc 処理
    * @return 処理の実行時間
    */
  def getExecutionTime(proc : => Unit): Long = {
    //System.currentTimeMillisより精度が高いSystem.nanoTimeを使用
    val startTime: Long = System.nanoTime
    proc
    val endTime: Long = System.nanoTime
    endTime - startTime
  }
}
