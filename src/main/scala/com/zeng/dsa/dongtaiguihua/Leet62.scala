package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-01-27 1:18 下午
 * @desc:
 */
object Leet62 {
  def main(args: Array[String]): Unit = {
    var res = uniquePaths(3, 2)
    println(res)
  }

  /**
   * 一个机器人位于一个 m x n网格的左上角 （起始点在下图中标记为 “Start” ）。
   * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
   * 问总共有多少条不同的路径？
   *
   * 一眼想着用回溯法，但是会超时，所以回溯问题，也可以使用动态规划求解
   * 五部曲：
   * 1.明确dp数组及下标定义
   * dp[i][j] 代表从下标(0, 0)到(i, j)位置总共的路径
   *
   * 2.转移方程
   * 依据题意，只能向左、向下移动，所以到达(i,j)位置的路径数 等于 到达(i, j)上方、左方位置的路径数之和
   * dp[i][j] = dp[i][j-1] + dp[i-1][j]
   *
   * 3.dp初始化
   * dp[0][j] = 1，从(0,0)到(0, j)的路径只有一条，因为机器人只能向右、向下移动，因此同理
   * dp[i][0] = 1
   *
   * 4.遍历顺序
   * 从前向后，一层层遍历保证前面的先有值
   *
   * 5.举例推导
   *
   * @param m
   * @param n
   * @return
   */
  def uniquePaths(m: Int, n: Int): Int = {
    val dp: Array[Array[Int]] = Array.ofDim(m, n)
    for (i <- 0 until m) {
      dp(i)(0) = 1
    }
    for (j <- 0 until n) {
      dp(0)(j) = 1
    }

    for (i <- 1 until m) {
      for (j <- 1 until n) {
        dp(i)(j) = dp(i-1)(j) + dp(i)(j-1)
      }
    }
    dp(m-1)(n-1)
  }
}
