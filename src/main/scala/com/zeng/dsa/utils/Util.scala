package com.zeng.dsa.utils

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @author zengwang
 * @create 2022-03-06 9:33 上午
 * @desc:
 */
object Util {
  def main(args: Array[String]): Unit = {
    var nums = Array(1,2,3)
    swapArray(nums, 2, 0)
    println(nums.mkString("—"))
  }
  def swapArray(nums: Array[Int], s: Int, e: Int): Unit = {
    val tmp = nums(s)
    nums(s) = nums(e)
    nums(e) = tmp
  }
}
