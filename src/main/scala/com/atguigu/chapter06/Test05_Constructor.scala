package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-05 9:18 上午
 * @function:
 */
object Test05_Constructor {
  def main(args: Array[String]): Unit = {
//  val student = new Student1()
    val student = new Student1  // new对象时，主构造器无参数的话，也可以不带()
    student.Student1()

    // 调用辅助构造器创建对象
    val alice = new Student1(name = "Alice")
    val bob = new Student1("Bob", 26)
  }
}

// 定义类时，主构造器无参数的话，也可以不带()
class Student1() {
  // 定义属性
  var name: String = _
  var age: Int = _

  // 属性可以定义在主构造器方法参数里面，这样这里就不用定义属性了

  println("1. 主构造方法被调用")
  // 上面的代码块就是主构造器的方法体内容

  //声明辅助构造方法
  def this(name: String) {
    this()  // 直接调用主构造器,使用this关键字，主构造器方法没有参数
    println("2. 辅助构造方法一被调用")
    this.name = name
    println(s"name: $name age $age")
  }

//  def this(nickname: String) {
//    this()
//    println("4.辅助构造器胡闹")
//  }
//
//  def this(age: Int) {
//    this()
//    println("4.辅助构造器胡闹")
//  }

  // 可以声明多个辅助构造器
  def this(name: String, age: Int) {
    // 调用辅助构造器
    this(name)
    println("3. 辅助构造器方法三被调用")
    this.age = age
    println(s"name: $name age $age")
  }

  // Scala中可以定义一个和类名相同的方法，这并不是构造器！
  def Student1(): Unit = {
    println("普通方法被调用")
  }
}