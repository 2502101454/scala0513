package com.zeng.dsa.sort
import scala.util.Random
/**
 *
 * @author zengwang
 * @create 2021-12-28 10:48 下午
 * @function:
 */
object quickSort {
  def main(args: Array[String]): Unit = {
    val array: Array[Int] = Array(1, 3, 2, 1, 5, 6, 7, 4)
    qs(array, 0, array.length - 1)
    println(array.mkString("-"))
  }

  def swap(array: Array[Int], i: Int, j: Int): Unit = {
    val tmp = array(i)
    array(i) = array(j)
    array(j) = tmp
  }
  /**
   * 快速排序:
   * 先分区在递归左右部分
   * 递归终止条件: 左边界 >= 右边界
   * @param array
   * @param s
   * @param e
   */
  def qs(array: Array[Int], s: Int, e: Int): Unit = {
    if (s >= e) {
      return
    }
//    val p: Int = partition(array, s, e)
    // 返回Random.nextInt(n) 返回[0, n-1]
    val pivotIndex = s + Random.nextInt(e - s + 1)
    val p: Int = partitionOptimized(array, s, e, pivotIndex)
    qs(array, s, p - 1)
    qs(array, p + 1, e)
  }

  /**
   * 分区函数：选取最后一个元素作为pivot，利用选择排序的思想
   * @param array
   * @param s
   * @param e
   * @return 返回pivot调整后的'位置'
   */
  def partition(array: Array[Int], s: Int, e: Int): Int = {
    var (i, j) = (s, s)
    val pivot = array(e)
    for (j <- s to e) {
      if (array(j) < pivot) {
        swap(array, i, j)
        i += 1
      }
    }
//    var tmp = array(i)
//    array(i) = pivot
//    array(e) = tmp
    swap(array, i, e)
    i
  }

  /**
   * 快排的分区函数的优化
   * 背景:
   *  原有的分区函数总是从数组的末尾元素取pivot，带来弊端：
   *  如果末尾元素自身就是最大的元素时(或者比较大)，分区就极度不平衡，算法时间复杂度甚至下降为O(n^2)
   *  可参见之前在solid_fundation中的排序分析实验
   *
   * 解决: 只要让分区两边更均衡就行，两种方式(还有更多)
   *   1.随机取pivot, pivot在数组中随机选一个元素
   *   2.三数取中，pivot在首、中、尾三个元素中，挑一个元素值是中间的元素
   *
   * 实现: 1，2不论哪种方式，当pivot选择不在末尾的时候，都要保证pivot的初始位置放在末尾(自己预先swap一下)
   *
   * 原因: 举例，如果pivot选在中间位置，pivot_index后面有很多小于pivot的元素，那么在遍历的数组的过程中，
   * i++就可能达到pivot_index的位置，从而交换pivot)_index位置元素为小于，再顺着程序往下走，你就发现这个数组
   * 分区结束后变成了小-大-小-大的排列，
   * 原因部分自行感兴趣推导即可，先记住必须保证pivot初始位置在末尾
   *
   * @param array
   * @param s
   * @param e
   * @param pivotIndex 随机选择的pivot下标
   * @return
   */
  def partitionOptimized(array: Array[Int], s: Int, e: Int, pivotIndex: Int): Int = {
    // pivotIndex可能不在末尾，这里提前和末尾交换一下
    swap(array, pivotIndex, e)
    var (i, j) = (s, s)
    val pivot = array(e)
    for (j <- s to e) {
      if (array(j) < pivot) {
        swap(array, i, j)
        i += 1
      }
    }
    //    var tmp = array(i)
    //    array(i) = pivot
    //    array(e) = tmp
    swap(array, i, e)
    i
  }

}
