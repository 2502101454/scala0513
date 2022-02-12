package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-01-27 9:29 上午
 * @desc:
 */
object Leet746 {
  def main(args: Array[String]): Unit = {
    val cost: Array[Int] = Array(10, 15, 20)
    var res = minCostClimbingStairs(cost)
    println(res)
  }

  /**
   * 给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。
   * 一旦你支付此费用，即可选择向上爬一个或者两个台阶。
   * 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。
   * 请你计算并返回达到楼梯顶部的最低花费
   *
   * DP五部曲:
   * 1.确定dp数组以及下标的含义
   * dp[i] 表示达到i阶楼梯时的最小花费
   *
   * 2.递推公式
   * 思路：
   * 题目要求登顶，登顶的状态就是不在楼梯上了，但是登顶前，我一定在楼梯上，所以我有如下**登顶**方式:
   * a.我在第i-1的阶梯，一步跨2阶梯登顶
   * b.我在第i的阶梯，一步跨1阶登顶
   * c.我在第i的阶梯，一步跨2阶登顶
   *
   * 因为最后一步登顶不需要花费体力
   * (解释:你踩在i或者i-1阶梯上，你需要支付体力，然后再一步登顶不用体力，因为顶不在cost范围)，
   * 所以我只需要看下，我到达i-1阶梯和我到达i阶梯的时候，哪个花费最小，那么就是我登顶的最小花费了！
   * 记做RC
   *
   * 转换问题: 求到达i阶梯的最小花费(i，i-1统一抽象成i)
   * dp[i] = min(dp[i-1], dp[i-2]) + cost[i]
   * + cost[i]意味着第i阶梯我是一定要踩的，这样我就到达了第i阶梯
   *
   * 3. 初始化，题目说了初始可以在第0 或者第1阶梯，初始也是要花费的
   * dp[0] = cost[0]
   * dp[1] = cost[1]
   *
   * 4.遍历顺序
   * 后面的i依赖前面的i-1，i-2，所以从前向后
   *
   * 5.出错校验，打印dp数组，核实1 2 3 4的推导
   *
   *
   * 转换问题，看看题目要求的解和我dp的最后状态是否有关联
   *
   * 优化，这种类似于斐波那契的结果可以不使用数组缓存
   * @param cost
   * @return
   */
  def minCostClimbingStairs(cost: Array[Int]): Int = {
    val dp = new Array[Int](cost.length)
    dp(0) = cost(0)
    dp(1) = cost(1)
    for (i <- 2 until cost.length) {
      dp(i) = math.min(dp(i - 1), dp(i - 2)) + cost(i)
    }
    math.min(dp(cost.length-1), dp(cost.length-2))
  }
}
