package com.zeng.dsa.topN_Or_Kth

import scala.util.Random
import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-12-29 11:50 上午
 * @function:
 */
object leet215_V2 {
  def main(args: Array[String]): Unit = {
    var nums: Array[Int] = Array(1, 2, 3, 5, 2, 3, 4, 7)
    nums = Array(1,7)
    nums = Array(1)
    val kth = findKthLargest(nums, 1)
    println(kth)
  }

  /**
   * 查找数组nums中的第k大元素
   * 堆排序的TopN问题解法，堆排序是两步: 1.建堆；2.排序
   * 应用到这里，我们只要建堆就好了
   *
   * 思路： TopN问题和Kth问题都一样
   * 1.这里我们从数组中取前k个值，维护一个大小为k的小顶堆
   * 2.然后从下标k开始，取每个元素和堆顶元素比对
   *  2.1 如果比堆顶小(堆顶已经是最小的了) 就舍弃
   *  2.2 如果比堆顶大，就删除堆顶(覆盖堆顶) 然后做自上而下的堆化
   * 3.结果，堆顶元素就是我们要求的第k大
   * @param nums
   * @param k
   * @return
   */
  def findKthLargest(nums: Array[Int], k: Int): Int = {
    buildHeap(nums, k)
    // 对从数组下标k开始，逐个和堆顶比较
    for (i <- k until nums.length) {
      if (nums(i) > nums(0)) {
        nums(0) = nums(i)
        heapfiy(nums, 0, k - 1)
      }
    }
    nums(0)
  }

  def swap(nums: Array[Int], i: Int, j: Int): Unit = {
    val tmp = nums(i)
    nums(i) = nums(j)
    nums(j) = tmp
  }

  /**
   * 建立大小为K的小顶堆：
   * 对下标从0~k-1的元素建堆，记下标为i，堆顶元存于i=0，左节点l=2*i + 1 ,右节点r=2*i + 2
   * 对于下标i，其父节点下标为(i - 1)/2
   *
   * 1.当前堆的最后一个节点下标为k-1, 其对应的最后一个父节点是k/2 - 1，从这里开始做自上而下的堆化
   * 2.对[0, k/2 - 1] 父节点都要做堆化，方向是从后向前
   * @param nums
   * @param k
   */
  def buildHeap(nums: Array[Int], k: Int): Unit = {
    for (i <- (0 to k/2 - 1).reverse) {
      heapfiy(nums, i, k-1)
    }
  }

  /**
   * 对下标i的节点，做自上而下的堆化，小顶堆
   * @param nums
   * @param i
   * @param end 是堆的最后一个元素边界下标
   */
  def heapfiy(nums: Array[Int], i: Int, end: Int): Unit = {
    // 初始化父节点、子节点下标位置
    var p = i

    // 挨个判断，父节点和子节点们的大小关系，然后选择值最小的节点和父节点交换，这样的逻辑比较冗余
    // 换个思路，额外声明一个变量，我们找这3个节点中值最小的，变量记录其下标：
    //  1. 值最小的是父节点，则不用交换
    //  2. 值最小的是子节点，则父节点和子节点交换
    //!!!类似于之前for 循环中找数组最小的元素，不断的更新最小的下标，这里只是把for循环的语义改成了两次if判断
    // 要注重你代码表达的目的，而不是代码展示的形式
    Breaks.breakable(
      while (true) {
        val left = 2 * p + 1
        val right = 2 * p + 2

        var minIndex = p
        if (left <= end && nums(minIndex) > nums(left)) minIndex = left
        if (right <= end && nums(minIndex) > nums(right)) minIndex = right

        if (minIndex == p) Breaks.break()
        swap(nums, minIndex, p)
        p = minIndex
      }
    )
  }

}
