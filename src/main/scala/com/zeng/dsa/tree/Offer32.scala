package com.zeng.dsa.tree

import java.util
import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2022-02-26 9:41 上午
 * @desc:
 */
object Offer32 {
  def main(args: Array[String]): Unit = {
    val queue = new util.LinkedList[Int]()
    // 向队列中加入元素
    queue.offer(1)
    queue.offer(2)
    // 求队列大小
    println(queue.size()) // 2
    // 返回第一个元素，但不删除
    println(queue.peek()) // 1
    // 出队一个元素(就删除了)
    println(queue.poll()) // 1
    println(queue.size()) // 1

  }

  /**
   * 二叉树的层序遍历, 但是每一层打印一行(放到一个list中)
   * 思考： 普通的层序遍历，入队根节点，出队的同时还会入队子节点，队列不空的时候，重复这个过程，
   * 问题：我们无法划分出某一层的子节点
   *
   * 解决：如果我们只取当前层的节点后(依然取子节点入队)，就退出，这样队列 里面就只保留了下一层的所有节点~
   * 如何实现？
   * 我们需要知道当前层节点的个数，这样我们取够这么多个后，就退出
   * 当前层节点的个数是多少？
   * 1.先入队根节点，那么当前层节点的个数就是1，即queue.size
   * 2.取1个节点，即把root取出来（同时入队两个下层节点，），则退出，则下层节点的个数就是queue.size，即2
   * 3.取2个节点，则退出，以此类推~
   *
   * 代码实现(如何衔接上述的流程):
   * 1.初始化queue，入队根节点
   * 2.当queue.size > 0，取queue.size个节点(同时入队子节点)，则退出
   * 循环条件: while queue.size > 0，当取完当前层queue.size个节点后，没有下层节点的时候，queue为空 退出
   *
   * @param root
   * @return
   */
  def levelOrder(root: TreeNode): List[List[Int]] = {
    if (root == null) return List()
    // 初始化队列，放入根节点
    val queue = new util.LinkedList[TreeNode]()
    queue.offer(root)

    val buffer: ListBuffer[List[Int]] = ListBuffer()
    // 父层存于队列中
    while (queue.size() > 0) {
      // tmp 存储父层的节点值
      val tmp = ListBuffer[Int]()
      // 知道父层的节点个数，依次只取完父层的节点,子层入队
      var curLevelNum: Int = queue.size()
      while (curLevelNum > 0) {
        val node: TreeNode = queue.poll()
        tmp.append(node.value)
        if (node.left != null) queue.offer(node.left)
        if (node.right != null) queue.offer(node.right)
        curLevelNum -= 1
      }
      // 取完父层后，退出
      buffer.append(tmp.toList)
      // 子层变父层
    }
    buffer.toList
  }
}
