package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-01-16 2:13 下午
 * @function:
 */
object Offer10_2 {
  def main(args: Array[String]): Unit = {

  }
  /**

  * */

  /**
   * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个n级的台阶总共有多少种跳法。
   * !!!答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
   *
   * 分析：
   * 和之前的爬楼梯问题一样，一次爬一阶、和一次爬两阶
   *  动态规划:
   *    1.定义状态容器
   *    dp[n] 代表爬n阶楼梯总共的爬法
   *    2.初始化
   *    dp[1] = 1
   *    dp[2] = 2 // 2阶楼梯，两种爬法
   *    3.状态转移方程
   *    dp[n] = dp[n-1] + dp[n-2]
   *    即n阶楼梯的爬法 = 第一步爬1阶，剩下楼梯的爬法 + 第一步爬2阶，剩下楼梯的爬法
   *    ---这里可以给个样例输入帮助理解，比如n=3,自己画一下几种走法，普遍适用性---
   *
   *  这个也是**变样的斐波那契数列**，f(n) = f(n-1) + f(n-2)
   *  只不过f(2) 在这里等于 2，在正确的斐波那契中f(2) = 1
   * @param n
   * @return
   */
  def numWays(n: Int): Int = {
    if (n <= 1) return 1
    val dp = new Array[Int](n + 1)
    dp(1) = 1
    dp(2) = 2
    for (i <- 3 to n) {
      dp(i) = (dp(i - 1) + dp(i - 2)) % 1000000007
    }
    return dp(n)
  }

  /**
   * 再优化，使用斐波那契公式，不需要O(n)的空间
   * f(n) = f(n-1) + f(n-2)
   * @param n
   * @return
   */
  def numWaysV2(n: Int): Int = {
    if (n <= 1) return 1

    var a = 1 // f1
    var b = 1 // f0
    var sum = 0
    for (i <- 2 to n) {
      // f2 = f1 + f0
      sum = (a + b) % 1000000007
      b = a
      a = sum
    }
    sum
  }
}
