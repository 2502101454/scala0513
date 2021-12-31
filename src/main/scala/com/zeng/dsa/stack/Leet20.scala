package com.zeng.dsa.stack
import java.util
import scala.collection.mutable
import java.util.Stack
/**
 *
 * @author zengwang
 * @create 2021-12-28 4:29 下午
 * @function:
 */

object Leet20 {

  def main(args: Array[String]): Unit = {

  }
  /**
   * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
     有效字符串需满足：
     左括号必须用相同类型的右括号闭合。
     左括号必须以正确的顺序闭合。

    思路:
    遍历字符串，遇到左括号入栈，右括号出栈，同时比对是否是对应的左括号，一旦不相等，就是false
    最后遍历结束，栈为空则是有效的，否则无效
   * @param s
   */
  def isValid(s: String): Boolean = {
    val chars: Array[Char] = s.toCharArray
    // 栈内只存左括号
    val stack = new util.Stack[Char]()
    // 定义括号映射
    val brackets: Map[Char, Char] = Map('(' -> ')', '{' -> '}', '[' -> ']')
    for (elem <- chars) {
      if (brackets.contains(elem)) {
        stack.push(elem)
      } else {
        // 当前遇到右括号，出栈左括号
        if (stack.isEmpty) {
          return false
        }
        val leftBracket: Char = stack.pop()
        if (brackets(leftBracket) != elem) {
          return false
        }
      }
    }
//    if (stack.isEmpty) {
//      return true
//    } else {
//      return false
//    }
    stack.isEmpty
  }
}
