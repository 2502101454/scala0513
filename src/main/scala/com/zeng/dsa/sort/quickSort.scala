package com.zeng.dsa.sort

import com.zeng.dsa.utils.Util

/**
 *
 * @author zengwang
 * @create 2022-03-06 12:24 下午
 * @desc:
 */
object quickSort {
  def main(args: Array[String]): Unit = {
    val ints: Array[Int] = Array(3, 4, 1, 2, 1)
    quickSort(ints, 0, ints.length - 1)
    println(ints.mkString("_"))

  }

  /**
   * 快速排序：
   * 先排(partition排完后，左边小，右边大)，再分
   * 而归并排序，是先分再排
   * @param nums
   * @param start
   * @param end
   */
  def quickSort(nums: Array[Int], start: Int, end: Int): Unit = {
    if (start >= end) return
    val m: Int = partitioned(nums, start, end)
    quickSort(nums, start, m - 1)
    quickSort(nums, m + 1, end)
  }

  /**
   * 对[start, end]区间，选择末尾pivot，分区后，返回分区的`中间`下标
   * tips: 也可以随机选一个pivot，然后和末尾元素做交换，这样把Pivot也就放到末尾了
   * @param nums
   * @param start
   * @param end
   * @return
   */
  def partitioned(nums: Array[Int], start: Int, end: Int): Int = {
    val pivot: Int = nums(end)
    // 使用选择排序的思想，i始终维护小于 pivot的下标
    var i = start
    for (j <- start to end) {
      if (nums(j) < pivot) {
        Util.swapArray(nums, i, j)
        i += 1
      }
    }

    // 最后！把pivot调到`中间`
    Util.swapArray(nums, i, end)
    i
  }
}
