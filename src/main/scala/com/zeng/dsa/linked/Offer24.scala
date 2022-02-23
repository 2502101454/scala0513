package com.zeng.dsa.linked

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2022-02-18 1:26 下午
 * @desc:
 */
object Offer24 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 链表反转:
   * 关注p--中间这个节点(pre, p, pNext)，要做的事情，画图举例帮助分析
   * @param head
   * @return
   */
  def reverseList(head: ListNode): ListNode = {
    var pre: ListNode = null
    var p = head
    while (p != null) {
      val pNext = p.next
      p.next = pre
      pre = p
      p = pNext
    }
    pre
  }
}
