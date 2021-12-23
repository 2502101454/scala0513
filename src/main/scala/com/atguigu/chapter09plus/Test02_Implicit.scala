package com.atguigu.chapter09plus

/**
 *
 * @author zengwang
 * @create 2021-11-13 11:56 上午
 * @function:
 */
object Test02_Implicit {
  def main(args: Array[String]): Unit = {
    val new12 = new MyRichInt(12)
    println(new12.myMax(14))
    // 使用 implicit 关键字声明的函数称之为隐式函数
    implicit def myConvert(arg: Int): MyRichInt = {
      new MyRichInt(arg)
    }
    //当想调用对象功能时，如果编译错误，那么编译器会尝试在当前作用域范
    //围内查找能调用对应功能的转换规则，这个调用过程是由编译器完成的，所以称之为隐
    //式转换。也称之为自动转换
    println(12.myMax(11))
    println("====================")
    // 2. 隐式类，
    // (1)其所带的构造参数有且只能有一个
    // (2)隐式类必须被定义在“类”或“伴生对象”或“包对象”里，即隐式类不能是顶级的(即放在外边)。
    implicit class MyRichInt2(val self: Int) {
      def myMax2(i: Int): Int = (
        if (self < i) i else self
        )
      def myMin2(i: Int): Int = (
        if (self >= i) i else self
        )
    }
    println(1.myMin2(3))
    println(2.myMax2(3))

    println("===================")
    // 3.隐式参数
    // 上下文找的是相同的类型，而不是变量名，
    implicit val str: String = "alice"
    // 所以相同类型的隐式变量只能有一个
//    implicit val str2: String = "alice"
    def sayHello(implicit name: String): Unit = {
      println("hello, " + name)
    }
    sayHello
    // 这里是函数柯里化那里的方式，其实上面的隐式参数的函数底层是
    // sayHello()(implicit name: String)
    // 隐式值的优先级更高，会覆盖掉默认参数
    def sayHi(implicit name: String = "atguigu"): Unit = {
      println("hi, " + name)
    }
    sayHi

    implicit val age: Int = 18
    // 简便写法，不用写参数
    def hiAge(): Unit = {
      // 想要拿到一个int类型的隐式变量
      println("hi, " + implicitly[Int])
    }
    hiAge()


  }
}
class MyRichInt(val self: Int) {
  def myMax(i: Int): Int = (
    if (self < i) i else self
  )
  def myMin(i: Int): Int = (
    if (self >= i) i else self
  )
}
