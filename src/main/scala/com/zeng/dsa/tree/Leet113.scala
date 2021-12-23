package com.zeng.dsa.tree

import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2021-12-07 12:56 下午
 * @function:
 */
object Leet113 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，
   * 找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
   * 叶子节点 是指没有子节点的节点。
   *
   * 思考：深度遍历问题
   * @param root
   * @param targetSum
   * @return
   */
  def pathSum(root: TreeNode, targetSum: Int): List[List[Int]] = {
    val listBuffer = new ListBuffer[List[Int]]()
    val pathList = List[Int]()

    if (root != null) {
      collectPath(root, targetSum, 0, pathList, listBuffer)
    }

    listBuffer.toList
  }

  /**
   * 递归终止条件:
   * 当前是叶子节点
   *
   * @param root
   * @param targetSum
   * @param totalCount 先前累加的值，不包括当前节点
   * @param pathList  当前节点之前的一条路径，不包括当前节点
   * @param pathsList 最后的结果，收集所有符合条件的路径
   */
  def collectPath(root: TreeNode, targetSum: Int, totalCount: Int,
                  pathList:  List[Int],
                  pathsList:  ListBuffer[List[Int]]): Unit = {

    // 不要一进来就给路径加入节点，这个可变集合会越来越大!

    // 叶子节点，递归结束
    if (root.left == null && root.right == null) {
      // 如果值相等，则找到了一条路径，给路径加入叶子节点
      if (totalCount + root.value == targetSum) {
        val path: List[Int] = pathList :+ root.value
        pathsList.append(path)
        return
      }
    }

    // 不是叶子节点，则加入子节点到路径中，继续递归左右子树, 收集所有路径
    // 不应该使用listBuffer，都换成list，然后传入的时候不断的变换成新的list引用即可
    if (root.left != null) {
      collectPath(root.left, targetSum, totalCount + root.value,
        pathList :+ root.value, pathsList)
    }
    if (root.right != null) {
      collectPath(root.right, targetSum, totalCount + root.value,
        pathList :+ root.value, pathsList)
    }

  }
}
