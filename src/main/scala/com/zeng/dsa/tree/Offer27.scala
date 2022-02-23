package com.zeng.dsa.tree

/**
 *
 * @author zengwang
 * @create 2022-02-22 9:30 上午
 * @desc:
 */
object Offer27 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 二叉树的镜像
   *
   * 分析：
   * 二叉树，多用递归实现，需要分子问题，不求甚解
   * 递推公式:
   * 根节点的树的镜像 = 左子树的镜像处理后 + 右子树镜像处理后 + 左右子树互换
   * @param root
   * @return
   */
  def mirrorTree(root: TreeNode): TreeNode = {
    if (root ==  null) return null
    // 递归解决子树的镜像
    mirrorTree(root.left)
    mirrorTree(root.right)
    // 左右子树交换
    val tmp: TreeNode = root.left
    root.left = root.right
    root.right = tmp
    root
  }
}
