package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-11 1:13 下午
 * @function: 测试衍生集合
 */
object Test12_DerivedCollection {
  def main(args: Array[String]): Unit = {
    val list1 = List(1, 2, 3, 4)
    val list2 = List(11, 22, 33)

    // (1) 获取集合的头
    println(list1.head)
    // (2) 获取集合的尾，返回除了头部元素之外，剩下的元素组成的新的集合
    println(list1.tail) // List(2, 3, 4)
    // (3) 获取集合的最后一个元素
    println(list1.last)
    // (4) 集合初始数据, 返回除了最后一个元素之外，剩下元素组成的新集合
    println(list2.init) // List(11, 22)
    // (5) 集合的反转
    println(list1.reverse)
    // (6) 取前(后)n个元素
    println(list1.take(3))
    println(list1.takeRight(2))
    // (7) 去掉前(后) n个元素
    println(list1.drop(3))
    println(list1.dropRight(2))
    // 上面的操作只对list这种有顺序的集合才有意义

    // (8) 并集, 是list则不会去重，是set会去重的
    println(list1.union(list2))
    val set1 = Set(1, 2, 3)
    val set2 = Set(11, 2, 4)
    println(set1.union(set2)) // 和两个set的 ++ 操作一样

    // (9) 交集
    val intersection: List[Int] = list1.intersect(list2)
    println("list intersection" + intersection)
    // (10) 差集
    val diff1 = list1.diff(list2)
    println(diff1)

    // (11) 拉链，组成二元组,python的zip
    println(list1.zip(list2)) // List((1,11), (2,22), (3,33))
    println("---------")
    // (12) 滑窗，开一个窗口,指定大小，框起来元素又是一个list， sliding返回一个迭代器
    val list3 = List(1, 2, 3, 4, 5, 6, 7)
    for (elem <- list3.sliding(3)) println(elem)
    // 设置滑窗步长为4，窗口大小为3
    for (elem <- list3.sliding(3, 4)) println(elem)
    // 设置滑窗步长为3，窗口大小为3，这种步长和大小相同的窗口，常称为滚动窗 口
    for (elem <- list3.sliding(3, 3)) println(elem)
  }
}
