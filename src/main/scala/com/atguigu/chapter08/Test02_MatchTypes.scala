package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-12 4:42 下午
 * @function:
 */
object Test02_MatchTypes {
  def main(args: Array[String]): Unit = {
    // 1.匹配常量
    def describeConst(x: Any): String = x match {
      case 1 => "Int one"
      case "hello" => "String hello"
      case true => "Boolean true"
      case '+' => "char +"
      case _ => ""
    }
    println(describeConst("hello"))
    println(describeConst(1)) //Int one
    println(describeConst(true))
    println(describeConst(1.0)) // Int one
    println(describeConst(2.0))
    println("=============")

    // 2.匹配类型
    def describeType(x: Any): String = x match {
      case i: Int => "Int" + i
      case s: String => "String " + s
      // List在jvm底层存在泛型擦除，只能判断到是一个List，无法判断内部的元素类型
      case list: List[String] => "List " + list
      // Array不存在泛型擦除
      case array: Array[Int] => "Array[Int]" + array.mkString(",")
      case a => "Something else " + a
    }

    println(describeType(3))
    println(describeType("hello"))
    println(describeType(List(1, 2, 3))) // List List(1, 2, 3)
    println(describeType(List("a", "c"))) // List List(a, c)
    println(describeType(Array(1, 2, 3))) // Array[Int]1,2,3
    println(describeType(Array("a", "c"))) // Something else [Ljava.lang.String;@7c75222b
    println("===================")
    // 3.匹配数组
    for (arr <- List(
      Array(1),
      Array(1, 0),
      Array(0, 1),
      Array(0, 1, 2),
      Array(2, 3, 4, 5),
      Array("any", 1, 0)
    )) {
      val result = arr match {
        // 匹配指定值的数组
        case Array(0) => "0"
        case Array(1, 0) => "Array(1, 0)"
        // 匹配指定元素个数的数组, x y代表两个元素的
        case Array(x, y) => "Array: " + x + ", " + y
        // _* 表示后面元素任意个 _是通配符
        case Array(0, _*) => "以0开头的数组"
        case Array(x, 1, z) => "中间为1的三元数组"
        case _ => "something else"
      }
      println(result)
    }
    println("=============")
    // 4.匹配list
    for (list <- List(
      List(0),
      List(1, 0),
      List(0, 0, 0),
      List(1, 1, 0),
      List(88),
      List("hello"),
    )) {
      // 匹配规则和上述的Array一样
      val result = list match {
        case List(0) => "0"
        case List(x, y) => "List(x, y): " + x + "," + y
        case List(0, _*) => "List(0, ....)"
        case List(a) => "List(a): " + a
        case _ => "something else"
      }
      println(result)
    }
    println("==========")
    // 方式二
    val list = List(1, 2, 3, 4, 5) // first: 1, second: 2, rest: List(3, 4, 5)
    val list1 = List(1, 0) // first: 1, second: 0, rest: List()
    val list2 = List(1) // something else
    list match {
      // 对数组进行切分为三部分 first second 是第一第二个元素，rest是其余剩下的list, rest可以是空的
      // :: 这个元素符是函数调用，取一个List的head 和 tail，这里两次:: 调用了两次
      case first :: second :: rest => println(s"first: $first, second: $second, rest: $rest")
      // 对元素不够匹配这里
      case _ => println("something else")
    }
    println("==============")
    // 5.匹配元组
    for (tuple <- List(
        (0, 1),
        (0, 0),
        (0, 1, 0),
        (0, 1, 1),
        (1, 23, 56),
        ("hello", true, 0.5)
    )) {
      val result = tuple match {
        // 匹配二元组
        case (a, b) => "" + a + ", " + b
        // 第一个元素是0 第二个元素任意
        case (0, _) => "(0, _)"
        case (a, 1, _) => "(a, 1, _)" + a
        case (x, y, z) => "(x, y, z)"
        case _ => "something else"
      }
      println(result)
    }

  }

}
