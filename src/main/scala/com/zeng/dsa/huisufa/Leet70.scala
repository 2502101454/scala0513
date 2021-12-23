package com.zeng.dsa.huisufa

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-12-20 10:29 下午
 * @function:
 */
object Leet70 {
  // 定义函数调用中，参数对应的返回值缓冲，防止递归调用重复计算太多
  // key是参数，value是返回值
  val call: mutable.Map[Int, Int] = mutable.Map(1->1, 2->2, 3->3)
  def main(args: Array[String]): Unit = {
    println(climbStairs(4)) // 5
    println(climbStairs(5)) // 8
    println(climbStairs(6)) // 13
    println("=============")
    // 发现规律，有点斐波那契数列内味了，设plan(n)表示 n个台阶的走法，那么plan(n) = plan(n-1) + plan(n-2)
    println(climbStairs2(1)) // 13
    println(climbStairs2(6)) // 13
  }

  /**
   * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
   * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
   * 注意：给定 n 是一个正整数。
   *
   * 分析：
   *    回溯法，
   *    1. 划分阶段，每阶段对应每走一步
   *    2. 代码表现出当前阶段的处理(即 每步要么跨两个台阶，要么跨一个台阶)，然后承接下个阶段
   *    3. 递归的退出，当前累积走的台阶数是n了
   *    第一步走1个台阶后，剩余台阶的走法；和 第一步走2个台阶，剩余台阶的走法；
   *    相加一起就是整体n个台阶的走法
   *
   *    技巧，简单用例输入，用笔画一下函数的调用栈(二叉树那种，同时注明函数参数情况)，
   *    就是穷举的思路，找到满足的情况就返回
   *
   * @param n
   * @return
   */
  def climbStairs(n: Int): Int = {
    if (call.contains(n)) return call(n)
    if (n == 0) {
      return 1
    } else if (n < 0) {
      return 0
    }
    var planNums = climbStairs(n - 1) +  climbStairs(n - 2)
    call.put(n, planNums)
    planNums
  }

  /**
   * 动态规划法
   *
   * 1.状态容器 dp(i) 表示台阶数为i时，有多少种的走法
   * 2.初始化dp数组
   * 3.状态转义方程
   *  dp(0) = 0
   *  dp(1) = 1
   *  dp(2) = 2
   *
   *  dp(i) = dp(i-1) + dp(i-2)  if i > 2
   *  解释: 在当前台阶数为i的情况下，我们第一步走1个台阶的走法，对应dp(i-1),
   *  一个台阶是定的了，只能看剩下台阶的走法了；同理第一步走2个台阶的走法，dp(i-2)
   *  两者相加即为当前台阶数为i的走法了
   * @param n
   * @return
   */
  def climbStairs2(n: Int): Int = {
    if (n == 1 || n == 2) {
      return n
    }
    // 初始化状态容器
    val dp: Array[Int] = new Array[Int](n + 1)
    dp(1) = 1
    dp(2) = 2

    for (i <- 3 to n) {
      println("jjjjjj")
      dp(i) = dp(i - 1) + dp(i - 2)
    }
    dp(n)
  }

}
