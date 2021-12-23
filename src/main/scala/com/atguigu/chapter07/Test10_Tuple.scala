package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-11 12:50 下午
 * @function:
 */
object Test10_Tuple {
  def main(args: Array[String]): Unit = {
    // 1. 声明元组
    val tuple: (Int, String, Boolean) = (1, "as", true)
    // 元组是只读的，不可以赋值
    //    tuple._1 = 3
    println(tuple)
    // 2. 访问数据
    // 这种下标从1开始
    println(tuple._1)
    println(tuple._2)

    // 这样访问下标从0开始
    println(tuple.productElement(0))
    println("==========")
    // 3. 遍历元组数据
    for (elem <- tuple.productIterator) println(elem)
    // 4. 嵌套元组
    val tuple1: (Int, Double, String, (Int, String), Int) = (12, 0.3, "hello", (23, "scala"), 3)
    println(tuple1._4._2)
  }
}
