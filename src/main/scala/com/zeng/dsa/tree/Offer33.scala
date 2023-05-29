package com.zeng.dsa.tree

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2022-02-28 9:25 上午
 * @desc:
 */
object Offer33 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 给定一个序列，判断它是不是正确的二叉查找树的后序遍历的结果(节点值不重复)
   *
   * 二叉查找树性质：
   * theory：对于任意一个节点A，左子树的所有节点值都 < A，右子树的所有节点值都 > A
   *
   * 递推公式：
   * 一个二叉查找树的后序遍历序列是正确的 = 当前根节点满足theory(站在根节点角度考虑) +
   * 左子树的后序遍历序列是正确的 + 右子树的后序遍历序列是正确的(站在左右子树角度考虑)
   *
   * 我们是先分左子树部分、右子树部分，然后在判断
   *
   * *使用范围下标[s, e]，表示左子树、右子树数据集:
   * 1. 假设序列下标[0, i]，根节点即为nums(i), m代表第一个大于等于根节点的元素下标，m初始化为-1
   * 2. 从前向后在[0, i] 找到第一个大于等于根节点的位置即为m
   * 则：[0, m-1] 为左子树序列， [m, i-1] 为右子树序列
   * 注意：左子树序列已经可以确定都是小于根节点的了，但是右子树序列需要再检查一遍
   *
   * 递归终止条件：
   * 处理的数据集 只有1个节点 或者 为空的时候，即  s >= e 返回true
   * s > e 为空集, s == e只有一个节点
   * (举例1 3 2， 1 2， 7 8 6 5 这几种情况即可验证~)
   *
   *
   * 结果，这个解法没有问题，但是scala自身函数调用特性的问题，可能是把postorder复制了多份，导致内存超限制
   * 换成传分区数组的方式
   * @param postorder
   * @return
   */
  def verifyPostorder(postorder: Array[Int]): Boolean = {
    if (postorder == null) return true
    // recurVerify(postorder, 0, postorder.length - 1)
    recurVerify2(postorder)
  }

  def recurVerify(postorder: Array[Int], start: Int, end: Int): Boolean = {
    if (start >= end) return true
    var m = -1
    Breaks.breakable(
      // 分左子树遍历序列，右子树遍历序列
      for (i <- start to end) {
        if (postorder(i) >= postorder(end)) {
          m = i
          Breaks.break()
        }
      }
    )

    // 左子树序列为[start, m-1]
    // 右子树序列为[m, end-1]
    // 额外校验右子树序列是否满足一致都 > 根节点
    for (j <- m until end) {
      if (postorder(j) < postorder(end)) return false
    }

    // 接着递归子问题：左右子树是否是正确的后序序列
    recurVerify(postorder, start, m - 1) && recurVerify(postorder, m, end)
  }


  def recurVerify2(postorder: Array[Int]): Boolean = {
    if (postorder.length <= 1) return true
    val start: Int = 0
    val end: Int = postorder.length - 1
    var m: Int = -1
    Breaks.breakable(
      // 分左子树遍历序列，右子树遍历序列
      for (i <- start to end) {
        if (postorder(i) >= postorder(end)) {
          m = i
          Breaks.break()
        }
      }
    )

    // 左子树序列为[start, m-1]
    // 右子树序列为[m, end-1]
    // 额外校验右子树序列是否满足一致都 > 根节点
    for (j <- m until end) {
      if (postorder(j) < postorder(end)) return false
    }

    // 接着递归子问题：左右子树是否是正确的后序序列
    recurVerify2(postorder.slice(start, m)) && recurVerify2(postorder.slice(m, end))
  }

}
