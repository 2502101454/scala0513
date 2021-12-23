package com.atguigu.chapter07

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-11-11 9:43 上午
 * @function:
 */
object Test09_MutableMap {
  def main(args: Array[String]): Unit = {
    // 1.创建map
    val map1: mutable.Map[String, Int] = mutable.Map("a" -> 1, "b" -> 3)
    println(map1)
    println(map1.getClass)

    // 2.添加元素，存在则覆盖
    map1.put("a", 2)
    println(map1)
    map1.put("c", 5)
    map1.put("d", 9)
    println(map1)
    map1 += (("e", 7), ("f", 2))
    println(map1)
    println("===============")
    // 3.访问元素
    println(map1("a")) // 不存在key就抛异常
    println(map1.getOrElse("w", 0))
    // 4. 删除
    map1.remove("a")
    map1 -= "b"
    println(map1)

    // 5.合并两个map
    val map2 = Map("aa" -> 1, "vv" -> 2)
    map1 ++= map2
    println(map1) // 修改map1
    println(map2)

    //6. contains判断是否key存在
    println(map1.contains("a"))

  }

}
