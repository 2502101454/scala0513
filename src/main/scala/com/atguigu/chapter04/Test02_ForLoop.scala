package com.atguigu.chapter04

import scala.collection.immutable

/**
 *
 * @author zengwang
 * @create 2021-10-30 9:07 下午
 * @function:
 */
object Test02_ForLoop {
  def main(args: Array[String]): Unit = {
    //1. 范围遍历，两头闭区间[1, 10]
    for (i <- 1 to 10){
      println(i + ". hello world")
    }
//    for (i <- 1.to(10)){
//      println(i + ". hello world")
//    }

    for (i <- 1 until 10){
      println(i + ". hello world")
    }

    for (i <- 1 to 3 if i % 2 == 0){
      println(s"i is ${i}")
    }

    for (i <- 1 to 10 by 3){
      println(s"i is ${i}")
    }

    for (i <- 1 to 9; j <- i to 9){
      println(s"${i}x${j}=${i * j}")
    }

    for {i <- 1 to 3
         j = i + 1
    } {
      println(s"$i and $j")
    }

    val ints: immutable.IndexedSeq[Int] = for (i <- 1 to 3) yield i * i
    println(ints)
    for (i <- 1 to 3 reverse){
      println(s"$i")
    }
    println("===========")
    // range 和python的用法基本一样，左闭右开，[1, 4) ，需要倒转的时候调用reverse即可
    for (i <- Range(1, 4).reverse) {
      println(i)
    }
  }
}
