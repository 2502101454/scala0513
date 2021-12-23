package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2021-12-03 1:36 下午
 * @function:
 */
object Leet095 {
  /**
   * 最长公共子序列: 只求长度，
   * 自己加难度: 可以不用求子序列是什么(最优解可能有很多，只求出一组最长的子序列即可)
   *
   * 给定两个字符串text1 和text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
    一个字符串的子序列是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
    例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
    两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

    参考B站视频: https://www.bilibili.com/video/BV14q4y1D7jZ/

    第一步: 设计状态
    f[i][j] 表示序列Si = {X1, X2,...Xi}, Sj = {Y1, Y2, ...Yj} 的最长公共子序列的长度
    二维数组的元素值是最长共子序列的长度, f[i][j]这个位置也是最后的结果

    f[i][j]中的 i, j代表当前长度为i和j的两个序列

    根据场景推导如下结论
    第二步: 状态转移方程

    2.1 f[i][j] = 0, 当 i = 0  or j = 0
    # 边界问题：即两个序列之中有空集， 则肯定最长公共子序列长度为0了, 如S1="abc" S2=""


    2.2 f[i][j] = f[i-1][j-1] + 1，当Xi == Yj时
    # 如: Si=abcd Sj=lmid, 转换为f[i-1][j-1] + 1，这个1代表最后一个元素都相等,
          1是已经匹配，看前面abc和lmi的最长公共子序列长度

    2.3 f[i][j] = max(f[i-1][j], f[i][j-1]) , 当Xi != Yj时
    # 如果: Si=abcd  Sj=acde, 最后一个元素不相等，那我们得换着看
      abc (i - 1) 和 acde (j) 这两部分的公共子序列是2
      abcd (i)  和 acd (j - 1) 这两部分的公共子序列是3
      所以a b c d 和 a c d e的 最长 公共子序列长度为 max(2, 3) = 3


    总结，相等就取对角线上一个元素(i-1)(j-1)加 1, 不相等就是Max(上方、左边)

   * @param text1
   * @param text2
   * @return
   */
  def longestCommonSubsequence(text1: String, text2: String): Unit = {
    // 设计状态, 默认是0，刚好省去了对i = 0 or j = 0时候的空集对应的数组元素设0
    val states: Array[Array[Int]] = Array.ofDim(text1.length + 1, text2.length + 1)
    // 为什么数组要多出0 行， 0列呢？ 因为从1行，1列开始，计算需要用到对角线上、
    // 上方、左边的元素，所以0行0列就是用来弥补这个的

    // 二维数组填表, 从1行1列开始
    for (i <- 1 to text1.length) {
      for (j <- 1 to text2.length) {
        // 状态转移方程，当Xi == Yj时
        // 字符串的字符是从下标 0开始的，所以要 - 1，不明白就画表格
        if (text1(i - 1) == text2(j - 1)) {
          states(i)(j) = states(i - 1)(j - 1) + 1
        } else {
          // 当Xi != Yj 时
          states(i)(j) = math.max(states(i - 1)(j), states(i)(j - 1))
        }
      }
    }

    // 数组最后一个元素 states(text1.length)(text2.length)
    // 这个位置代表遍历结束了两个字符串所有的字符
    println(states(text1.length)(text2.length)) // f(i)(j)

    /**
     * 回溯求出一个最长公共子序列
     * 1.从states(text1.length)(text2.length) 这个位置开始，判断字符是否相等，从而设置递归方向
     * 2. i == 0 或者 j == 0则递归结束
     *
     */

    def LCS(i: Int, j: Int): Unit = {
      if (i == 0 || j == 0) {
        return
      }
      // 和上面一样，text 下标从0开始
      // 字符相等 则是由对角线上的推导出来的，所以去推导对角线
      if (text1(i - 1) == text2(j - 1)) {
        LCS(i - 1, j - 1)
        println(text1(i - 1))

        // 字符不相等，则求MAX(左边，上方)
      } else if (states(i)(j - 1) > states(i - 1)(j)){
        // 如果左边的比上方的大，则一定是从左边推导来的，所以递归左边
        LCS(i, j - 1)
      } else {
        // 如果左边比上方小，或者一样大，我们都递归上方
        LCS(i - 1, j)
      }
    }

    LCS(text1.length, text2.length)
  }



  def main(args: Array[String]): Unit = {
    longestCommonSubsequence("abcd", "acde")
    longestCommonSubsequence("", "acde")
    // 对空串也兼容
    longestCommonSubsequence("abcd", "")
  }
}
