package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-11 8:31 下午
 * @function:
 */
object Test13_SimpleFunction {
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 4, 5, 3)
    val list2 = List(("a", 1), ("b", 2), ("c", 3), ("d", 0))
    // (1)求和
    println(list1.sum)
    // (2)求乘积
    println(list1.product)
    // (3)最大值
    println(list1.max)
//    println(list2.maxBy((line: (String, Int)) => line._2))
    println(list2.maxBy(_._2))
    // (4)最小值
    println(list1.min)
    println(list2.minBy(_._2))
    // (5)排序
    // 从小到大，正序
    println(list1.sorted)
    // 从大到小，倒序
    println(list1.sorted(Ordering[Int].reverse))
    // 默认按照第一个元素排序
    println(list2.sorted)
    // 5.2 sortBy
    println(list2.sortBy(_._2))
    println(list2.sortBy(_._2)(Ordering[Int].reverse))

    // 5.3 常用sortWith ，类似于java的comparetor
    // a < b 返回true，就不用交换，false进行交换，所以是升序
    println(list1.sortWith((a: Int, b: Int) => {a < b}))
    println(list1.sortWith( _ < _ ))
    // 降序
    println(list2.sortWith( _._2 > _._2))

  }
}
