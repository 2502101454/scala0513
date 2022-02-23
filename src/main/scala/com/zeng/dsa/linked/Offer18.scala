package com.zeng.dsa.linked

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2022-02-17 1:15 下午
 * @desc:
 */
object Offer18 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 给定链表的头指针，和一个值`val`，请删除值为`val`的节点，并返回头指针
   * 题目假设链表中节点值互不相同
   *
   * 分析： 使用链表哨兵节点简化场景判断
   * @param head
   * @param `val`
   * @return
   */
  def deleteNode(head: ListNode, `val`: Int): ListNode = {
    if (head == null) return head

    val sentinel: ListNode = new ListNode(_next = head)
    var p: ListNode = sentinel

    while (p.next != null && p.next.x !=  `val`) {
      p = p.next
    }
    // 这个写法得保证上面while循环一定找到了删除的节点
    p.next = p.next.next
    sentinel.next
  }
}
