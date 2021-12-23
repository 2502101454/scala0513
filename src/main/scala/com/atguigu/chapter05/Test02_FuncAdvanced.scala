package com.atguigu.chapter05

/**
 *
 * @author zengwang
 * @create 2021-11-01 10:59 下午
 * @function:
 */
object Test02_FuncAdvanced {
  def main(args: Array[String]): Unit = {

    def func(i: Int): String => ( Char => Boolean ) = {
      def f1(s: String): Char => Boolean = {
        def f2(c: Char): Boolean = {
          if (i == 0 && s == "" && c == '0') false else true
        }
        f2
      }
      f1
    }
    // 正常来看，func函数返回f1就执行完了，然后回释放自己的局部变量i
    // 同理，f1返回f2后也就执行完了，会释放自己的局部变量s，
    // 但是最后f2中依然可以访问到 i 和 s变量，这就是闭包，
    println(func(0)("")('0'))
    println(func(0)("")('1'))
    println(func(23)("hello")('0'))

    def add(a: Int, b: Int): Int = {
      a + b
    }
    //拆成闭包写法
    def addByA(a: Int): Int => Int = {
      def addB(b: Int): Int = {
        a + b
      }
      addB
    }

    println(add(1, 2))
    println(addByA(1)(2))
    // 对add方法改为 柯里化的表达方式
    def addCurrying(a: Int)(b: Int): Int = {
      a + b
    }
    // 一旦用到柯里化的表达，底层一定是闭包，闭包不一定非用柯里化去写，但是推荐这么写
    println(addCurrying(1)(2))

    println("=======" * 5)
    // 1.传值参数, 就是普通的参数
    def f1(a: Int): Int = {
      println("f1 a is " + a)
      12
    }

    // 2.传名参数: 传递的不再是具体的值，而是代码块，代码块的最后一行代表整个代码块的返回
    // 传名参数把整个代码块的交给了参数，由参数后续使用时进行执行代码块
    // => Int 代表代码块没有输入，返回是Int类型
    def f2(a: => Int): Unit = {
      println("f2 a: " + a)
      println("f2 a: " + a)
    }
    // 代码块的调用，f2({xxxx})，花括号里如果只有一行代码，则花括号可以去掉，直接f2(xxx)
    // 下面都是代码块调用范例
    f2(23) // =f2({23})
    f2(f1(1)) // =f2({f(1)})
    // f(1)的调用执行是发生在  println("f2 a: " + a)这里，即 println("f2 a: " + f(1))
    println("---" * 5)
    f2({
      println("I am code Block, I will return Int")
      29
    })

  }


}
