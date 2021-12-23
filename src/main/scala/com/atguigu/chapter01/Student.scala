package com.atguigu.chapter01

/**
 *
 * @author zengwang
 *
 * @create 2021-09-30 4:32 下午
 *
 */
class Student(name: String, age: Int) {
  def printInfo(): Unit = {
    println(name + " " + age + " " + Student.school)
  }
}

// 引入伴生对象
object Student {
  val school: String = "atguigu"

  def main(args: Array[String]): Unit = {
    // 使用的是class Student做的实例化
    val alice = new Student("alice", 20)
    alice.printInfo()
    var name = "ming"
    var age = 20
    println(s"${name} age is ${age}")

    var sql: String = s"""
       |select *
       |from
       |  student
       |where
       |  name = ${name}
       |and
       |  age > ${age}
       |""".stripMargin
    println(sql)
  }
}