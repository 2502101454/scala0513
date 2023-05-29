package com.zeng.dsa.sort

import com.zeng.dsa.utils.Util

/**
 *
 * @author zengwang
 * @create 2022-03-06 10:49 上午
 * @desc:
 */
object SelectSort {
  def main(args: Array[String]): Unit = {
//    val ints: Array[Int] = Array(1, 3, 2, 5, 4)
    val ints: Array[Int] = Array(3, 2, 1, 4, 4)
    sort(ints)
    println(ints.mkString("_"))
  }

  /**
   * 选择排序：
   *
   * 1.遍历一遍数组，选中最大的值，然后和数组末尾元素交换
   * 2.在遍历一遍数组(不包含末尾)，选最大值，然后再和末尾次位交换，依次类推
   *
   * n个元素，只选n-1轮，最后一轮是从两个元素中选的
   * @param nums
   */
  def sort(nums: Array[Int]): Unit = {
    val length: Int = nums.length
    for (i <- 1 until length) { // 进行n-1轮的选择
      var curMaxIndex = 0
      for (j <- 0 to length - i) { // 每一轮的选择范围
        if (nums(curMaxIndex) < nums(j)) curMaxIndex = j
      }
      Util.swapArray(nums, curMaxIndex, length - i)
    }
  }
}
