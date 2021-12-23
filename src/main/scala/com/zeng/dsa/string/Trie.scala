package com.zeng.dsa.string

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-11-22 10:48 下午
 * @function:
 */
object Trie {
  def main(args: Array[String]): Unit = {
    // 初始化根节点
    var root = new TrieNode(data = '/')
  }

  /**
   * 构建Trie树，返回根节点
   * @param root
   * @param word
   */
  def buildTrie(root: TrieNode, word: String): Unit = {
    val array: Array[Char] = word.toCharArray
    // 对于一个字符串来说，它的每个字符都是一个节点，我们从根节点开始，
    // 依次把他们串起来，最后末尾字符对应的节点标红即可
    var p: TrieNode = root
    for (elem <- array) {
      if (! p.children.contains(elem)) {
        var charNode = new TrieNode(data = elem)
        p.children.put(elem, charNode)
      }
      // 移动到下一个节点
      p = p.children(elem)
    }
    p.isEndChar = true
  }

  /**
   * 在Trie树中查找一个单词
   * @param root
   * @param word
   * @return
   */
  def find(root: TrieNode, word: String): Boolean = {
    val array: Array[Char] = word.toCharArray
    // 依次检查每个字符，从根节点进行深入
    var p: TrieNode = root
    for (elem <- array) {
      if (!p.children.contains(elem)) return false
      p = p.children(elem)
    }
    // 不能完全匹配，只是前缀
    if (p.isEndChar) true else false
    }

}


class TrieNode(val data: Char) {
  var isEndChar: Boolean = false
  val children: mutable.Map[Char, TrieNode] = mutable.Map()
}