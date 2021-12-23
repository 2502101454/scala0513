package com.atguigu.chapter07

import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2021-11-10 1:26 下午
 * @function:
 */
object Test05_ListBuffer {
  def main(args: Array[String]): Unit = {
    // 1. 创建可变列表
    val list1: ListBuffer[Int] = new ListBuffer[Int]()
    val list2 = ListBuffer(12, 53, 75)
    println(list1)
    println(list2)
    println("==============")

    // 2.添加元素
    list1.append(1, 2, 3)
    list2.prepend(4, 5)
    list1.insert(1, 19, 22)

    println(list1)
    println(list2)
    println("===============")
    // 和ArrayBuffer一样，+=： 是给头list头部加，+=是给尾部加
    21 +=: 96 +=: list1 += 25 += 11
    println(list1)

    // 3. 合并list， ++ 产生一个新的list
    val list3 = list1 ++ list2
    println(list1)
    println(list2)
    println("------------")
    println(list3)
    println("==============")
    // 合并list，修改其中一个list
    // ++= 修改list1，给list1结尾追加list2
    list1 ++= list2
    println(list1)
    println(list2)
    println("=================")
    // ++=:  这个: 相当于是list2在调用，改变list2
    // 结果还是list1在前list2在后，不加:是改变list1，加:是改变list2
    list1 ++=: list2
    println(list1)
    println(list2)
    // 调方法更舒服
//    list2.appendAll()
//    list2.prependAll()

    // 4. 修改元素,两种方法
    list2(3) = 30
    // 更新下标0的元素
    list2.update(0, 89)
    println(list2)

    // 5.删除元素
    list2.remove(2)
    // 指定删除某个值的元素, 删除第一次出现的元素
    list2 -= 4
    println(list2)
  }
}
