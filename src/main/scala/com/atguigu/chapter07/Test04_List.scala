package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-10 12:36 下午
 * @function
 */
object Test04_List {
  def main(args: Array[String]): Unit = {
    //1. 创建一个List
    val list1 = List(23, 65, 87)
    println(list1)

    // 2. 访问和遍历元素
    println(list1(2))
    // list1(2) = 1 // error，可以使用下标读但是不可以修改
    // 增强for循环、迭代器、foreach都可以遍历
    for (elem <- list1) println(elem)
    list1.foreach(println)

    // 3. 添加元素，基本和Array一样
    val list2: List[Int] = list1.+:(10)
    // 简写
    val list3 = 10 +: list1 :+ 23
    println(list2)
    println(list3)

    println("=============")
    // 在列表的头部添加
    val list4 = list1.::(1)
    println(list4)
    // 常用: 使用空的List来创建一个list //Nil就是空的List
    // 在Nil的头部添加元素
    val list5 = Nil.::(13)
    println(list5)
    // 在Nil的头部添加元素，等价于 元素 :: Nil
    val list6 = 32 :: Nil
    println(list6)
    // 常用, 从后向前，不断给当前列表头部添加元素
    val list7 = 1 :: 2 :: 3 :: 4 :: Nil
    println(list7)

    // 和并列表
    // 在list 7的头部添加打平的数组list6
    val list8 = list6 ::: list7
    println(list8)
    val list9 = list6 ++ list7
    println(list9)

    // 排序 sortWith + 匿名函数， sortWith返回一个新的集合
    // val listC: List[(String, Int)] = listB.sortWith(_._2 > _._2)
    val list10 = List(2,5,1,5,6,3)
    // x < y ，从小到大，结果就是正序
    val ints: List[Int] = list10.sortWith(_ < _)
    println(ints)
    // x > y, 从大到小，倒序
    val ints1: List[Int] = list10.sortWith(_ > _)
    println(ints1)

    // 其他结构的元素排序，也支持对象排序，匿名函数取属性即可
    val list11 = List(("a", 10), ("b", 20), ("c", 16))
    val tuples: List[(String, Int)] = list11.sortWith(_._2 < _._2)
    println(tuples)

  }
}
