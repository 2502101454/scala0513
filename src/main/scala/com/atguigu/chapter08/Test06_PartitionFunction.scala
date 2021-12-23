package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-13 11:09 上午
 * @function:
 */
object Test06_PartitionFunction {
  def main(args: Array[String]): Unit = {
    val list = List(("a", 1), ("b", 2), ("c", 3))
    // 1.map转换 实现key不变，value 2倍
    val newList = list.map( tuple => (tuple._1, tuple._2 * 2))
    // 2. 用模式匹配，对元素元素赋值，实现功能
    val newList2 = list.map(
      tuple => {
        tuple match {
          case (word, count) => (word, count * 2)
        }
      }
    )
    // 3. 偏函数写法， 省略lambda表达式的写法，进行简化
    val newList3 = list.map {
          case (word, count) => (word, count * 2)
      }
    println(newList)
    println(newList2)
    println(newList3)

    // 偏函数的应用，求绝对值
    // 对输入数据分为不同的情形: 正、负、0
    // [Int(输入数据类型), Int(返回的数据类型)]
    val positiveAbs: PartialFunction[Int, Int] = {
      case x if x > 0 => x
    }
    val negativeAbs: PartialFunction[Int, Int] = {
      case x if x < 0 => -x
    }
    val zeroAbs: PartialFunction[Int, Int] = {
      case 0 => 0
    }
    // 偏函数，就是部分函数实现。上面定义了三个偏函数，下面就是应用他们给一个普通函数
    def abs(x: Int): Int = (positiveAbs orElse negativeAbs orElse zeroAbs) (x)
    println(abs(-6))
    println(abs(6))
    println(abs(0))
  }
}
