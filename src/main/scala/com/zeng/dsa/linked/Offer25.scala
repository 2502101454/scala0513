package com.zeng.dsa.linked

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2022-02-18 1:40 下午
 * @desc:
 */
object Offer25 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 合并两个升序链表
   *
   * 使用哨兵节点解决头结点初始化(而不知道选哪个头结点的问题)
   * @param l1
   * @param l2
   * @return
   */
  def mergeTwoLists(l1: ListNode, l2: ListNode): ListNode = {
    val sentinel: ListNode = new ListNode(_next = null)
    var p = sentinel
    var p1 = l1
    var p2 = l2
    while (p1 != null && p2 != null) {
      // 每一轮比较选择最小的值 插到新链表中
      var value = p1.x
      if (p1.x <= p2.x) {
        p1 = p1.next
      } else {
        value = p2.x
        p2 = p2.next
      }
      val node = new ListNode(value)
      p.next = node
      p = node
    }

    /**
     * 优化，这里也可以不再继续创建新节点了，直接把剩余的L1、L2给接上去!
     */
    if (p1 != null) p.next = p1
    if (p2 != null) p.next = p2
    /*while (p1 != null) {
      val node = new ListNode(p1.x)
      p.next = node
      p = node
      p1 = p1.next
    }

    while (p2 != null) {
      val node = new ListNode(p2.x)
      p.next = node
      p = node
      p2 = p2.next
    }
   */
    sentinel.next
  }
}
