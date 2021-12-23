package com.atguigu.chapter05

/**
 *
 * @author zengwang
 * @create 2021-11-02 10:56 下午
 * @function:
 */
object Test02_Lazy {
  def main(args: Array[String]): Unit = {
    lazy val res: Int = sum(1, 2)

    println("1. 函数调用")
    println("2. result = " + res)
  }
  def sum(a: Int, b: Int): Int = {
    println("3. sum call back")
    a + b
  }
}
