package com.zeng.dsa.heap

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-11-17 1:13 下午
 * @function:
 */
object heap_sort {
  def main(args: Array[String]): Unit = {
    /**
     * 无需定义完全二叉树，使用完全二叉树的数组存储结构，
     * 设根节点存储在下标i = 0 , 则2 * 1 + 1, 2 * i +2 分别是左右子节点的位置
     * 堆排序:
     *  1.建堆， 构建大顶堆
     *  2.排序
     */

//    var array: Array[Int] = Array[Int](5, 1, 3, 4, 6, 7, 2)
//    var array: Array[Int] = Array[Int](5, 2, 3, 1, 1, 7, 2, 0, 9)
//    var array: Array[Int] = Array[Int](5, 0)
    var array: Array[Int] = Array[Int]( 0)
    // 建堆，设堆元素个数为n，从第一个非叶子节点开始，自上而下做堆化，
    // 第一个非叶子节点的位置是 (last_index - 1)/ 2， 因为是从0开始存储元素的
    for (i <- 0 to (array.length - 2) / 2 reverse ) {
      heapfiy(array, i, array.length - 1)
    }
    
    // 排序: 设堆中有n个节点，取堆顶元素和数组尾部元素交换，即尾部最大了，接着再对n-1个元素反复构建堆，
    // 取次大，再放到n-1的位置，如此反复即可，直到堆中只剩下一个元素
    sort(array)
    println(array.mkString(","))
  }

  /**
   * 交换数组元素位置
   * @param array
   * @param i
   * @param j
   */
  def swapArray(array: Array[Int], i: Int, j: Int): Unit = {
    val tmp = array(i)
    array(i) = array(j)
    array(j) = tmp
  }
  /**
   * 自上而下的进行堆化, 大顶堆
   * @param array 要参与堆化的数组。
   * @param startIndex 是要开始堆化的元素位置
   * @param limitIndex 堆化的范围，不允许超过这个下标，后面排序的时候要指定的
   */
  def heapfiy(array: Array[Int], startIndex: Int, limitIndex: Int): Unit = {
    // Scala中函数参数是常量，不允许赋值?
    var _startIndex = startIndex
    Breaks.breakable(
      while(true) {
        var maxPoi = _startIndex
        // 取左右子节点进行比较
        val left = _startIndex * 2 + 1
        // 防止数组下标越界
//        if (left >= array.length) Breaks.break()
        val right = _startIndex * 2 + 2
        if (left <= limitIndex && array(maxPoi) < array(left)) maxPoi = left
        if (right <= limitIndex && array(maxPoi) < array(right)) maxPoi = right
        if (maxPoi == _startIndex) Breaks.break()
        swapArray(array, maxPoi, _startIndex)
        _startIndex = maxPoi
      }
    )

  }
  
  def sort(array: Array[Int]): Unit = {
    // n 代表元素的个数
    var n = array.length
    while (n > 1) {
      swapArray(array, 0, n - 1)
      n -= 1
      // 下面的n - 1这里要转成下标的，所以n就得减去1
      heapfiy(array, 0, n - 1)
      
    }
  }
}
