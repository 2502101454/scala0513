package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-10 10:46 下午
 * @function:
 */
object Test06_ImmutableSet {
  def main(args: Array[String]): Unit = {
    // 1.创建Set
    val set1 = Set(1, 2, 1, 3, 2)
    println(set1)
    // 2.添加元素，set无序的，加进去的元素顺序也是无序的
    val set2: Set[Int] = set1 + 20
    println(set1)
    println(set2)

    // 3.合并set
    val set3 = Set(1, 5, 6)
    val set4 = set3 ++ set1
    println(set4)

    //删除元素
    val set5 = set1 - 1
    println(set5)


  }
}
