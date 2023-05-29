package com.zeng.dsa.sort

import com.zeng.dsa.utils.Util

/**
 *
 * @author zengwang
 * @create 2022-03-06 9:23 上午
 * @desc:
 */
object BubbleSort {
  def main(args: Array[String]): Unit = {
    val ints: Array[Int] = Array(2, 1, 3, 5, 4)
//    val ints: Array[Int] = Array(2, 1, 3)
    sort(ints)
    println(ints.mkString("_"))
  }

  /**
   * 算法思想：
   * n个元素，比较n-1轮，每一轮做两两元素相互比较，符合则交换，这样最大的元素冒泡到末尾
   * @param nums
   */
  def sort(nums: Array[Int]): Unit = {
    // 正序排
    val length: Int = nums.length
    for (i <- 1 until length) { // 比较多少轮, n-1轮
      for (j <- 0 until(length - i)) { // 每一轮内的比较j 和j + 1
        if (nums(j) > nums(j + 1)) Util.swapArray(nums, j, j + 1)
      }
    }
  }
}
