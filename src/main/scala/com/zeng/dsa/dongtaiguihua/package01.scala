package com.zeng.dsa.dongtaiguihua

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-11-29 10:41 下午
 * @function:
 */
object package01 {
  def main(args: Array[String]): Unit = {
    val weight: Array[Int] = Array(2, 2, 4, 6, 3)
    val n = 5
    val limitWeight = 9
    val value: Array[Int] = Array(3, 4, 8, 9, 6)
//    println(knapsack(weight, n, limitWeight))
//    println(knapsack2(weight, n, limitWeight))
//    println(knapsack3(weight, value, n, limitWeight))
    knapsack4(weight, n, limitWeight)
  }
  /* 0-1 背包问题的动态规划版本
  对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，
  在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？

  private val maxW: Int = Integer.MIN_VALUE // 结果放到maxW中
  private int[] weight = {2，2，4，6，3}; // 物品重量
  private int n = 5; // 物品个数
  private int w = 9; // 背包承受的最大重量
   */

  /**
   *states[i][j] 代表第i个物品决策后，当前背包中的重量[用j表示]，决策就是放入或者不放入，所以就是两个值
   * @param weight 物品重量的数组
   * @param n 物品数量
   * @param w 背包限重
   * @return
   */
  def knapsack(weight: Array[Int], n: Int, w: Int): Int = {
    // 初始化状态集合数组, n行 w+1列，数组默认值是false
    val states: Array[Array[Boolean]] = Array.ofDim(n, w + 1)
    // 初始化第一行
    states(0)(0) = true // 第0个物品不放
    // 第0个物品放
    if (weight(0) <= w) {
      states(0)(weight(0)) = true
    }

    // 基于上层的状态, 对剩余物品依次做决策
    for (i <- 1 until n) {
      // 当前层决策，第i个物品不放入
      for (j <- 0 to w) {
        if (states(i - 1)(j)) states(i)(j) = states(i - 1)(j)
      }

      // 当前层决策，第i个物品放入
      // 既然决定当前物品放入了，那么放入后就不能超重
      // 设上层的累积重量记为S, S + 第i个物品重量 <= w(总限重)
      for (j <- 0 to (w - weight(i))) {
        if (states(i - 1)(j)) states(i)(j + weight(i)) = states(i - 1)(j)
      }
    }

    // 最后一个物品的决策，从后往前第一个状态重量一定是最大的(最优解)
    for (j <- Range(0, w + 1).reverse) {
      if (states(n-1)(j)) return j
    }

    0
  }

  /**
   * states[i]  i依旧表示当前背包中的重量,n个物品，循环n次即可
   * @param weight 物品重量的数组
   * @param n 物品数量
   * @param w 背包限重
   * @return
   */
  def knapsack2(weight: Array[Int], n: Int, w: Int): Int = {
    val states: Array[Boolean] = new Array(w + 1)
    // 第0个物品决策, 不放入
    states(0) = true
    // 第0个物品放入
    if (weight(0) <= w) {
      states(weight(0)) = true
    }

    // 每个阶段物品都不放入，则维护现有状态即可
    // 外层for循环，代表n个阶段；内层for循环处理每个阶段的状态集合
    for (i <- 1 until n) {
      // 把第i个物品放入背包,
      // 注意: 这里必须倒着处理
      // 如果你从0 开始处理, j下标是从前往后走，如果前面某个下标处有状态，则更新array(j+weight(i))=true
      // 而j + weight(i)这个下标有可能在接下来的循环中被遍历到，这样我们拿到它是true，就又要加一次，这就错了

      // 如果倒序处理，那么j下标是从后往前走，后面加到的weight(i)只会出现在j的后面，j继续往前推进即可，
      // 也取不到后面的下标了
      for (j <- (0 to w - weight(i)).reverse) {
        if (states(j)) states(j + weight(i)) = true
      }
    }

    for (i <- states.indices.reverse) {
      if (states(i)) return i
    }
    0
  }
  /* 对上面倒序处理状态集合的解释：
  states数组解释：该数组存储的是items中各个变量的随机组合相加的值，值存在且小于w则为true，大于w抛弃不存。
   例如 {1,2}的随机组合是不可能出现states[10] = true的情况。 代码中 for循环开始遍历items中每个变量，
   { 第 i 个变量 } 加上 { 第 i 个变量之前的所有变量随机组合的值 } ，如果小于w则修改为true
   这时就有问题了：比如{1，2，4，6}，w = 12，如果此时要计算4 ，因为之前{1，2}的随机组合 会导致
   states[0] states[1] states[2] states[3] 都是true，在计算4的时候，正确的计算会把states[4]，
   states[5]，states[6]，states[7]都记为true，如果 j 从0开始计算的话，我们在判断states[0] = true是，
   把states[4]改为了true，但是我们当前循环还会判断states[4]=true，并把states[8]改为true，
   这样就有问题了，因为单靠{1,2}这两个数字是不可能随机组合出4的，我们是在4的基础上又加了4，
   所以出现了重复计算，j从大到小计算的话，states[]大的只会更大，不会影响小states[]中的值
  * */

  /**
   *
   * @param weight
   * @param n
   * @param w
   * @return
   */
  def knapsack3(weight: Array[Int], value: Array[Int], n: Int, w: Int): Int = {
    // 初始化状态数组,状态数组存储当前背包中的物品价值
    val states: Array[Array[Int]] = Array.ofDim(n, w + 1)
    for (i <- states.indices) {
      for (j <- states(i).indices) {
        states(i)(j) = -1
      }
    }
    // 第0个物品不放入，那么背包的重量是0，价值也是0
    states(0)(0) = 0
    if (weight(0) <= w) {
      // 第0个物品放入
      states(0)(weight(0)) = value(0)
    }

    // 问题分n个阶段，每个阶段的决策更新状态数组,
    // 处理剩下n-1个阶段
    for (i <- 1 until n) {
      // 第i个物品不放入, 重量、价值都不会变
      for (j <- 0 to w) {
        // 这里是上层已有的状态
        if (states(i - 1)(j) >= 0) states(i)(j) = states(i - 1)(j)
      }

      // 第i个物品放入
      for (j <- 0 to (w - weight(i))) {
        if (states(i - 1)(j) >= 0) {
          // 既然放入，那么当前背包价值为上阶段的价值加当前物品价值
          val v = states(i - 1)(j) + value(i)
          // states(i)(j + weight(i)) 这个位置可能在上面不放入的时候更新过了
          // 比如：1.之前都放入，这次不放入 2.之前都不放入，这次放入
          // 那么1就对应states(i)(j), 2 就对应states(i)(0 + weight(i))
          // 所以1的价值可能还大于2的价值呢，所以需要对比选保留最大的价值
          // 因为命中了同一个数组位置，那么j一定是一样的，即背包重量都一样，保留最大价值显然符合题意
          if (states(i)(j + weight(i)) < v) {
            states(i)(j + weight(i)) = v
          }
        }
      }
    }
    // 当前阶段决策后的状态是基于上个阶段的状态集合

    // 找出最大价值
    var maxValue = -1
    // 这里正着、倒着找价值最大无所谓
    for (i <- 0 to w) {
      if (maxValue < states(n-1)(i)) maxValue = states(n-1)(i)
    }

    maxValue
  }


  /** 求得最大重量后，再求出选了哪些物品
   *states[i][j] 代表第i个物品决策后，当前背包中的重量[用j表示]，决策就是放入或者不放入，所以就是两个值
   * @param weight 物品重量的数组
   * @param n 物品数量
   * @param w 背包限重
   * @return
   */
  def knapsack4(weight: Array[Int], n: Int, w: Int): Unit = {
    // 初始化状态集合数组, n行 w+1列，数组默认值是false
    val states: Array[Array[Boolean]] = Array.ofDim(n, w + 1)
    // 初始化第一行
    states(0)(0) = true // 第0个物品不放
    // 第0个物品放
    if (weight(0) <= w) {
      states(0)(weight(0)) = true
    }

    // 基于上层的状态, 对剩余物品依次做决策
    for (i <- 1 until n) {
      // 当前层决策，第i个物品不放入
      for (j <- 0 to w) {
        if (states(i - 1)(j)) states(i)(j) = states(i - 1)(j)
      }

      // 当前层决策，第i个物品放入
      // 既然决定当前物品放入了，那么放入后就不能超重
      // 设上层的累积重量记为S, S + 第i个物品重量 <= w(总限重)
      for (j <- 0 to (w - weight(i))) {
        if (states(i - 1)(j)) states(i)(j + weight(i)) = states(i - 1)(j)
      }
    }

    // 找到最大重量
    var j = -1
    Breaks.breakable(
      for (i <- Range(0, w + 1).reverse) {
        if (states(n-1)(i)) {
          j = i
          Breaks.break()
        }
      }
    )

    // 没有可行解
    if (j == -1) {
      println("no plan~")
      return
    }

    println("max weight " + j)
    // 求出选择物品的方案
    /** 从下往上推导states即可
     * 每个状态，正着可以推出，由上层推下层，
     * 每个状态，反着也可以推，由当前推上层，
     * states(i)(j) 代表中第i个物品决策后的状态，第i个物品可以选，也可以不选，
     * 这两种情况都 *可能* 通过上层状态 集合 推导到states(i)(j) 的这个位置，
     * 即出现了两种可达状态，我们任选一种即可
     *
     * 最优解不止一个！我们这里只是选一个解而已
     *
     * 我们优先选择放入当前物品，去推上层状态，如果上层状态支持(上层有这个状态)
     * ，那么就选择当前物品；如果上层状态不支持，则对应不选当前物品的情况
     *
     * 当前物品选了，states(i)(j) 对应上层状态为states(i-1)(j - weight(i)) 是true
     */
      for (i <- (1 until n).reverse) {
        if (j - weight(i) >= 0 && states(i-1)(j - weight(i))) {
          println(s"select item ${i} weight ${weight(i)}")
          j = j - weight(i)
        }
        // 否则上层没有对应当前选了的状态，就是当前物品没选了, j保持不变
      }

      // 第1层是基于第0层的选择，如果第一层基于第0层选了，所以第0层自身做判断即可
      if (j > 0) {
        println(s"select item 0 weight ${weight(0)}")
      }
  }

}
