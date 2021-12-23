package com.zeng.dsa.linked

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-12-01 2:45 下午
 * @function:
 */
class LRUCache(_capacity: Int) {
  // _capacity是局部变量，也可以在实例方法中进行访问
  var head: Node = null
  // 使用哨兵节点简化代码, 哨兵节点一般不给值的。。这里不好改先给-1了
  val sentinel: Node = new Node(-1, -1,next = head)
  // 当前容量为0
  var currentCap = 0

  def get(key: Int): Int = {
    var p = sentinel
    while (p.next != null && p.next.key != key) {
      p = p.next
    }

    var pre = p
    p = p.next
    // 找不到就返回-1
    if (p == null) {
      return -1
    }

    // 找到了，p就是，不仅要返回值
    // 还要调整位置: 删除该节点，重新创建新节点插入链表头
    pre.next = pre.next.next
    var newNode = new Node(p.key, p.value, next = null)
    newNode.next = sentinel.next
    sentinel.next = newNode

    p.value
  }

  def put(key: Int, value: Int) {
    val newNode =  new Node(key, value, null)
    val res: (Boolean, Node, Node) = findK(key)
    val flag = res._1
    var prep = res._2
    var p = res._3

    // 找到了key，调位置: 删除原位置节点，插入新节点到链表头
    if (flag) {
      prep.next = prep.next.next

    }
    else {
      // 没找到key，则要做插入，得判断缓存是否已满
      // 1.缓存没满，则直接插入新节点到头部
      if (currentCap < _capacity) {
        currentCap += 1
      }
      // 2.缓存满了，则删除链表尾部元素，再插入新节点到头部
      else {
        var preDelNode = getTailPre()
        // 虽然结合上面的条件，缓存已满，我们的链表不会出现为空的情况，不过这里还是额外判断一下
        if (preDelNode.next != null) {
          preDelNode.next = preDelNode.next.next
        }
      }
    }

    newNode.next = sentinel.next
    sentinel.next = newNode
  }

  /**
   * 获取链表的末尾节点的前驱指针 pre
   * pre.next 就是末尾节点了
   * 当链表为空时，pre.next为null
   * @return
   */
  def getTailPre(): Node = {
    var pre = sentinel
    while (pre.next != null && pre.next.next != null) {
      pre = pre.next
    }

    pre
  }

  /**
   * 找到指定key的node节点
   * @param key
   * @return (true or false, pre-node， node)
   */
  def findK(key: Int): (Boolean, Node, Node) = {
    var prep: Node = sentinel
    var p = sentinel.next
    while (p != null && p.key != key) {
      p = p.next
      prep = prep.next
    }
    // 找到key
    if (p != null) {
      return (true, prep, p)
    }
    // p 是 null 没找到key
    (false, prep, p)

    // p可能是null，上层无法var (flag: Boolean, prep: Node, p: Node) 这样解包接收，报类型不匹配
    // 但是可以var res: (Boolean, Node, Node) = findK(1) 这样接收

  }

}

/**
 * 缓存策略有三种:
 * 1.FIFO, 先进先出
 * 2.LFU，最少使用
 * 3.LRU，最近最少使用
 * 使用单链表实现最近最少使用的缓存策略
 *
 * 单链表从后向前建立
 * 我们维护一个有序单链表，越靠近链表尾部的结点是越早之前访问的。当有一个新的数据被访问时，我们从链表头开始顺序遍历链表。
 * 1. 如果此数据之前已经被缓存在链表中了，我们遍历得到这个数据对应的结点，并将其从原来的位置删除，然后再插入到链表的头部。
 * 2. 如果此数据没有在缓存链表中，又可以分为两种情况：
 *    2.1如果此时缓存未满，则将此结点直接插入到链表的头部；
 *    2.2如果此时缓存已满，则链表尾结点删除，将新的数据结点插入链表的头部。
 *
 */

/**
 * 链表节点存储(k, v), 取元素的时候通过k遍历
 * @param key
 * @param value
 * @param next
 */
class Node(val key: Int, val value: Int, var next: Node)

object Test {
  def main(args: Array[String]): Unit = {
//    val (a: Int, b: Int) = (1, 22)
//    print(a, b)
    var lRUCache: LRUCache = new LRUCache(2);
//    var res: (Boolean, Node, Node) = lRUCache.findK(1)
//    println(res)
//    val (flag: Boolean, prep: Node, p: Node) = lRUCache.findK(1)
//    val (flag: Boolean, prep: Node, p: Node) = res
//    println(flag, prep, p)
    println(lRUCache.get(2))
    println(lRUCache.put(2, 6)) // 缓存是 {1=1}
    println(lRUCache.get(1))    // 返回 1
    println(lRUCache.put(1, 5)) // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
    println(lRUCache.put(1, 2)) // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
    println(lRUCache.get(1))  // 返回 -1 (未找到)
    println(lRUCache.get(2))   // 返回 3

  }
}