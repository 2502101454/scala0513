package com.atguigu.chapter05

/**
 *
 * @author zengwang
 * @create 2021-10-31 2:41 下午
 * @function:
 */
object Test01_FunctionAndMethod {
  // 在外边就是方法，这个是对象的方法
  def main(args: Array[String]): Unit = {
    //定义在里面，就是函数
    def sayHi(name: String): Unit = {
      println("hi," + name)
    }
    //优先调用最近的函数，如果没有定义函数的话，则调用外部的对象方法
    sayHi("alice")

    // 使用对象.fuc的方式显式的调用外部方法
    Test01_FunctionAndMethod.sayHi("Bob")
    // 函数不可以重载和重写，程序报错
//    def sayHi(): Unit = {} //编译error
  }

  // 定义对象的方法
  def sayHi(name: String): Unit = {
    println("Hi," + name)
  }
  // 方法可以进行重载和重写
  def main(): Unit = {

  }

}
