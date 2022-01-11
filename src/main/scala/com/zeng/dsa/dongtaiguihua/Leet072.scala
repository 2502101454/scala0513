package com.zeng.dsa.dongtaiguihua


/**
 *
 * @author zengwang
 * @create 2022-01-03 8:06 下午
 * @function:
 */
object Leet072 {
  /**
   * 编辑距离
   * 给你两个单词word1 和word2，请你计算出将word1转换成word2 所使用的最少操作数。
   * 你可以对一个单词进行如下三种操作：
   *  插入一个字符
   *  删除一个字符
   *  替换一个字符

   * 题解，有点类似于最长公共子串:
   * 1.定义状态容器 dp[i][j]，代表 将word1到i位置转换成word2到j位置，最小的步数。这个转换就是上面的三种操作
   * 2.初始化状态容器，加入初始空字符，方便计算
   * 3.状态转移方程:
   *  3.1 if (w1(i) == w2(j)) dp[i][j] = dp[i-1][j-1]
   *  解释: 当前w1(i)和w2(j)字符相等时，当前字符不用变化，
   *  所以依赖前(i-1)部分转为前(j-1)部分的最少操作数，即dp[i-1][j-1]
   *
   *  3.2 if (w1(i) != w2(j)) dp[i][j] = 1 + min(dp[i-1][j-1], dp[i][j-1], dp[i-1][j])
   *  解释: 当前w1(i)和w2(j)字符不相等时，为了把word1 i部分转换为word2 j部分，可以有三种处理情况:
   *    a. i-1 部分 转成 j-1部分，然后w1(i) 更新为 w2(j)即可，对应dp[i-1][j-1]
   *    b. i-1 部分 转成 j部分，当前w1(i) 删除掉(多余的字符)，对应dp[i-1][j]
   *    c. i 部分 转成 j-1部分，再添加字符w2(j) 完成整个j部分的转换，对应dp[i][j-1]
   *    注意: 不用担心i-i 字符个数不够，转换是包含插入字符的操作的
   *   上面每种情况对应这不同的步数，所以求其中步数最少的方式，+ 1 当前为了完成转换，abc中额外的字符操作
   *
   *   从二维数组(表格角度)理解，在处理当前位置i、j时，(左、对角、上位置的dp已经处理完毕)
   *   对角: dp[i-1][j-1] 代表的额外操作是更新
   *   左边: dp[i][j-1] 代表的额外操作是新加
   *   上边: dp[i-1][j] 代表的额外操作是删除
   * @param args
   */
  def main(args: Array[String]): Unit = {
    var res = minDistance("horse", "ros")
//    res = minDistance("intention", "execution")
//    res = minDistance("", "a")
    println("=========")
    res = minDistance("distance",  "springbok")
    println(res)

  }

  def minDistance(word1: String, word2: String): Int = {
    val wl1 = word1.length
    val wl2 = word2.length
    if (wl1 == 0) return wl2
    if (wl2 == 0) return wl1

    val dp: Array[Array[Int]] = Array.ofDim(wl1 + 1, wl2 + 1)
    // 初始化dp, 行、列0下标 都是空字符边界
    for (i <- 0 to wl1) {
      dp(i)(0) = i
    }
    for (j <- 0 to wl2) {
      dp(0)(j) = j
    }

    val chars1: Array[Char] = word1.toCharArray()
    val chars2: Array[Char] = word2.toCharArray()
    // dp行列下标从1开始代表单词字符, 填充dp数组
    for (i <- 1 to wl1) {
      for (j <- 1 to wl2) {
        if (chars1(i - 1) == chars2(j - 1)) {
          dp(i)(j) = dp(i - 1)(j - 1)
        } else {
          dp(i)(j) = 1 + math.min(math.min(dp(i - 1)(j - 1), dp(i - 1)(j)), dp(i)(j - 1))
        }
      }
    }
//    dp.foreach(line => {println(line.mkString("_"))})
//    dp.foreach(_.mkString).fo
    dp(wl1)(wl2)
  }
}
