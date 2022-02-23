package com.zeng.dsa.tree

/**
 *
 * @author zengwang
 * @create 2022-02-21 1:17 下午
 * @desc:
 */
object Offer26 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 输入两棵树A，B，判断B树是不是A树的子结构(子树)
   * 空树不是任意一个树的子结构
   *
   * 分析：
   * 1.前序遍历A中的每个节点
   *    1.1 当遇到节点值和B的根节点值相同的时候
   *    我们再额外遍历B树(前序)，遍历过程中顺带取A对应位置的节点做判断, 遵循，以B树为主(站在B树的角度):
   *      B树中有的节点值，A树一定要有;
   *      B树中没有的节点值，A树无所谓(可有可无);
   *
   *    1.2 当遇到节点值和B的根节点值不同时，则继续遍历A的其他节点
   *
   *  两个前序要记牢，短路||、&&关系要分清
   * @param A
   * @param B
   * @return
   */
  def isSubStructure(A: TreeNode, B: TreeNode): Boolean = {
    if (A == null || B == null) return false

    // 遇到A节点值是B的根节点的情况
//    if (A.value == B.value) {
//      // 以B树为主，从A树当前节点开始，挨个比对节点值
//    }
    // 把上述逻辑一并放到 判断B树中做

    inspectMoreByB(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B)

  }

  /**
   * 站在B树角度开始前序遍历： 检测B树是否是A节点开始的树的子结构
   * @param A
   * @param B
   * @return
   */
  def inspectMoreByB(A: TreeNode, B: TreeNode): Boolean = {
    // 第一次调用此处时，B不会是null的，因为在外边已经过滤了，而且外边也没有让B做子树遍历，只有这里面才让B做自己子树遍历，才会是null
    if (B == null)  true
      // 下面表示： B不是null， A是null； B不是null，A不是null，但是A B 值不相等
    else if (A == null || A.value != B.value) false
    else {
      // 递归拆分子问题理解:
      // B树是A节点树的子结构判定结果 =  (B节点=A节点) && (B 左子树判定结果) && (B 右子树判定结果)
      inspectMoreByB(A.left, B.left) && inspectMoreByB(A.right, B.right)
    }

  }
}
