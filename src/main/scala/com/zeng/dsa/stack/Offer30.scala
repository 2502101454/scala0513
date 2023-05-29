package com.zeng.dsa.stack

import java.util
import java.util.Stack
/**
 *
 * @author zengwang
 * @create 2022-02-24 12:55 下午
 * @desc:
 */
object Offer30 {
  def main(args: Array[String]): Unit = {

  }


}

/**
 * 包含min函数的最小栈，希望使用min方法，在O(1)时间复杂度内得到栈中的最小值
 *
 * 分析：
 *  初始时，我想每个栈元素都捆绑一个变量curMin，代表入栈到当前元素为止，栈内最小的元素值是多少
 *  但是这个方法空间复杂度直接翻倍
 *
 *  辅助栈法：
 *  栈A 用于维持正常的出栈入栈， 栈B 专门存放栈A入栈过程中的小元素、更小元素、更更小元素~
 *  B的栈顶代表--栈A中目前的最小的元素
 *
 *  具体方式如下:
 *  初始 A， B为空
 *  1.x入栈A，比较当前B的栈顶，如果x <= B栈顶，则x也要入栈B
 *  2.出栈A，出栈元素记做x:
 *    x > B栈顶元素，B栈什么也不做;
 *    x == B栈顶元素，B栈也得出栈一次
 *
 *  3.求min直接返回B的栈顶即可
 */
class MinStack() {

  /** initialize your data structure here. */
  private val stack = new util.Stack[Int]()
  // 存小元素的辅助栈
  private val minersStack = new util.Stack[Int]()

  def push(x: Int): Unit = {
    stack.push(x)
    // 处理小元素栈
    if (this.minersStack.isEmpty) {
      this.minersStack.push(x)
    } else {
      val curMin: Int = this.minersStack.peek()
      if (x <= curMin) this.minersStack.push(x)
    }
  }

  def pop() {
    if (!this.stack.isEmpty) {
      val ele: Int = this.stack.pop()
      // A 栈不空的时候，B栈不会为空的
      val curMin: Int = this.minersStack.peek()
      if (ele == curMin) this.minersStack.pop()
    }
  }

  def top(): Int = {
    this.stack.peek()
  }

  def min(): Int = {
    this.minersStack.peek()
  }

}