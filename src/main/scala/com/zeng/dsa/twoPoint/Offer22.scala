package com.zeng.dsa.twoPoint

import com.zeng.dsa.greedy.ListNode

/**
 *
 * @author zengwang
 * @create 2022-02-18 12:46 下午
 * @desc:
 */
object Offer22 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 找到链表倒数第k个节点，计数从1开始，尾结点是倒数第一个节点
   *
   * 双指针解法：设有指针a, b，让a，b两指针之间的距离始终是k-1，则当b达到尾部时，a即为倒数第k个节点
   *
   * @param head
   * @param k
   * @return
   */
  def getKthFromEnd(head: ListNode, k: Int): ListNode = {
    // 初始a, b指针
    var pa = head
    var pb = head
    var distance = k - 1
    while (distance > 0) {
      pb = pb.next
      distance -= 1
    }

    while (pb.next != null) {
      pb = pb.next
      pa = pa.next
    }
    pa
  }

}
