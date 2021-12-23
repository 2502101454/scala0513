package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-06 5:24 下午
 * @function:
 */
object Test10_AnonymousClass {
  def main(args: Array[String]): Unit = {
    val person: Person10 = new Person10 {
      override var name: String = "alice"

      override def eat(): Unit = println("person eat")
    }
    println(person.name)
    person.eat()
  }
}
// 匿名子类在java中都是针对抽象类和接口使用的，在Scala中也不例外
abstract class Person10{
  var name: String
  def eat(): Unit
}