package com.zeng.dsa.greedy

/**
 *
 * @author zengwang
 * @create 2021-12-03 9:54 上午
 * @function:
 */
class LeetCode2 {

}

/***
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，
 * 并且每个节点只能存储 一位 数字。请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0开头。
 *
 * 两个变量两个游标，分别从两个链表开始遍历，取到值相加，同时更新进位，把个位值存入结果链表中，继续遍历
 * 最后注意额外的进位需要补入结果链表中即可，这里每次补0额外创建节点实属浪费，可以优化一下
 * @param _x
 * @param _next
 */
class ListNode(_x: Int = 0, _next: ListNode = null) {
    var next: ListNode = _next
    var x: Int = _x
 }
object Solution {
  val sentinel = new ListNode()
  var p = sentinel
  def addTwoNumbers(l1: ListNode, l2: ListNode): ListNode = {
    // 表示进位
    var flag = 0
    var _l1 = l1
    var _l2 = l2
    // 函数参数为不可变参数...
    while (_l1 != null || _l2 != null) {
      // 判断补0 l1 or l2
      if (_l1 == null) {
        _l1 = new ListNode(0, null)
      }
      if (_l2 == null) {
        _l2 = new ListNode(0, null)
      }

      var value = _l1.x + _l2.x + flag
      // 判断加后的值是否有进位
      if (value >= 10) {
        flag = 1
        value -= 10
      } else {
        // 无进位则需要 把进位重置为0
        flag = 0
      }

      // 连接结果
      val newNode = new ListNode(value, null)
      p.next = newNode
      p = newNode

      _l1 = _l1.next
      _l2 = _l2.next
    }
    // 上面处理完后，l1, l2都有效位置完全遍历干净了
    // 再判断进位，如果还有进位，说明l1,l2最后两者相加有进位，则这里再把进位 1 加到结果里
    if (flag > 0) {
      val newNode = new ListNode(flag, null)
      p.next = newNode
      p = newNode
    }
    sentinel.next
  }
}