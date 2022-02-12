package com.zeng.dsa.array

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2022-01-22 3:01 下午
 * @desc:
 */
object Offer13 {
  def main(args: Array[String]): Unit = {
    println(movingCount(2, 3, 1))
  }

  /**
   * 机器人的运动范围
   * 分析: 回溯法
   * 1.分阶段： 每走一格子就是一个阶段
   * 2.代码承上启下
   *
   * 对于visited过的格子，曾经一定基于此格子做过扩展，
   * 现在再走到该格子了，再做扩展已经没必要了，所以返回
   * @param m
   * @param n
   * @param k
   * @return
   */
  def movingCount(m: Int, n: Int, k: Int): Int = {
    val visited: mutable.Map[String, Boolean] = mutable.Map[String, Boolean]()
    recursiveMoving(0, 0, m, n, k, visited)
    visited.size
  }

  /**
   * (i, j) 表示当前运动的位置，初始调用使用(0,0)
   * (m, n) 代表二维数组，这里不用创建二维数组
   * @param i
   * @param j
   * @param m
   * @param n
   * @param k 格子下标范围限制
   * @param visited 曾经扩展过的格子
   */
  def recursiveMoving(i: Int, j: Int, m: Int, n: Int, k: Int,
                      visited: mutable.Map[String, Boolean]): Unit = {
    // 格子曾经扩展过，则返回
    if (visited.contains(s"${i}_${j}")) return
    // 数组下标越界
    if (i < 0 || i >= m) return
    if (j < 0 || j >= n) return
    // 当前格子坐标i, j 位数之和不能超过k
    if (digitalSum(i) + digitalSum(j) > k) return

    // 符合要求的格子，加入缓存，并进行四个方向的扩展
    visited.put(s"${i}_${j}", true)
    recursiveMoving(i, j + 1, m, n, k, visited)
    recursiveMoving(i, j - 1, m, n, k, visited)
    recursiveMoving(i + 1, j, m, n, k, visited)
    recursiveMoving(i - 1, j, m, n, k, visited)

  }

  /**
   * 可以优化成n / 10 取的是十位(当n是100的时候，也是10)， n % 10取的是个位
   * @param n
   * @return
   */
  def digitalSum(n: Int): Int = {
    var sum: Int = 0
    val digital: Array[Char] = n.toString.toCharArray
    for (elem <- digital) {
      // 注意，字符0的ASCII值是48
      sum += elem.toInt - 48
    }
    sum
  }
}
