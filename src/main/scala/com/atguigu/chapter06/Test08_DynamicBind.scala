package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-06 4:20 下午
 * @function:
 */
object Test08_DynamicBind {
  def main(args: Array[String]): Unit = {
    val student: Person08 = new Student08
    println(student.name)
    student.hello()
  }
}

class Person08 {
  val name: String = "person"
  def hello(): Unit = {
    println("hello person")
  }
}

class Student08 extends Person08 {
  override val name: String = "Student"

  override def hello(): Unit = {
    println("hello Student")
  }

}