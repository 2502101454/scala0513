package com.zeng.dsa.array
import scala.collection.mutable
/**
 *
 * @author zengwang
 * @create 2022-01-22 3:01 下午
 * @desc:
 */
object Offer12 {
  def main(args: Array[String]): Unit = {
    var board: Array[Array[Char]] = Array(
      Array('A','B','C','E'),
      Array('S','F','C','S'),
      Array('A','D','E','E')
    )
    var word = "ABCCED"
    var res = exist(board, word)
    println(res)
  }

  /**
   * 给定一个m x n 二维字符网格board 和一个字符串单词word 。
   * 如果word 存在于网格中，返回 true ；否则，返回 false 。
   * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
   * 同一个单元格内的字母不允许被重复使用。
   *
   * 分析:
   * 回溯法：
   * 1.分阶段
   * 2.代码表达出每个阶段做的事情，以及承上启下
   *
   * 1.对于二维数组的每个位置，都可以作为单词的首字母并开始判断
   * 2.当数组当前位置命中单词的首字母时，接着查看下一个字母
   * @param board
   * @param word
   * @return
   */
  def exist(board: Array[Array[Char]], word: String): Boolean = {
    val everPositions: mutable.Map[String, Boolean] = mutable.Map[String, Boolean]()
    val chars: Array[Char] = word.toCharArray
    for (i <- board.indices) {
      for (j <- board(0).indices) {
        val res = recursiveFind(i, j, board, chars, 0, everPositions)
        if (res) return true
        // 当找不到路径的时候，下面进行回溯remove，最后缓存自己就为空了
        // everPositions.clear()
      }
    }
    false
  }

  /**
   *  在二维数组的(i, j)位置开始查找第n个字符
   * @param i
   * @param j
   * @param board
   * @param chars
   * @param n
   * @param everPositions
   * @return
   */
  def recursiveFind(i: Int, j: Int, board: Array[Array[Char]], chars: Array[Char],
                    n: Int, everPositions: mutable.Map[String, Boolean]): Boolean = {
    if (n == chars.length) return true
    // i，j边界判断
    if (i < 0 || i >= board.length) return false
    if (j < 0 || j >= board(0).length) return false

    // 当前位置曾经遍历过判断
    if (everPositions.getOrElse(f"${i}_${j}", false)) return false

    if (board(i)(j) == chars(n)) {
      everPositions.put(f"${i}_${j}", true)
      // 下
      // 右
      // 上
      // 左
      val res = recursiveFind(i + 1, j, board, chars, n + 1, everPositions) ||
        recursiveFind(i, j + 1, board, chars, n + 1, everPositions) ||
        recursiveFind(i - 1, j, board, chars, n + 1, everPositions) ||
        recursiveFind(i, j - 1, board, chars, n + 1, everPositions)

      /**
       * 当前字母找到了，但是在当前字母的周围，下一个字母没有找到，
       * 所以从开始到当前字母的路径不会是该单词的正确路径
       * 因此，要剔除当前字母的缓存，然后回溯到上一个字母的位置，重新选择别的字母
       *
       * 为什么要剔除当前字母的缓存?
       * 如果当前字母和后面的某个字母重复，到时候需要引用当前字母
       *
       */
      if (! res) everPositions.remove(f"${i}_${j}")
      return res
    }
    false
  }
}
