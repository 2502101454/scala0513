package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-02-07 10:30 下午
 * @desc:
 */
object package01_v2 {
  def main(args: Array[String]): Unit = {
    val weight: Array[Int] = Array(2, 2, 4, 6, 3)
    val n = 5
    val limitWeight = 9
    val value: Array[Int] = Array(3, 4, 8, 9, 6)
  }

  /**
   * 0-1背包，基于我对动态规划五部曲理解后解法
   *
   * 我的理解不对，这里只是为了方便记忆，以防面试考0-1背包问题
   * TODO: correction, 看carle的0-1背包视频，接着啃他的动态规划文档
   *
   * 题目: 有n个物品，第i个物品对应的重量是weight[i]、对应的价值是value[i]，背包限重w，
   * 要求在满足限重的前提下，背包的最大重量是多少?
   *
   * 1.明确dp数组以及下标含义
   * dp[i][j]
   * 代表决策完第i个物品**后**，背包当前重量是j，此时背包的**最大价值**存于dp[i][j]
   *
   * 2.动态转化方程
   * 对于每个物品而言，都有放和不放入背包两种情况
   * 对于第i个物品的做决策也是如下两种:
   *  不放入i: dp[i][j] = dp[i-1][j] 记做C1
   *  放入i: dp[i][j + weight(i)] = dp[i-1][j] + value[i] and j + weight[i] <= 限重W 记做C2
   *  这里的j都是上层i-1对应的重量
   *  所以有两个递推公式，但是需要额外处理一下，因为第一遍先决策不让入的情况，第二遍在决策放入的情况，
   *  在决策第二遍的过程中，j + weight(i)的位置可能在第一遍决策时已经写入价值了，所以我们要对比选择价值最大的
   *
   *  (PS: j 不是一个具体的值，是质量状态的演进过程, 它可以是很多值，从小到大)
   *
   * 理解误区，并不是i放入肯定比i不放入 价值大！因为还依赖于i-1的状态
   * 如果i-1放入，i不放入 记做A；i-1 不放入，i 放入，记做B
   * A和B的价值还真不好说，而且这还只是推演到i-1，i-2.....0都没推演
   *
   * 3.初始化
   * 3.1 初始化整个dp数组为-1，这样可以很简单找到上个有状态的位置，然后体现基于上层状态集合，推导当前状态集合(王争的笔记总结)
   *    因为上层做了决策，价值一定不是-1, 脑海里体会整个二维数组的填充过程~
   * 3.2
   *  对于物品0 (i=0)， i-1就没法求了，所以初始化物品0，然后从i=1开始循环
   *  物品0不放入，dp(0)(0) = 0
   *  物品0放入, dp(0)(weight(0)) = value(0) and weight(0) <= w， 如果物品0超重，就只能走不放入了
   *
   * 4.遍历顺序
   * 从前向后
   *
   * 5.打印dp数组
   *
   * @param weight
   * @param value
   * @param n
   * @param w
   * @return
   */
  def knapsack3(weight: Array[Int], value: Array[Int], n: Int, w: Int): Int = {
    val dp: Array[Array[Int]] = Array.ofDim(n, w + 1)
    // 初始化
    for (i <- 0 until n) {
      for (j <- 0 to w) {
        dp(i)(j) = -1
      }
    }
    // 第一个物品不放入
    dp(0)(0) = 0
    // 第一个物品放入
    if (weight(0) <= w) dp(0)(weight(0)) = value(0)

    //从第二个物品开始遍历
    for (i <- 1 until n) {
      //体会基于上层状态推导当前状态的感觉,脑子里有dp二维数组的样子
      // 当前i不放入, dp(i)(j) = dp(i-1)(j)
      for (j <- 0 to w) {
        // 当上层的该位置存在状态时
        if (dp(i-1)(j) >= 0) dp(i)(j) = dp(i-1)(j)
      }
      // 当前i放入, 但是不能超过限重，所以可选的上层状态只能小于w-wight(i)
      // dp(i)(j + wight(i)) = dp(i-1)(j) + value(i)
      for (j <- 0 to w-weight(i)) {
        if (dp(i-1)(j) >= 0) {
          val curValue = dp(i-1)(j) + value(i)
          if (curValue > dp(i)(j+weight(i))) dp(i)(j+weight(i)) = curValue
        }
      }
    }

    // 在决策完最后一个物品后，找所有状态中的最大价值
    var maxValue = -1
    for (i <- 0 to w) {
      if (maxValue < dp(n-1)(i)) maxValue = dp(n-1)(i)
    }

    maxValue
  }
}
