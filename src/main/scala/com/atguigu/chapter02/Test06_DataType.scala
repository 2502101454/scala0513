package com.atguigu.chapter02

import com.atguigu.chapter01.Student

/**
 *
 * @author zengwang
 * @create 2021-10-30 3:37 下午
 * @function:
 */
object Test06_DataType {
  def main(args: Array[String]): Unit = {

    // 5. 空类型
    // 5.1 空值Unit
    def sayOk(): Unit = {
      println("sayOk")
    }

    val a = sayOk() // a = ()
    println(a)

    // 5.2 空引用
//    val n: Int = null
    var student = new Student("alice", 25)
    student = null
    println(student)

    // Nothing
//    def m2(n: Int): Nothing = {
//      throw new NullPointerException("sssas")
//    }
//    var b = m2(1)
//    println(b)

    def m3(n: Int): Int = {
      if (n == 0)
        throw new NullPointerException("sssas")
      else
        return n
    }
//    var c = m3(0)
//    println(c)

    val s1: String = "Hello"
    val s2: String = new String("Hello")

    println(s1 == s2) // scala中 == 比较的是值, true
    println(s1.equals(s2)) // 使用java中的equals 比较值, true
    println(s1.eq(s2))  // scala中eq比较的是引用, False

    var i = 1
    println(i.+(2))
    println(1.34.*(2))

    if (i < 100){
      println()
    } else if(i < 120){
      println()
    } else {

    }
  }
}
