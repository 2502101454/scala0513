package com.zeng.dsa.tree

/**
 *
 * @author zengwang
 * @create 2022-02-22 12:57 下午
 * @desc:
 */
object Offer28 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 判断二叉树是不是对称的
   *
   * 分析：
   * 转换问题：求二叉树的左右子树，*普通两颗树*之间是否对称
   *  为了不混乱，左右子树理解为普通两棵树即可
   *
   * 1.拆分子问题(就题目样例二叉树分析即可)
   * A树和B树 对称 =
   *      A树根节点值 等于 B树根节点值      &&
   *      (A的左子树, B的右子树) is对称 &&
   *      (A的右子树, B的左子树) is 对称
   * 2.实现代码
   * 3.代入案例检查
   * @param root
   * @return
   */
  def isSymmetric(root: TreeNode): Boolean = {
    if (root == null) return true

    ABSymmetric(root.left, root.right)
  }

  /**
   * 检查A、B两颗树是否对称
   * @param a
   * @param b
   * @return
   */
  def ABSymmetric(a: TreeNode, b: TreeNode): Boolean = {
    if (a == null && b == null) return true
    else if (a == null || b == null) return false
    else if (a.value != b.value) return false
    else {
      // 如果A、B两树的根节点值相等，接着再看
      ABSymmetric(a.left, b.right) && ABSymmetric(a.right, b.left)
    }
  }
}
