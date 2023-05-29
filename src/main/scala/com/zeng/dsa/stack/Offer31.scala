package com.zeng.dsa.stack

import java.util

/**
 *
 * @author zengwang
 * @create 2022-02-24 11:18 下午
 * @desc:
 */
object Offer31 {
  def main(args: Array[String]): Unit = {

  }
  /**
   * 输入两个序列，pushed为入栈序列，popped是出栈序列，判断popped是否是正确的出栈序列
   * 比如，入栈：1，2，3 出栈可以是 2 3 1 (入栈2后，再出栈2，再入栈3...)
   * 思考: 辅助栈法
   * 自己初始化一个栈，按照题目的入栈序列、出栈序列 走一遭
   *
   * 注意：
   *  入栈序列，只是记录了入栈操作，中间可以存在  一连续出栈操作(出栈序列)，
   *  所以每次入栈后都要判断当前栈内元素是否满足  一部分出栈序列
   *
   * 做法:
   * 1.遍历pushed，不断的压栈到辅助栈，初始化游标i，位于出栈序列首位
   *  每次压栈，结合辅助栈与出栈序列，判断当前是否满足 一部分出栈序列？
   *    满足：
   *      辅助栈尝试出栈(i也不断更新)
   *    不满足：辅助栈继续压栈
   *
   * 2.辅助栈不为空，则说明出栈序列不正确
   *  因为正确的出栈序列，我们每次入栈都尝试 出栈一部分序列
   *
   * 3.举例分析一波
   * @param pushed
   * @param popped
   * @return
   */
  def validateStackSequences(pushed: Array[Int], popped: Array[Int]): Boolean = {
    val stack = new util.Stack[Int]()
    var i = 0
    // 遍历入栈序列
    for (elem <- pushed) {
      stack.push(elem)
      // 查看当前辅助栈中的元素是否满足一部分出栈序列
      // 防止辅助栈空了，需要入栈(满足了出栈序列后，我自己却不够用了,需要从外部补给)
      while (!stack.isEmpty && stack.peek() == popped(i)) {
        stack.pop()
        i += 1
      }

      }
    stack.isEmpty

    }

}
