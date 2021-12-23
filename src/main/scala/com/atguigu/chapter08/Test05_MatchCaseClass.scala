package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-13 10:58 上午
 * @function:
 */
object Test05_MatchCaseClass {
  def main(args: Array[String]): Unit = {
    val alice: Student1 = Student1("alice1", 18)
    val result = alice match {
      case Student1("alice", 18) => "alice 18"
      case _ => "Else"
    }
    println(result)
  }

}

// 定义样例类
case class Student1(name: String, age: Int)