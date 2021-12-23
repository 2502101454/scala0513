package com.atguigu.chapter02

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-10-30 10:23 下午
 * @function:
 */
object Test06_BreakContinue {
  def main(args: Array[String]): Unit = {

    //  Breaks.break() 跳出整个breakable 包起来的代码块
    Breaks.breakable(
      for (i <- 1 to 10){
        println(i)
        if (i == 3) Breaks.break()
      }
    )
    println("=======")
    // 最内存for循序break，这里跳出整个breakable包起来的代码块，接着下面执行，即整个嵌套的for循环块
    Breaks.breakable(
      for (i <- Range(1, 3)) {
        for (j <- Range(1, 3)) {
          println(s"$i $j")
          if (i == 1) Breaks.break()
        }
      }
    )
    println("+++++++")

    // 实现continue，Scala中没有continue关键字
    for (i <- 1 to 4) {
      Breaks.breakable(
        // 把break当continue的用
        if (i % 2 ==0) Breaks.break()
          // 非continue执行的逻辑如下
        else println(i)
      )
    }

    for (i <- 1 to 4) {
      Breaks.breakable(
        // 把break当continue的用
        if (i % 2 ==0) Breaks.break()
        // Breaks中只能嵌套一个代码块，if else是一个，两个 if是两个代码块
        // 编译报错
//        if ( i % 3 == 0) println(i)
      )
    }
  }

}
