package com.zeng.dsa.tree

import java.util
import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2022-02-27 8:09 下午
 * @desc:
 */
object offer32_3 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 同32题一样，一层层遍历二叉树
   * 要求： 第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
   * 第三行再按照从左到右的顺序打印，其他行以此类推。
   *
   * 思路：
   * 1.解法和上题一样，每次只取当前层的节点，取完就退出；
   * 2.但是结果集中，每层节点值的顺序 需要分奇偶数层而定，观察发现：
   *   计数从0开始，第0层是正序，第1层是逆序，第2层又是正序，
   *   所以，从0开始，偶数层正序，奇数层逆序
   * @param root
   * @return
   */
  def levelOrder(root: TreeNode): List[List[Int]] = {
    if (root == null) return List()

    val buffer: ListBuffer[List[Int]] = ListBuffer[List[Int]]()
    val queue = new util.LinkedList[TreeNode]()
    queue.offer(root)
    // 按需取父层节点
    while (queue.size() > 0) {
      var num: Int = queue.size()
      val tmp: ListBuffer[Int] = ListBuffer()
      // 取完就退出
      while (num > 0) {
        val node: TreeNode = queue.poll()
        tmp.append(node.value)

        val left: TreeNode = node.left
        if (left != null) queue.offer(left)
        val right: TreeNode = node.right
        if (right != null) queue.offer(right)
        num -= 1
      }
      // 从0层开始处理，偶数层正序，奇数层逆序
      if (buffer.size % 2 == 0) {
        buffer.append(tmp.toList)
      }
      else {
        buffer.append(tmp.reverse.toList)
      }
    }

    buffer.toList
  }
}
