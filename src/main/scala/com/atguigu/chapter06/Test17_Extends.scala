package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-08 12:43 下午
 * @function: 测试一些扩展内容
 */
object Test17_Extends {
  def main(args: Array[String]): Unit = {
    //1. 类型的检测和转换
    val student: Student17 = new Student17("alice", 18)
    student.study()
    student.sayHi()
    val person: Person17 = new Student17("bob", 20)
    person.sayHi()

    println("student is student17: " + student.isInstanceOf[Student17])
    println("student is Person17: " + student.isInstanceOf[Person17])
    println("person is Person17: " + person.isInstanceOf[Person17])
    println("person is student17: " + person.isInstanceOf[Student17]) // true 多态时的表现

    val person2: Person17 = new Person17("cary", 27)
    println("person2 is Student17: " + person2.isInstanceOf[Student17]) // false

    // 类型转换，isInstanceOf 是true的时候才可以
    if (person.isInstanceOf[Student17]) {
      val newStudent = person.asInstanceOf[Student17]
      newStudent.study()
    }

    // 打印类信息
    println(classOf[Student17])
    println(student.getClass)

    println(WorkDay.TUESDAY)
  }
}

class Person17(val name: String, val age: Int) {
  def sayHi(): Unit = {
    println("hi from person " + name)
  }
}

class Student17(name: String, age: Int) extends Person17(name, age) {
  override def sayHi(): Unit = {
    println("hi from student " + name)
  }

  def study(): Unit = {
    println("student study")
  }
}

// 定义枚举类对象
object WorkDay extends Enumeration {
  val MONDAY = Value(1, "monday")
  val TUESDAY = Value(2, "tuesday")
}

// 定义应用类对象, 不需要写main 可以直接调用
object TestApp extends App {
  println("app start")
  println(WorkDay.MONDAY) // monday
  // 使用 type 关键字可以定义新的数据数据类型名称，本质上就是类型的一个别名
  type MyString = String
  val a: MyString = "abc"
  println(a) //abc
}