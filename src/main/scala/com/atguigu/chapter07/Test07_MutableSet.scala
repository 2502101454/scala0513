package com.atguigu.chapter07

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-11-10 10:58 下午
 * @function:
 */
object Test07_MutableSet {
  def main(args: Array[String]): Unit = {
    // 1.创建set
    val set1: mutable.Set[Int] = mutable.Set(1, 1, 2, 4)
    println(set1)

    // 2.添加元素
    set1 += 3
    println(set1)
    // add方法返回是否添加成功，集合没有这个元素的时候，才会添加成功返回true
    val flag: Boolean = set1.add(5)
    println(flag)
    println(set1)
    val flag2: Boolean = set1.add(5)
    println(flag2)
    println("============")

    // 3.删除元素
    set1 -= 3
    println(set1)
    // remove 返回删除是否成功
    val flag3: Boolean = set1.remove(5)
    println(flag3)
    println(set1)
    val flag4: Boolean = set1.remove(5)
    println(flag4)

    // 4.合并两个Set
    val set2 = mutable.Set(5, 6, 7)
    // set1变，set2不变
    set1 ++= set2
    println(set1)
    println(set2)
  }
}
