package com.zeng.dsa.sort

import com.zeng.dsa.utils.Util

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2022-03-07 8:37 下午
 * @desc:
 */
object HeapSort {
  def main(args: Array[String]): Unit = {
//    val ints: Array[Int] = Array(2, 3, 1, 2, 4, 5, 2)
    val ints: Array[Int] = Array(2, 3, 1, 6, 4, 5, 0)
    sort(ints)
    println(ints.mkString("_"))
  }
  /**
   * 堆背景：
   * 大顶堆，任意节点的值 >= 子树节点值
   * 小顶堆，任意节点的值 <= 子树节点值
   * 还有一种说法是任意节点的值 >= 左右节点值，这两种说法都行
   *
   * 堆的存储: 使用数组
   * 首先我们假设raw nums从原始数组下标为1的位置开始存储，设有下标i，则i/2为父节点
   * 如果raw nums从原始数组的下标为0的位置开始存储，设有下标i,则(i-1)/2为父节点
   *
   * 堆排序分两步走，假设大顶堆：
   * 1.建堆: 从最后一层的非叶子节点开始，从后向前，做自上而下的堆化
   * 建堆完成后，堆顶即为最大值
   *
   * 2.排序：堆顶和堆尾元素交换，则数组末尾最大，相当于堆顶的删除操作，堆大小-1；
   *    接着对堆顶做自上而下的堆化，重复上述过程，直到堆中元素个数为1
   *
   *
   * 总结：最重要的是实现大顶堆的自上而下的堆化函数
   *      接着建堆也是需要堆化；排序时做删除堆顶，也需要堆化
   *
   */
  def sort(nums: Array[Int]): Unit = {
    buildHeap(nums)

    // 堆中元素个数
    var headSize = nums.length
    while (headSize > 1) {
      // 删除堆顶
      Util.swapArray(nums, 0, headSize - 1)
      headSize -= 1
      heapfyUpToBottom(nums, 0, headSize)
    }

  }

  /**
   * 建大顶堆
   * @param nums
   */
  def buildHeap(nums: Array[Int]): Unit = {
    val length: Int = nums.length
    // 堆数组从0下标开始存储， i为最后一个非叶子节点的下标
    var i = ((length - 1) - 1) / 2
    while (i >= 0) {
      heapfyUpToBottom(nums, i, length)
      i -= 1
    }
  }

  /**
   * 大顶堆
   * 对于pos位置的元素，做一次自上而下的堆化
   * @param nums
   * @param pos
   * @param heapSize 当前堆的大小
   */
  def heapfyUpToBottom(nums: Array[Int], pos: Int, heapSize: Int): Unit = {
    // 数组从0开始，父节点下标为i，则左子节点为2i+1,右子节点为2i+2
    var i = pos
    //注意这块的循环判断条件, 表达出，不满足大顶堆的父子关系时，则进入循环，太费劲了
    // while ((left < nums.length && nums(pos) < nums(left)) || (right < nums.length && nums(pos) < nums(right)))
    // 直接while true，满足大顶堆父子关系就退出！
    Breaks.breakable(
      while (true) {
        // 初始化最大值的下标
        var maxValueIndex = i
        val left: Int = 2 * i + 1
        val right: Int = 2 * i + 2
        // 寻找父节点、左子节点、右子节点，值最大的下标
        if (left < heapSize && nums(maxValueIndex) < nums(left)) maxValueIndex = left
        if (right < heapSize && nums(maxValueIndex) < nums(right)) maxValueIndex = right
        // 父节点自身就是最大的值，已经满足大顶堆关系，则退出
        if (maxValueIndex == i) {
          Breaks.break()
        }

        // 否则，交换 父节点和最大值子节点，并更新i
        Util.swapArray(nums, i, maxValueIndex)
        i = maxValueIndex
      }
    )
  }
}
