package com.zeng.dsa.array

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2022-01-17 9:42 上午
 * @function:
 */
object Offer11 {
  def main(args: Array[String]): Unit = {
    var numbers: Array[Int] = Array(3, 4, 5, 1, 2)
    numbers = Array(2, 2, 2, 0, 1)
    numbers = Array(4, 5, 6, 7, 0, 1, 2)
    val minOne: Int = minArray(numbers)
    println(minOne)
  }

  /**
   * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    给你一个可能存在 重复 元素值的数组numbers，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。
    请返回旋转数组的最小元素。例如，数组[3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。

   * 分析: 举一些例子分析出不同的场景的处理流程，将流程落地成代码
   *
   * while s <= e: 不断二分，找m位置的元素，判断前后元素大小关系
   * 如果arr(m-1) > arr(m)  比如 3 4 0 1 2  m是2下标, return arr(m)
   * 如果arr(m) > arr(m + 1) [4,5,6,7,0,1,2]  m是3下标  return arr(m+1)
   *
   * 先不考虑重复情况
   * 1. 旋转后就是两段有序数组，我们可以使用二分查找法，一次二分，数组可以有如下四种情况
   * 2. 每次二分后，我们让m 在正确的区间内移动
   * 3. 正确区间不断缩小，何为正确区间: 拼一起的两个递增区间，只不过是缩小版
   * 所以m总会到达最小的元素位置的，关键在于我们判断一下要接着输入的区间即可
   *
   * 设输入为 1 2 3 4 5 6，二分后的下标为m,m=2
   * case 1: m 两边区间都是连续递增的.
   *  4 5 6 1 2 3 # 直接符合返回情况, m > m + 1, return arr(m + 1)
   * case 2: m左边的区间连续递增，m右边两个递增区间.
   *  3 4 5 6 1 2 # 单个的6也可以看成是个递增的区间
   * case 3: m右边的区间连续递增，m左边两个递增区间
   *  6 1 2 3 4 5
   * case 4: 直接符合返回情况, m < m -1 return arr(m)
   * 5 6 1 2 3 4
   *
   * 基于 case 2 3 需要继续二分的场景，我们接下来如何选择正确的区间?
   * 设开头、末尾下标分别为s, e
   * a. if arr(m) > arr(s) 当前区间是转移前的尾部区间(自身比较大)，我们去 [m+1, e]的区间找
   *  a 可以命中case 2
   * b. if arr(m) < arr(s) 当前区间断开过，去[s, m-1] 中找
   *
   * 有些trick:
   *  [3,3,3,1]，得扩大为arr(m) >= arr(s)，接着找[m+1, e],才能最后找到1
   *  对于重复的数据情况, 比如 1 0 1 1 1 就又不对了，这里我们得提前判断 m s e的情况
   *  if m == s == e: 我们就得整个全局找
   *
   *
   * @param numbers
   * @return
   */
  def minArray(numbers: Array[Int]): Int = {
    var s = 0
    var e = numbers.length - 1
    // 边界情况，数组没做旋转
    if (numbers(s) < numbers(e)) return numbers(s)
    while (s <= e) {
      val m = s + (e - s) / 2
      // 防止下标越界
      if (m - 1 >= 0 && numbers(m - 1) > numbers(m)) return numbers(m)
      if (m + 1 <= numbers.length - 1 && numbers(m) > numbers(m + 1)) return numbers(m + 1)
      // 需要全局找的情况
      if (numbers(s) == numbers(e) && numbers(s) == numbers(m)) {
        return iterFindMin(numbers, s, e)
      }

      if (numbers(m) >= numbers(s)) {
        s = m + 1
      } else {
        e = m - 1
      }
    }
    // DEBUGU
    -1010
  }

  /**
   * 在数组[s, e]范围内寻找最小的元素
   * @param numbers
   * @param s
   * @param e
   * @return
   */
  def iterFindMin(numbers: Array[Int], s: Int, e: Int): Int = {
    var minVal = numbers(s)
    var i = s
    for (i <- s+1 to e) {
      if (numbers(i) < minVal) minVal = numbers(i)
    }
    return minVal
  }
}
