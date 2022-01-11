package com.zeng.dsa.linked

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2022-01-09 8:46 下午
 * @function:
 */
object Offer06 {
  def main(args: Array[String]): Unit = {

  }

  /**
   *
   * @param head
   * @return
   */
  def reversePrint(head: ListNode): Array[Int] = {
    // 可以申请一个空数组
    val ints = new Array[Int](0)
    recursiveCollectElem(head, ints)
  }

  /**
   * 递归回溯的思想
   * @param head
   * @param array 要加入当前元素前的数组状态
   * @return
   */
  def recursiveCollectElem(head: ListNode, array: Array[Int]):  Array[Int] = {
    if (head == null) {
      return array
    }
    val backArray = recursiveCollectElem(head.next, array)
    return backArray :+ head.x
  }
}
