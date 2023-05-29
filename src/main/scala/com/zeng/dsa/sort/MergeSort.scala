package com.zeng.dsa.sort

import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2022-03-07 11:41 上午
 * @desc:
 */
object MergeSort {
  def main(args: Array[String]): Unit = {
    val ints: Array[Int] = Array(2, 1, 4, 3, 4, 5)
//    val ints: Array[Int] = Array(2, 1)
    sort(ints, 0, ints.length - 1)
    println(ints.mkString("_"))
  }

  /**
   * 归并排序:
   * 1.先分，对数组进行不断的分两段，直到最后只有一个元素，认为是有序数组
   * 2.再排，对左右两端进行有序数组的合并，然后刷新旧数组的值，依次回溯即可完成整体排序
   *
   * 递推公式:
   * merge_sort(nums, begin, end) = merge_sort(nums, begin, m) + merge_sort(nums, m+1, end) + merge(begin,m, m+1, end)
   * nums的归并排序 = 左半部分的归并排序 + 右半部分的归并排序 + 对左右两部分的有序数组合并
   *
   * 注意分段一定得是 [begin, middle] [middle + 1, end]
   * 因为你要保证确实是把数组分开了，如果数组只有两个元素，此时middle就是 0，按照上述公式则分为 [0, 0]，[1, 1]
   *
   * 但是如果写成[begin, m-1], [m, end] 则为[0, -1], [0, 1]，这样数组没有被分成有效的两部分，递归会死循环
   * @param nums
   * @param begin
   * @param end
   */
  def sort(nums: Array[Int], begin: Int, end: Int): Unit = {
    if (begin >= end) return

    val middle: Int = (end - begin) / 2 + begin
    // 对左右两部分进行分
    // !!!! 注意这里 是begin~middle
    sort(nums, begin, middle)
    sort(nums, middle + 1, end)

    // 对左右两部分数组进行合并
    mergeSortedArray(nums, begin, middle, middle + 1, end)
  }

  /**
   * 合并有序数组:
   * l1~e1
   * l2-e2
   * @param nums
   * @param l1
   * @param e1
   * @param l2
   * @param e2
   */
  def mergeSortedArray(nums: Array[Int], l1: Int, e1: Int, l2: Int, e2: Int): Unit = {
    val listBuffer: ListBuffer[Int] = ListBuffer[Int]()
    var (lb, le, rb, re) = (l1, e1, l2, e2)

    while (lb <= le && rb <= re) {
      if (nums(lb) < nums(rb)) {
        listBuffer.append(nums(lb))
        lb += 1
      } else {
        listBuffer.append(nums(rb))
        rb += 1
      }
    }

    for (i <- lb to le) {
      listBuffer.append(nums(i))
    }

    for (j <- rb to re) {
      listBuffer.append(nums(j))
    }

    // 覆盖原数组
    for (i <- 0 until listBuffer.length) {
      nums(l1 + i)  = listBuffer(i)
    }
  }
}
