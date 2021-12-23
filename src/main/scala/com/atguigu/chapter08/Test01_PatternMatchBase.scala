package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-12 3:38 下午
 * @function:
 */
object Test01_PatternMatchBase {
  def main(args: Array[String]): Unit = {
    // 1. 基本定义语法
    val x: Int = 4
    val y: String = x match {
      case 1 => "one"
      case 2 => "two"
      case 3 => "three"
      case _ => "other"
    }
    println(y)

    // 2. 配合函数，实现简单的二元运算
    val a = 12
    val b = 9
    // 对参数做match匹配
    def matchDualOp(op: Char): Any = op match {
      case '+' => a + b
      case '-' => a - b
      case '*' => a * b
      case _ => "非法运算符"
    }
    println(matchDualOp('+'))
    println(matchDualOp('/'))

    // 3. 模式守卫，没有确定的值，而是进行判断
    // 求一个数的绝对值
    def abs(num: Int): Int = {
      num match {
        // 用变量i来接受num的值
        case i if i >= 0 => i
        case i if i < 0 => -i
      }
    }
    println(abs(1))
    println(abs(-1))
    println(abs(0))
  }
}
