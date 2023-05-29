package com.zeng.dsa.sort

/**
 *
 * @author zengwang
 * @create 2022-03-06 9:58 上午
 * @desc:
 */
object InsertSort {
  def main(args: Array[String]): Unit = {
//    val ints: Array[Int] = Array(1, 3, 2, 6, 4, 1, 4)
    val ints: Array[Int] = Array(4, 1, 4)
    sort(ints)
    println(ints.mkString("-"))
  }

  /**
   * 插入排序:
   * 把数组的第一个元素当做是一个有序的数组A，然后后面的元素在A中寻找合适的位置进行插入，
   * 这样始终维护了一个有序的数组
   *
   * 举例，对于1 3 4 2，我们把1 3 4当做有序数组A，2则要在A中找到第一个大于某个元素的位置，
   * 在找的同时，挪动数组
   * @param nums
   */
  def sort(nums: Array[Int]): Unit = {
    // 从数组的下标1开始，一直到数组的最后元素，都是要插入的对象
    for (i <- 1 until(nums.length)) {
      // 对于每个要插入的对象，寻找插入位置
      val tmp = nums(i)
      // A数组的末尾位置，从这里开始查找第一个大于A中元素的时机
      var j = i - 1
      while (j >= 0 && tmp < nums(j)) {
        nums(j + 1) = nums(j)
        j -= 1
      }

      // tmp >= nums(j)则退出，j + 1则是放入tmp的位置
      // 边界条件最后在考虑
      nums(j + 1) = tmp
    }
  }
}
