package com.atguigu.chapter05

import java.rmi.StubNotFoundException

/**
 *
 * @author zengwang
 * @create 2021-10-31 3:29 下午
 * @function:
 */
object Test01_Func_param {
  def main(args: Array[String]): Unit = {
    def test0(a: Int): Unit = {
//      a = 1 //error, 函数参数是不可变的，不能当变量赋值
    }

    def test(s: String*): Unit = {
      println(s)
    }

    // 可变参数和默认参数的配合是不支持的
//    def test2(sex: String="1111", nickanme: String*): Unit = {
//      println(sex)
//      println(nickanme)
//    }
//    def test3(nickanme: String*, sex: String="1111"): Unit = {
//      println(sex)
//      println(nickanme)
//    }
//    test2("nbob", "ssa", "niaq")
//    test2("nbob")
//    test2(sex = 2, "nbob", "ssa", "niaq")
//    test2(nickname="nbob", "ssa", "niaq")

    def test4() {
      println("sss")
    }

    // 定义普通的函数calculator，参数中，op: (Int, Int) => Int
    // 这里指出了op这个参数类型是一个函数，接受两个Int输入，输出是Int ('=>'右边)
    def calculator(a: Int, b: Int, op: (Int, Int) => Int): Int = {
      op(a, b)
    }

    // (1) 标准版匿名函数
    println(calculator(1, 2, (x: Int, y: Int) => { x + y}))
    // (2) 如果只有一行，则花括号可省略
    println(calculator(1, 2, (x: Int, y: Int) =>  x + y))
    // (3) 参数的类型也可以省略，会根据形参自动推导
    println(calculator(1, 2, (x, y) =>  x + y))
    // 如果参数只出现一次，则参数省略且方法体中的参数变量可以用_表示
    println(calculator(1, 2, _ + _))
    println(calculator(1, 2, _ - _))
    // 下划线是可以带其他操作的
    println(calculator(1, 2, 2 * _ + _ * 2))

    def foo(): Int = {
      1
    }
    // 把函数赋值给变量
    //方式1: 需要在末尾加 _
    var f1 = foo _
    println(f1())
    //方式2: 指定变量类型则不用加 _
    var f2: () => Int = foo
    println(f2())

    // 将函数作为返回
    def bar() = {
      def inside() = {
        2
      }
      // 方式1：如果外层bar不指定返回值类型，则需要加 _
      inside _
    }
    println(bar()())
    def bar2(): () => Int = {
      def inside() = {
        2
      }
      // 方式2：如果外层bar2指定返回值类型，则不需要加 _
      inside
    }
    println(bar2()())
  }
}


/**
 * 匿名函数参数使用补充
 */
object DemoMore {
  def main(args: Array[String]): Unit = {
    val array: Array[Array[Int]] = Array.ofDim(2, 3)
    //error：'_'只可以用作匿名函数至简后的表达，不可以用做未简化时的参数名
    //array.foreach(_ => {println(_.mkString)})
    array.foreach(line => {println(line.mkString)})

    // error: 把 _.mkString("=") 整体当做'_'，所以这里无法解析的
//    array.foreach(println(_.mkString("=")))
    // error: 把 bar(_) 整体当做'_'
//    array.foreach(println(bar(_)))
    array.foreach(bar(_))
    array.foreach(println(_))

    /**
     * 结论：
     * 1. _ 只可用于匿名函数简化后的表达，不可以用做未简化时的参数名
     * 2. 匿名函数至简原则时，注意函数的嵌套使用时，要表达的参数'_' 到底代表的啥
     *
     * 开发中，实在不行就不简化了，多写点就行
     */
  }

  def bar(array: Array[Int]): Int = {
    array.sum
  }

}
