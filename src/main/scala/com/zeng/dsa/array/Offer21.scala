package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2022-02-17 1:47 下午
 * @desc:
 */
object Offer21 {
  def main(args: Array[String]): Unit = {
    exchange(Array(1,2,3,4)).foreach(println)
  }

  /**
   * 来自快排的分区函数的思想--本质是基于选择排序的
   * @param nums
   * @return
   */
  def exchange(nums: Array[Int]): Array[Int] = {
    // i始终维护为计数的下标
    var i = 0
    for (j <- 0 to nums.length - 1) {
      // j从0开始遍历数组，如果遇到奇数，则和i做交换，让nums(i)始终存奇数
      if (nums(j) % 2 != 0) {
        val tmp = nums(i)
        nums(i) = nums(j)
        nums(j) = tmp
        i += 1
      }
    }
    nums
  }

}
