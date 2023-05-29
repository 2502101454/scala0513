package com.zeng.dsa.tree

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2022-02-28 11:34 下午
 * @desc:
 */
object Offer34 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 找出所有 从根节点到叶子结点 的 路径 之和等于target的路径
   *
   * 分析：回溯法
   * 1.分阶段输入
   * 2.代码实现每个阶段要做的事情，同时对下个阶段进行调用(承上启下)
   *
   * 实现:
   * 1.当前节点是否为叶子节点:
   *  1.1 是叶子节点，求路径之和是否为target，是则加入buffer结果集中，否则什么也不做
   *  1.2 不是叶子节点，继续深度遍历左右子树
   * 2.回溯返回上级的时候，把之前的节点从路径里剔除
   *
   * 这些基本是 一个阶段内要做的事情
   *
   * 最后代入测试用例分析即可
   * @param root
   * @param target
   * @return
   */
  def pathSum(root: TreeNode, target: Int): List[List[Int]] = {
    if (root == null) return List()

    // 不断的深度遍历收集节点，然后回溯的时候剔除节点
    val historyValue: ListBuffer[Int] = ListBuffer()
    val paths = ListBuffer[List[Int]]()
    dfsPaths(root, historyValue, 0, target, paths)
    paths.toList
  }

  /**
   *
   * @param root
   * @param historyValue 不包括root节点的路径，即要将root加入historyValue之前，history的结果
   * @param historySum 不包括root的history的总和，为了提高求和速度
   * @param target
   * @param targetPaths 所有的结果路径
   */
  def dfsPaths(root: TreeNode, historyValue: ListBuffer[Int],
               historySum: Int, target: Int, targetPaths: ListBuffer[List[Int]]): Unit = {
    historyValue.append(root.value)
    val curSum = historySum + root.value

    val left: TreeNode = root.left
    val right: TreeNode = root.right

    // 是叶子节点
    if (left == null && right == null) {
      // 路径相等，clone一份，不然后面回溯的时候要从historyValue对象中剔除
      if (curSum == target) targetPaths.append(historyValue.clone().toList)
    } else {
      // 接着深度遍历左右子树
      if (left != null) {
        dfsPaths(left, historyValue, curSum, target, targetPaths)
        // 回溯的时候，移除上次加入路径的节点
        historyValue.remove(historyValue.length - 1)
      }
      if (right != null) {
        dfsPaths(right, historyValue, curSum, target, targetPaths)
        historyValue.remove(historyValue.length - 1)
      }
    }
  }
}
