package com.zeng.dsa.linked

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2021-12-22 11:02 下午
 * @function:
 */
object Leet206 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 链表反转
   *
   * 分析:
   * 1.在反转Node.next的时候，防止next链路断掉，提前取一个游标存着
   * 2.注意第一个节点也要反转，next指向null
   *
   * 关注cur--中间这个节点(pre, cur, nextTmp)，要做的事情，画图举例帮助分析
   * @param head
   * @return
   */
  def reverseList(head: ListNode): ListNode = {
    var pre: ListNode = null
    var cur = head

    while (cur != null) {
      // 反转之前，先缓存下一个链路节点
      val nextTmp = cur.next
      // 开始反转
      cur.next = pre
      pre = cur
      cur = nextTmp
    }

    pre
  }

  /**
   * 递归大法，先DFS的思想走到尾节点，然后回溯 转向
   * @param listNode
   * @return
   */
  def reverseList2(listNode: ListNode): ListNode = {
    if (listNode == null) {
      return listNode
    }
    val node: ListNode = recursive(listNode)
    node
  }

  def recursive(head: ListNode): ListNode = {
    if (head.next == null) {
      // 返回末尾节点是反转后的头节点
      return head
    }
    var reversedHead = recursive(head.next)
    head.next.next = head
    head.next = null
    reversedHead
  }
}
