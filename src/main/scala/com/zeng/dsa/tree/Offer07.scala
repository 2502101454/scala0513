package com.zeng.dsa.tree

/**
 *
 * @author zengwang
 * @create 2022-01-15 8:31 下午
 * @function:
 */
object Offer07 {

  def main(args: Array[String]): Unit = {
    val ints: Array[Int] = Array(1, 2, 3, 2, 4)
    println(ints.indexOf(2))
    println(ints.indexOf(4))
    println(ints.indexOf(5))
    println(ints.slice(0, 0).length)
  }

  /**
   * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
   * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
   *
   * 分析：
   * 1.根据前序的第一个节点，将中序划分为左右子树的中序遍历结果(左右两部分)
   * 2.知道左右子树的长度后，再前序中划分出了左右子树的前序遍历结果
   * 3.根据左子树的前序、中序结果，重建左子树；右子树同理
   *
   * 这就是是递归的引出，我们递归做一件事，输入前序、中序 两个序列，返回基于当前输入，构建出的二叉树的根节点
   * 递推公式: fun(preList, mediumList) =
   *     Node(preList(0))
   *     左子树中序结果，右子树中序结果 = partition(mediumList)
   *     左子树前序结果，右子树前序结果 = partition(preList)
   *     Node.left = fun(左子树前序，左子树中序)
   *     Node.right = fun(右子树前序，右子树中序)
   *     return Node
   *
   * 递归终止条件: preList.len == 0 && mediumList.len == 0
   *
   * 备注: 普遍适用大法分析二叉树问题挺好的，我们假设输入是一棵满二叉树，然后得出的递归规律，写出代码流程也是符合
   * 一些残缺情况的二叉树的!
   * @param preorder
   * @param inorder
   * @return
   */
  def buildTree(preorder: Array[Int], inorder: Array[Int]): TreeNode = {
    if (preorder.length == 0 && inorder.length == 0) return null

    val value: Int = preorder(0)
    val node = new TreeNode(value)
    // 找到左、右子树的中序遍历部分
    val pivot: Int = inorder.indexOf(value)
    val leftInorder = inorder.slice(0, pivot)
    val rightInorder = inorder.slice(pivot + 1, inorder.length)
    // 找到左、右子树的前序遍历部分
    val leftPreorder = preorder.slice(1, 1 + leftInorder.length)
    val rightPreorder = preorder.slice(1 + leftPreorder.length, preorder.length)

    node.left = buildTree(leftPreorder, leftInorder)
    node.right = buildTree(rightPreorder, rightInorder)
    node
  }

//  def buildMan()

}
