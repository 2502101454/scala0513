package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-04-27 8:09 下午
 * @desc:
 */
object LCSSample {
  def main(args: Array[String]): Unit = {
    // 2
    val n: Int = longestContinuousCommonSubsequence("abcd", "acde")
    // 5
    val n2: Int = longestContinuousCommonSubsequence("abcdaaaaa", "acdeaaaaa")
    println(n2)
  }

  /***
   * 最长*连续*公共子序列，注意是要连续，这个题是最长公共子序列的变换题，字节抖音直播1面考了，没搞出来，日狗！
   * 比如：
   * s1 = abcd  s2 = acde
   * 两者的最长公共子序列是 acd，最长连续公共子序列是cd。这里我们求长度即可
   *
   * 动态五部:
   * 1.明确dp数组的下标和含义
   * dp[i][j] 表示以s1[i] s2[j]结尾的最长连续公共子列长度，注意！s1[i] s2[j]是连续公共子串的一部分
   * 比如ab , ac 当s1[i]=b, s2[j]=c时，dp[i][j]=0，因为此时b =! c，不符合连续公共的意义，dp[i][j]=0
   *
   * 2.状态转换方程
   * 和公共子序列一样，引入空串机制
   * dp[i][0] = 0 or dp[0][i] = 0
   *
   * if s[i] == s[j]，dp[i][j] = dp[i-1][j-1] + 1 (
   * 两种case都给含了:
   * -1位相等，则当前s[i],s[j] 继续构成新的子串；
   * 如果前面-1位不相等，s[i],s[j]作为新的开头)
   *
   * if s[i] != s[j], dp[i][j] = 0，断开的case
   *
   * 3.初始化
   * dp[i][0] = 0 or dp[0][i] = 0
   * 4.遍历顺序
   * 一行行遍历
   * 5.打印dp
   *
   * @param text1
   * @param text2
   */
  def longestContinuousCommonSubsequence(text1: String, text2: String): Int = {
    val chars1: Array[Char] = text1.toCharArray
    val chars2: Array[Char] = text2.toCharArray
    // 初始化，默认是0
    val dp: Array[Array[Int]] = Array.ofDim(chars1.length + 1, chars2.length + 1)

    // 其实我们定义的dp求的只是局部最优，题目要求全局最优，即把所有case都遍历到后，找到全局最长的！
    var maxLen: Int = 0
    for (i <- 1 until dp.length) {
      for (j <- 1 until dp(0).length) {
        if (chars1(i - 1) == chars2(j - 1)) {
          dp(i)(j) = dp(i - 1)(j - 1) + 1
          if (dp(i)(j) > maxLen) {
            maxLen = dp(i)(j)
          }
        }
        // else dp(i)(j) = 0不用写，默认就是0
      }
    }
    maxLen
  }
}
