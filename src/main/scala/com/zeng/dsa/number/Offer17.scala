package com.zeng.dsa.number

/**
 *
 * @author zengwang
 * @create 2022-02-17 12:57 下午
 * @desc:
 */
object Offer17 {
  def main(args: Array[String]): Unit = {
    printNumbers(2).foreach(println)
  }

  /**
   * 打印从1 到最大的 n位 十进制数
   * @param n
   * @return
   */
  def printNumbers(n: Int): Array[Int] = {
    // 计算出n位数所能代表的最大的十进制数，肯定各个位都是9
    var maxNum = 0
    for ( i <- 0 to n - 1) {
      maxNum += math.pow(10, i).toInt * 9
    }

    val ints = new Array[Int](maxNum)
    for (j <- ints.indices) {
      ints(j) = j + 1
    }

    ints
  }
}
