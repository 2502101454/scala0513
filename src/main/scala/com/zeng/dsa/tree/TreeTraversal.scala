package com.zeng.dsa.tree

import java.util
import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-12-05 9:10 下午
 * @function: 二叉树的前、中、后 迭代遍历
 */
object TreeTraversal {
  def main(args: Array[String]): Unit = {

    val buffer: ListBuffer[Int] =new ListBuffer()
    buffer.append(1)
    println(buffer)
//    val root: TreeNode = buildTree()
//    println(preorderTraversal(root))
//    println(colorfulPreOrderTraversal(root))
//    println(colorfulInOrderTraversal(root))
//    println(colorfulPostOrderTraversal(root))
  }

  def buildTree(): TreeNode = {
    var t3 = new TreeNode(3, null, null)
    var t2 = new TreeNode(2, t3, null)
    var t1 = new TreeNode(1, null, t2)
    t1
  }

  /**
   * 递归的前序遍历
   * @param root
   * @return
   */
  def recursivePreOrder(root: TreeNode, listBuffer: ListBuffer[Int]): Unit = {
    if (root == null) return
    listBuffer.append(root.value)
    recursivePreOrder(root.left, listBuffer)
    recursivePreOrder(root.right, listBuffer)
  }

  /**
   * 递归的中序遍历
   * @param root
   * @return
   */
  def recursiveInOrder(root: TreeNode, listBuffer: ListBuffer[Int]): Unit = {
    if (root == null) return
    recursivePreOrder(root.left, listBuffer)
    listBuffer.append(root.value)
    recursivePreOrder(root.right, listBuffer)
  }

  /**
   * 递归的后序遍历
   * @param root
   * @return
   */
  def recursivePostOrder(root: TreeNode, listBuffer: ListBuffer[Int]): Unit = {
    if (root == null) return
    recursivePreOrder(root.left, listBuffer)
    recursivePreOrder(root.right, listBuffer)
    listBuffer.append(root.value)
  }

  /**
   * 前序遍历:
   * 1.使用栈，先根节点入栈，然后出栈一节点(进行使用)，依次入栈该节点右子节点、左子节点
   * 为何先入栈右，其次才是左？ 因为这样出栈的时候才会是先左，再右
   * 2.栈不空则持续循环
   *
   * @param root
   */
  def preorderTraversal(root: TreeNode): List[Int] = {
    var list = List[Int]()
    val stack = new util.Stack[TreeNode]
    if (root != null) stack.push(root)
    while (!stack.empty()) {
      val node: TreeNode = stack.pop()
      list = list :+ node.value
      if (node.right != null) stack.push(node.right)
      if (node.left != null) stack.push(node.left)
    }
    list
  }


  /**
   * 也就前序遍历好写一点，一个栈搞定，不需要额外操作，中序、后序的迭代法还需要额外操作，就算理解记住了，面试也很难写出来，
   * 这里干脆直接上 颜色标记法，统一而又简单
   */

  // TODO 精选里的颜色标记法
  /**
   * 前、中、后序的遍历都是表示 --树中任意节点，其本身和它的左右子节点的遍历打印顺序--
   * 所以前序就是: 先打印节点本身，再打印左、再打印右
   * 后序就是: 左、右、本身
   * 中序就是: 左、本身、右
   *
   * 颜色标记法(很随意的一个名字),思想:
   * white 表示没有挖掘过左右子节点的，gray表示挖掘过左右子节点了
   *
   * 1. 根节点标为white, 先入栈，再出栈：
   *  1.1 遇到white的 node 就挖掘其左右子节点，左右子节点是新节点，标记为white，由于当前节点已挖掘过，
   *      那么标记为gray，入栈节点本身及左右子节点，使用特性2
   *  1.2 遇到gray的node就直接进行输出了
   *  1.3 遇到null节点跳过，这是曾经挖掘过叶子节点的结果，始终维护着顺序，但是不用处理，跳过就行
   *
   * 2. 反顺序入栈（反着想要的遍历顺序入栈）
   *  我们利用栈的特性(反序性)，针对任意节点，我们都按照相反的顺序入栈，最后出栈肯定是正确的顺序了
   *  比如中序，正确顺序是 左、中、右，我们入栈就要 右、中、左，这样出栈才能保证是中序遍历的顺序
   *
   * 3.栈不为空持续出栈, 进行上述流程
   *
   * 看这个理解的顺序应该是 1 ->1.1-> 2 -> 3 -> 1.2 1.3
   *
   */

  /**
   *  颜色标记前序遍历
   * @param root
   * @return
   */
  def colorfulPreOrderTraversal(root: TreeNode): List[Int] = {
    var list = List[Int]()
    val (white: Int, gray: Int) = (0, 1)

    val stack = new util.Stack[(Int, TreeNode)]
    if (root != null) stack.push((white, root))
    while (!stack.isEmpty()) {
      val tuple: (Int, TreeNode) = stack.pop()
      val color = tuple._1
      val node = tuple._2
      if (node != null) {
        if (color == white) {
          // 挖掘左右子节点，入栈，记住反顺序入栈!
          stack.push((white, node.right))
          stack.push((white, node.left))
          stack.push((gray, node))
        } else {
          list = list :+ node.value
        }
      }
    }

    list
  }

  /**
   *  颜色标记中序遍历
   * @param root
   * @return
   */
  def colorfulInOrderTraversal(root: TreeNode): List[Int] = {
    var list = List[Int]()
    val (white: Int, gray: Int) = (0, 1)

    val stack = new util.Stack[(Int, TreeNode)]
    if (root != null) stack.push((white, root))
    while (!stack.isEmpty()) {
      val tuple: (Int, TreeNode) = stack.pop()
      val color = tuple._1
      val node = tuple._2
      if (node != null) {
        if (color == white) {
          // 挖掘左右子节点，中序遍历, 左、中、右；相反则右中左
          stack.push((white, node.right))
          stack.push((gray, node))
          stack.push((white, node.left))
        } else {
          list = list :+ node.value
        }
      }
    }

    list
  }

  /**
   *  颜色标记后序遍历
   * @param root
   * @return
   */
  def colorfulPostOrderTraversal(root: TreeNode): List[Int] = {
    var list = List[Int]()
    val (white: Int, gray: Int) = (0, 1)

    val stack = new util.Stack[(Int, TreeNode)]
    if (root != null) stack.push((white, root))
    while (!stack.isEmpty()) {
      val tuple: (Int, TreeNode) = stack.pop()
      val color = tuple._1
      val node = tuple._2
      if (node != null) {
        if (color == white) {
          // 挖掘左右子节点，后序遍历, 左、右、中；相反则中右左
          stack.push((gray, node))
          stack.push((white, node.right))
          stack.push((white, node.left))
        } else {
          list = list :+ node.value
        }
      }
    }

    list
  }

  /**
   * 按层遍历
   * 使用队列，根节点先入队，每次把队列取空放到集合A中，然后取A集合所有node的子节点再依次入队，
   * 最后直到队列为空结束
   * @param root
   * @return
   */
  def levelOrder(root: TreeNode): List[List[Int]] = {
    var list = List[List[Int]]()
    val queue: util.Queue[TreeNode] = new util.LinkedList[TreeNode]()
    if (root != null) {
      queue.offer(root)
    }
    while (!queue.isEmpty) {
      var listLevel = List[Int]()
      val listBuffer = new ListBuffer[TreeNode]()
      // 获取当前队列中所有的节点
      while (!queue.isEmpty) {
        listBuffer.append(queue.poll())
      }

      for (node <- listBuffer) {
        listLevel = listLevel :+ node.value
        if (node.left != null) {
          queue.offer(node.left)
        }
        if (node.right != null) {
          queue.offer(node.right)
        }
      }

      list = list :+ listLevel

    }
    list
  }

}

