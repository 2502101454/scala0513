package com.zeng.dsa.array

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-12-12 9:10 下午
 * @function:
 */
object Leet4 {
  def main(args: Array[String]): Unit = {
    var a = Array(1, 2)
    var b = Array(3, 4)
    println(findMedianSortedArrays(a, b))
  }

  /**
   * 要求两个正序数组的中位数，要求算法时间复杂度为O(log(m + n))
   *
   * 分析：看来下官方题解，是把两个数组当成一个整体，求一个分割线，满足两个性质：
   * 1.两半元素个数的性质
   * 2.左边元素都小于等于右边元素
   * 反正是用二分查找搞，看不明白，先拉到
   *
   * 只能简单求解：
   * 使用合并两个有序数组的方式，之后再求中位数。
   * 分析：
   *  1.其实合并完成后，中位数下标之后的元素也没用，所以我们只需要合并到中位数的位置即可
   *  2.我们并不需要new新的数组，只需要记录当前元素即可。两个比变量就够
   *    当两个数组元素个数是偶数时，中位数是两个
   *    xxx是奇数的时候，中位数是1个
   *
   *  总结: 使用模拟合并的操作，只合并一半，即到中位数的位置
   *
   *  所以最后的时间复杂度是O(m + n)，空间复杂度是O(1)
   * @param nums1
   * @param nums2
   * @return
   */
  def findMedianSortedArrays(nums1: Array[Int], nums2: Array[Int]): Double = {
    // 自己别死记了，自己给两组输入观察下，元素个数和中位数下标的关系就知道了
    // 奇数的话，中位数下标就是mid这个位置了；偶数则有两个, mid 和 mid - 1
    // 所以我们可以合并到mid这个位置
    val mid = (nums1.length + nums2.length) / 2
    // 初始化两个数组的游标、两个变量模拟数组合并中的最新的两个元素值
    // resultIndex 是模拟合并结果的数组下标, 每次合并后产出一个元素，所以初始为-1，代表没有元素
    var (l1, l2, pre, cur, resultIndex) = (0, 0, 0, 0, -1)
    // 合并到中位数的位置，注意跳出时候刚好resultIndex == mid 代表合并到位置了
    while (l1 < nums1.length && l2 < nums2.length && resultIndex < mid) {
      pre = cur
      if (nums1(l1) < nums2(l2)) {
        cur = nums1(l1)
        l1 += 1
      } else {
        cur = nums2(l2)
        l2 += 1
      }
      resultIndex += 1
    }

    // 处理边界情况，同理
    while (l1 < nums1.length && resultIndex < mid) {
      pre = cur
      cur = nums1(l1)
      l1 += 1
      resultIndex += 1
    }
    while (l2 < nums2.length && resultIndex < mid) {
      pre = cur
      cur = nums2(l2)
      l2 += 1
      resultIndex += 1
    }

    if ((nums1.length + nums2.length) % 2 == 0) {
      (pre + cur).toDouble / 2
    } else {
      cur.toDouble
    }

  }
}
