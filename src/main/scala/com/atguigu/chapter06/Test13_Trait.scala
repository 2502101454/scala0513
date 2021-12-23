package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 4:06 下午
 * @function:
 */
object Test13_Trait {
  def main(args: Array[String]): Unit = {
    val student = new Student13
    student.sayHello()
    student.study()
    student.dating()
    student.play()
  }
}

// 定义一个父类
class Person13 {
  val name: String = "person"
  var age: Int = 18
  def sayHello(): Unit = {
    println("hello from: " + name)
  }
}
// 特质，和前面讲的抽象类十分类似，使用的时候一个类可以有多个特质
trait Young {
  // 声明抽象和非抽象的属性
  var age: Int
  val name: String = "young"

  // 声明抽象和非抽象的方法
  def dating(): Unit

  def play(): Unit = {
    println(s"young people $name is playing")
  }

//  def sports(): Unit
}

class Student13 extends Person13 with Young {
  // 父类和特质中都有一个name属性，运行会error，不知道该用哪个name
  // 重写冲突的属性
  override val name: String = "student"

  // 实现抽象方法
  def dating(): Unit = {
    println(s"student $name is dating")
  }
  // 声明自己的方法
  def study(): Unit = {
    println(s"student $name is studying")
  }

  // 重写父类的方法
  override def sayHello(): Unit = {
    // 这里使用super调用的时候，父类sayHello中的name属性也会因为动态绑定变成student
    super.sayHello()
    println(s"hello from: student $name")
  }
}