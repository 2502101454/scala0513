package com.zeng.dsa.queue

import java.util

/**
 *
 * @author zengwang
 * @create 2022-01-16 10:25 上午
 * @function:
 */
object offer09 {
  def main(args: Array[String]): Unit = {

  }
}

/**
 * 使用两个栈实现队列机制
 *
 * 分析:
 * 设有栈A，B，
 * 1.入队操作:
 *  元素一直压栈到A中
 * 2.出队操作:
 *  A全部出栈，入栈至B中，这样顺序就变成了队列的顺序了，但是对应三种情况:
 *  2.1 B 不为空，出队直接B.pop()
 *  2.2 B 为空
 *    a. 但是A不为空，拉取A的所有元素入栈
 *    b. A为空，没有元素了，返回-1
 *
 * 点评：A、B有点缓冲区的感觉，在将A栈元素全部转移给B的后，A缓冲区就空了，
 * 可以后续继续压栈，积攒元素；
 * B这边一次累积了A的所有元素后，可以提供一阵子的出栈消耗了，当消耗空了
 * 再去A中拉取所有元素
 *
 */
class CQueue() {
  private val stackA = new util.Stack[Int]()
  private val stackB = new util.Stack[Int]()

  def appendTail(value: Int): Unit = {
    // A 始终是在积攒元素，留待B的按需拉取
    this.stackA.push(value)
  }

  def deleteHead(): Int = {
    if (this.stackB.isEmpty) {
      if (this.stackA.isEmpty) {
        // A, B 都空
        return -1
      } else {
        // B 空，A不空，拉取A的所有元素
        while (!this.stackA.isEmpty) {
          this.stackB.push(this.stackA.pop())
        }
      }
    }
    // B 现在不空了，就继续消耗
    this.stackB.pop()
  }
}