package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2022-01-08 4:28 下午
 * @function:
 */
object Offer04 {
  def main(args: Array[String]): Unit = {

  }
  /**
   * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
   * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数
   *
   * 思路:
   * 从数组的右上角开始，不断比较当前位置的元素和 target的大小，从而调整查找范围
   * (剔除掉 *确定* 不存在target的范围)，继续从新范围的右上角开始，重复此过程
   *
   * 实现:
   * 1.选取右上角元素，A
   * 2. 比大小：
   *    2.1 如果A == target 则找到了
   *    2.2 如果A 大于 target，则剔除A所在的列 (这一列都确定比Target 大)
   *    2.3 如果A 小于 target，则剔除A所在的行 (这一行都确定比Target 小)
   *
   * 3.基于新的范围，继续 1， 2
   * */
  def findNumberIn2DArray(matrix: Array[Array[Int]], target: Int): Boolean = {
    if (matrix.length == 0) return false

    // 初始化右上角位置
    var j = matrix(0).length - 1
    var i = 0

    while (i <= matrix.length - 1 && j >= 0) {
      val value = matrix(i)(j)
      if (value == target) return true
      if (value < target) {
        // 确定这一行无用，剔除
        i += 1
      } else {
        // 确定这一列无用，剔除
        j -= 1
      }
    }

    false
  }

  /**
   * 优化一下: 利用二分查找，从第一行开始，找到最后一个小于等于target的下标 p，j直接挪到这个p位置
   *
   * p 后面的都是比 target大的元素，所以这些列都可以剔除
   * @param matrix
   * @param target
   * @return
   */
  def findNumberIn2DArrayV2(matrix: Array[Array[Int]], target: Int): Boolean = {
    if (matrix.length == 0) return false

    // 初始化右上角位置
    var j = bsLastLTE(matrix(0), target)
    var i = 0
    while (i <= matrix.length - 1 && j >= 0) {
      val value = matrix(i)(j)
      if (value == target) return true
      if (value < target) {
        // 确定这一行无用，剔除
        i += 1
      } else {
        // 确定这一列无用，剔除
        j -= 1
      }
    }

    false
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
