package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-12 10:37 下午
 * @function:
 */
object Test03_MatchTupleExtend {
  def main(args: Array[String]): Unit = {
    // 1. 在变量声明时匹配
    // 可以同时声明多个变量
    val (x, y) = (10, "hello")
    println(s"x: $x y: $y") // x: 10 y: hello

    val List(first, second, _*) = List(23, 15, 9, 78)
    println(s"first $first, second $second")
    // 调用 :: 不断的取list的head 和 tail，再对tail接着调用 ::
    val a :: b :: sadf = List(23, 15, 9, 78)
    println(s"a: $a, b: $b, rest: $sadf") // a: 23, b: 15, rest: List(9, 78)

    // 2. for 推导式(就是for 循环)中进行模式匹配
    val list = List(("a", 12), ("b", 3), ("a", 7))
    // 2.1 原本的遍历方式
    for (elem <- list) {
      println(elem._1 + " " + elem._2)
    }
    // 2.2 将List的元素直接定义为元组，对变量赋值
    for ((word, count) <- list) {
      println(word + ": " + count)
    }
    // 2.3 不考虑某个位置的变量，只遍历key或者value
    for ((word, _) <- list) {
      println(word)
    }
    // 2.4 指定某个位置的值必须是多少，有点类似于循环守卫
    for (("a", count) <- list ) {
      println(count)
    }

  }
}
