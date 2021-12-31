package com.zeng.dsa.topN_Or_Kth
import scala.util.Random
/**
 *
 * @author zengwang
 * @create 2021-12-29 11:50 上午
 * @function:
 */
object leet215 {
  def main(args: Array[String]): Unit = {
    val nums: Array[Int] = Array(1, 2, 3, 5, 2, 3, 4, 7)
    val kth = findKthLargest(nums, 3)
    println(kth)
  }

  /**
   * 查找数组nums中的第k大元素
   *
   * 思路： 利用快排的分区函数思想，从而得知左边都是大于pivot的，右边都是小于pivot的，
   * 这样pivot的下标是k-1的时候就是第k大了
   *
   * TODO 堆排序的TopN问题解法?
   *
   * 注意：使用优化后的随机pivot下标选择，一般会更好点，详情参看sort中的quickSort
   * @param nums
   * @param k
   * @return
   */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    find(nums, 0, nums.length - 1, k)
  }

  def swap(nums: Array[Int], i: Int, j: Int): Unit = {
    val tmp = nums(i)
    nums(i) = nums(j)
    nums(j) = tmp
  }

  /**
   *  分区函数，分区结果为 左大 pivot 右小
   * @param nums
   * @param s
   * @param e
   * @param pivotIndex
   * @return pivot调整后的位置
   */
  def partition(nums: Array[Int], s: Int, e: Int, pivotIndex: Int): Int = {
    // pivotIndex位置是随机出来的，可能不在末尾
    // pivotIndex为啥必须放在末尾？面试要是问，就一句话(感兴趣就去参考quickSort)：
    // 如果不放在末尾，下面的分区过程结束后，pivotIndex位置可能不是一个中间值，这样就不是一个正确的分区结果了

    // 预处理，保证pivotIndex还在末尾
    swap(nums, pivotIndex, e)
    var pivot = nums(e)
    var (i, j) = (s, s)
    for (j <- s to e) {
      if (nums(j) > pivot) {
        swap(nums, i, j)
        i += 1
      }
    }

    swap(nums, i, e)
    i
  }

  def find(nums: Array[Int], s: Int, e: Int, k: Int): Int = {
    // Random.nextInt
    val randomIndex = s + Random.nextInt(e - s + 1)
    val p: Int = partition(nums, s, e, randomIndex)
    // 函数至简原则，这里不用写return，if else 代码块自身的返回值就是函数返回值
    if (p == k - 1) {
      nums(p)
    } else if (p > k - 1) {
      // p在k - 1的后面，则递归在左区间进行查找
      find(nums, s, p - 1, k)
    } else {
      find(nums, p + 1, e, k)
    }
  }
}
