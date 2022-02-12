package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2022-01-09 4:57 下午
 * @function:
 */
object binarySearch {

  def main(args: Array[String]): Unit = {

  }
  /**
   * 彻底搞清楚二分的过程!
   * 二分法的中点 mid移动思考:
   * 随着s, e两端的不断缩减，中点一定在[s, e]范围内，而任何时候我们都将比较情况划分为三种：
   * 1.大于
   * 2.等于
   * 3.小于
   * !! mid始终朝着正确的方向移动(每次我们都在选择新的正确的区间)
   * 区间不断缩小:
   * 1. s， e相邻，mid = s，再调整得情况2
   * 2. s == e， mid = s = e
   * !! 所以mid可以等于s 或者 e
   */

  /**
   * 在数组nums中, 找到第一个大于等于target的元素下标；
   * 突破点： 前一个元素 < target
   * !!面试记着突破点就能写出来
   *
   * @param nums 递增数组
   * @param target
   */
  def bsFirstGTE(nums: Array[Int], target: Int): Int = {
    // mid
    // 比较 nums(mid) 和 target 调整新的start end
    var start = 0
    var end = nums.length - 1

    while (start <= end) {
      // 这么写防止整型溢出
      val mid = (end - start) / 2 + start

      if (nums(mid) >= target) {
        if (mid == 0 || nums(mid - 1) < target) {
          return mid
        }
        end = mid - 1
      } else {
        start = mid + 1
      }
    }

    -1
  }

  /**
   * 查找最后一个小于等于target的元素
   * 突破点：该元素的下一个位置的元素 > target
   * @param nums
   * @param target
   * @return
   */
  def bsLastLTE(nums: Array[Int], target: Int): Int = {
    // mid
    // 比较 nums(mid) 和 target 调整新的start end
    var start = 0
    var end = nums.length - 1

    while (start <= end) {
      // 这么写防止整型溢出
      val mid = (end - start) / 2 + start

      if (nums(mid) <= target) {
        if (mid == nums.length - 1 || nums(mid + 1) > target) {
          return mid
        }
        start = mid + 1
      } else {
        end = mid - 1
      }
    }

    -1
  }
}
