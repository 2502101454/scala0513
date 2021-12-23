package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2021-12-19 10:43 下午
 * @function:
 */
object Offer03 {
  def main(args: Array[String]): Unit = {
    //[2, 3, 1, 0, 2, 5, 3]
    var array: Array[Int] = Array(2, 3, 1, 0, 2, 5, 3)
    array = Array(1, 2, 0, 1, 4)
    array = Array(1, 2, 0, 4, 3)
    println(findRepeatNumber(array))
  }

  /**
   * n个数字，数字范围0~n-1，找出任意一个重复的数字
   *
   * 分析:
   * 思路1.排序
   * 思路2.借助map计数
   * 思路3.分析不重复情况，借助下标0~n-1，数字x应该放在下标为x的位置，一个萝卜一个坑
   *      解法：
   *        1.遍历数组：
   *          1.1 元素值x和其下标i相等，正确坑位，继续下一位；
   *          1.2 元素值x和其下标i不相等时，当前坑位不对，要找正确坑位，比对x和array[x]
   *              1.2.1 如果相等，说明坑位已被***提前***占用，有重复元素
   *              ！！！这个提前很重要，只有1.2条件存在的情况下，在找坑位的过程中，发现正确坑位被占用才是有重复元素
   *              场景举例，数字是0,1,2,3 这种排列，每个元素和下标都相等，但是并不重复
   *              1.2.2 如果不相等则坑位交换，在当前下标i的位置继续判断，即再从1.1处开始
   *
   *      总结： 废话这么多，其实就是让每个坑位有正确的数字，如果没有正确的数字那么就一直交换，
   *      直到有正确数字为止，然后接着下一个坑位的检查，期间发现坑位提前占用的情况就是重复数字了
   *
   *
   * @param nums
   * @return
   */
  def findRepeatNumber(nums: Array[Int]): Int = {
    for (i <- 0 until nums.length){
      // 比对坑位元素和当前自身下标是否相等
      while ( i != nums(i)) {
        var value = nums(i)
        var occupied_value = nums(value)
        if (occupied_value == value) return value
        else {
          // 交换坑位，正确坑位放正确的元素
          nums(i) = occupied_value
          nums(value) = value
        }
      }
    }
    0
  }
}
