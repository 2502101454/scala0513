package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-06 4:46 下午
 * @function:
 */
object Test09_AbstractClass{
  def main(args: Array[String]): Unit = {
    val student = new Student9
    student.eat()
    student.sleep()
  }
}

abstract class Person09{
  // 非抽象属性
  val name: String = "person"
  // 非抽象方法
  def eat(): Unit = {
    println("person eat")
  }

  //抽象属性
  var age: Int
  //抽象方法
  def sleep(): Unit
}

class Student9 extends Person09 {
  //实现抽象属性和方法
  var age: Int = 18

  // 实现抽象方法不写override也可以的
//  override def sleep(): Unit = ???
  def sleep(): Unit = {
    println("Student sleep")
  }

  // 重写非抽象的属性和方法, 注意只能重写非抽象的常量，[因为变量重新赋值就可以使用]
  override val name: String = "student"
  //访问父类的属性变量，重新赋值
//  name = "student"

  override def eat(): Unit = {
    super.eat()
    println("student eat")
  }
}