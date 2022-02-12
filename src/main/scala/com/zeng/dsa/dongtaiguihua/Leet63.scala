package com.zeng.dsa.dongtaiguihua

import scala.util.control.Breaks
import scala.util.control.Breaks.breakable

/**
 *
 * @author zengwang
 * @create 2022-01-28 9:46 上午
 * @desc:
 */
object Leet63 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 相比leet 62题，机器人要走的网格中有了障碍物，障碍物的位置在二维数组中有1表示，空位置用0表示
   *
   * 1.明确dp数组以及下标含义
   * dp(i)(j) 代表从坐标(0,0)到(i, j)的路径数
   *
   * 2.状态转移方程
   * dp(i)(j) = dp(i-1)(j) + dp(i)(j-1)
   * 需要注意: obstacleGrid(i, j)是障碍物时，到达障碍物的位置是没有路径的，因此dp(i)(j)=0
   *
   * 3.初始化
   * 同样横竖两条边dp(i)(0) dp(0)(j)都是1吗？
   * 注意如果在边上面的某个位置有障碍物，那么障碍物之后的边上所有位置都没有路径，dp得标记成0
   * 因为机器人只能向右、向下移动，没办法绕过去~
   *
   * 4.遍历顺序
   * 依旧是从左至右一层层的遍历，这样保证了上方、左方的位置都先有值，
   * 即推导dp(i)(j)的时候，dp(i-1)(j) 和 dp(i)(j-1)一定先有值
   *
   * 5.举例推导
   *
   *
   * @param obstacleGrid
   * @return
   */
  def uniquePathsWithObstacles(obstacleGrid: Array[Array[Int]]): Int = {
    var m = obstacleGrid.length
    val n: Int = obstacleGrid(0).length
    val dp: Array[Array[Int]] = Array.ofDim(m, n)
    // 初始化dp, 障碍物后的位置，包括障碍物。路径个数为0
    breakable(
      for (i<- 0 until m) {
        if (obstacleGrid(i)(0) == 1) Breaks.break()
        dp(i)(0) = 1
      }
    )
    breakable(
      for (j<- 0 until n) {
        if (obstacleGrid(0)(j) == 1) Breaks.break()
        dp(0)(j) = 1
      }
    )

    //遍历顺序
    for (i <- 1 until m) {
      for (j <- 1 until n) {
        // 注意障碍物的位置，路径数为0
        // if (obstacleGrid(i)(j) == 1)
        if (obstacleGrid(i)(j) == 0)
          dp(i)(j) = dp(i-1)(j) + dp(i)(j-1)
      }
    }

    dp(m-1)(n-1)
  }
}
