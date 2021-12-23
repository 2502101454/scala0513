package com.zeng.dsa.tree

/**
 *
 * @author zengwang
 * @create 2021-11-14 5:26 下午
 * @function:
 */
object Leet450 {
  def deleteNode(root: TreeNode, key: Int): TreeNode = {
    // 先做查找要删除的节点及其父节点
    var findRes: List[TreeNode] = findNode(root, key)
    if (findRes.size == 0) {
      return root
    }

    // 做删除，p是要删除的节点，pp是p的父节点
    var pp = findRes(0)
    var p = findRes(1)
    // p_extend是p的接班人，即p被删除后，应该使用哪个节点给pp用
    var p_extend: TreeNode = null

    // 分三种情况
    // 1. p有左右两个儿子节点
    if (p.right != null && p.right != null) {
      // 找p的右子树中最小的那个节点以及该节点的父节点
      var (father, minNode)  = findMinNodeInRight(p)
      p.value = minNode.value
      // 这里判断father和minNode的关系的逻辑和下面的pp和p是一样的，所以复用一下
      p_extend = null
      pp = father
      p = minNode
    }
    // 2. p有且只有一个儿子结点
    // 找到P的接班人
    p_extend = if (p.left != null) {
      p.left
    } else if (p.right != null) {
      p.right
    } else {
      // 3. p是一个叶子结点
      null
    }

    // 边界值判断 输入 [0] 0，则当前pp就是null，p就是root自身，那么就该返回null
    // [2, 1] 2 应该输出[1]
    if (pp == null) {
      // 查看有无接班人
      if (p_extend == null) {
        return null
      } else {
        return p_extend
      }
    }

    // 针对pp和p的关系，使用p的接班人
    if (pp.left == p) {
      pp.left = p_extend
    } else if (pp.right == p) {
      pp.right = p_extend
    }

    return root
  }

  def findNode(root: TreeNode, key: Int): List[TreeNode] = {
    /**
     * 查找key对应的节点，找到，则返回List(父节点, 删除节点)
     */
    var pp: TreeNode = null
    var p = root
    while (p != null && p.value != key) {
      val value = p.value
      pp = p
      if (key > value) {
        p = p.right
      } else {
        p = p.left
      }
    }
    if (p != null && p.value == key) {
      List(pp, p)
    } else {
      List()
    }
  }

  def findMinNodeInRight(p: TreeNode): (TreeNode, TreeNode) = {
    // 在传入的节点的右子树中查找最小值的节点
    var father = p
    var min = father.right
    while (min.left != null) {
      father = min
      min = min.left
    }
    (father, min)
  }
}

