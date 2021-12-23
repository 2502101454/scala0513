package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2021-12-14 9:47 上午
 * @function:
 */
object Leet05 {
  def main(args: Array[String]): Unit = {
    var s = "babad"
    s = "babadacd"
    s = "a"
    s = "aa"
    println(longestPalindrome(s))
  }

  /**
   * 找出一个字符串的最长回文子串
   * (此题的中心扩散法解参考string中的Leet05)
   * 题解: 动态规划法
   * 1.状态容器
   *  定义dp[i][j] 表示下标从i到j的一个子串 是否是回文串，dp[i][j]存储Boolean
   *  思考范围:
   *    i<=j 从前向后表示一个的子串；
   *
   * 2.初始化状态
   * i == j 表示子串就是一个字符，则本身就是回文
   * 但是这步我们不用做，一起放在状态转移的过程中做
   *
   * 3.状态转移方程
   * i <= j
   * 如果s(i) != s(j)， 从i到j肯定不是一个回文子串，dp[i][j]=false
   * 如果s(i) == s(j)，分如下情况:
   *    a. (j - i) < 3
   *    比这些子串，a(i == j), aa, aba， 两端相等，i和j距离差<=2, 都是回文，dp[i][j] = true
   *
   *    b. 除了a之外，i j之间距离足够远，有一些其他字符，比如 a x x a，假设x是未知字符，
   *    那么就得看中间这部分串是不是回文了，中间不是回文的话，两头相等也没用
   *    dp[i][j] = dp[i+1][j-1]
   *
   * 4.收集最大的回文子串的长度
   *
   * 两个不同：
   *  1.最长回文串长度不是取最后一层
   *  2.状态不是按照二维数组一层层推导的，上层状态i + 1，j - 1 反而在当前状态i j的下行
   * @param s
   * @return
   */
  def longestPalindrome(s: String): String = {
    val chars: Array[Char] = s.toCharArray
    val c_len = chars.length
    // 初始化状态数组 dp(i)(j)表示下标从i到j的子串
    val dp: Array[Array[Boolean]] = Array.ofDim(c_len, c_len)

    // 初始化回文子串长度
    var maxL = 0
    // 初始化最大回文子串的开头下标
    var ml = 0
    // 注意这里要满足i <= j，所先从j开始，对应到二维数，就是数组的上半部分区域（含对角线）
    for (j <- 0 until c_len) {
      for (i <- 0 to  j) {
        if (chars(i) != chars(j)) {
          dp(i)(j) = false
        } else {
          if (j - i < 3) {
            dp(i)(j) = true
          } else {
            dp(i)(j) = dp(i + 1)(j - 1)
          }
        }

        // 如果当前i到j的子串是回文，尝试更新最大回文子串长度
        if (dp(i)(j) && (j - i + 1) > maxL) {
          maxL = (j - i + 1)
          ml = i
        }
      }
    }

    chars.slice(ml, ml + maxL).mkString("")
  }
}
