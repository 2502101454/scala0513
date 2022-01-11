package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2022-01-05 9:36 上午
 * @function:
 */
object Leet042 {
  def main(args: Array[String]): Unit = {
    var height = Array(0,1,0,2,1,0,1,3,2,1,2,1)
    height = Array(4,2,0,3,2,5)
    val water = trap2(height)
    println(water)
  }

  /**
   * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
   *
   * 此题解法可以使用动态规划，这里是按列求解法(计算每列柱子的存水量)
   *
   * 对于每个柱子i，求其左边最高柱子A、右边最高柱子B，
   * 1.如果两边最高柱子中的较矮的柱子 > 柱子i，则形成了凹槽，可以存水
   *  存水量为：较矮柱子 - 当前柱子 (木桶原理)
   * 2.如果两边最高柱子中的较矮的柱子 <= 柱子i，无法形成凹槽，则不可以存水（自己可以画图分析）
   *
   * @param height
   * @return
   */
  def trap2(height: Array[Int]): Int = {
    // 对于第一根柱子，左边没有柱子了，认为左边柱子是0，
    // 对于最后一根柱子，右边没有柱子了，认为右边柱子是0，
    // 0肯定是最矮的柱子，且小于等于第一根柱子，无法形成凹槽，所以第一根柱子不存水
    // 0肯定是最矮的柱子，且小于等于最后一根柱子，无法形成凹槽，所以最后一根柱子不存水

    var water = 0
    // !!首尾柱子不存水，跳过!!，遍历每列柱子
    for (i <- 1 to height.length - 2) {
      // 寻找左边最高柱子高度
      var maxLeft = height(0)
      for (left <- 0 until i) {
        if (maxLeft < height(left)) maxLeft = height(left)
      }

      // 寻找右边最高柱子高度
      var maxRight = height(i + 1)
      for (right <- i + 1 until height.length) {
        if (maxRight < height(right)) maxRight = height(right)
      }

      // 求两边最高柱子中的较矮柱子
      val minHeight = math.min(maxLeft, maxRight)
      if (minHeight > height(i)) water += minHeight - height(i)
    }

    water
  }

  /**
   * 动态规划优化:
   * 但是上面每次都是从头开始重新找最高的左墙或者右墙，存在大量的重复计算 引入缓存
   * 1.状态容器
   *  maxLeft[i] 代表下标为i的柱子，其左边最高的柱子高度，注意这里是不包括柱子i
   *  maxRight[i] 代表下标为i的柱子，其右边最高的柱子高度，注意也不包括柱子i
   *
   * 2.初始
   * maxLeft[0] = 0
   * maxRight[height.length - 1] = 0
   *
   * 3.状态转移
   * maxLeft[i] = max(height(i-1), maxLeft(i-1))
   * 注意maxRight是从后往前算，所以i下标是从后往前移动
   * maxRight[i] = max(height(i+1), maxRight(i+1))
   *
   * @param height
   * @return
   */
  def trap(height: Array[Int]): Int = {
    val hLength: Int = height.length
    // 初始化状态数组
    val maxLeft = new Array[Int](hLength)
    val maxRight = new Array[Int](hLength)
    maxLeft(0) = 0
    maxRight(hLength - 1) = 0

    // 我们遍历每根柱子的时候，因为首尾柱子存水是0，所以跳过首尾，这里也就不用计算首尾柱子的状态容器
    for (i <- 1 until hLength - 1) {
      maxLeft(i) = math.max(height(i - 1), maxLeft(i - 1))
    }
    // 注意顺序，从后向前
    for (i <- (1 to hLength - 2).reverse) {
      maxRight(i) = math.max(height(i + 1), maxRight(i + 1))
    }

    var water = 0

    for (i <- 1 to height.length - 2) {
      val maxLeftHeight = maxLeft(i)
      val maxRightHeight = maxRight(i)
      // 求两边最高柱子中的较矮柱子
      var minHeight = math.min(maxLeftHeight, maxRightHeight)
      if (minHeight > height(i)) water += minHeight - height(i)
    }

    water
  }
}
