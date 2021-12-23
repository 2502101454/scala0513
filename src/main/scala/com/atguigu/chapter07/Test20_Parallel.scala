package com.atguigu.chapter07

import scala.collection.immutable
import scala.collection.parallel.immutable.ParSeq

/**
 *
 * @author zengwang
 * @create 2021-11-12 3:17 下午
 * @function:
 */
object Test20_Parallel {
  def main(args: Array[String]): Unit = {
    // 串行集合得到的Id都一样
    val result: immutable.IndexedSeq[Long] = (1 to 100).map(
      x => Thread.currentThread().getId
    )
    println(result) //Vector(1, 1, 1,....) 都是1

    // 并行集合，集合后面加一个 .par 即可。多个线程参与计算, 线程id不一样
    val longs: ParSeq[Long] = (1 to 100).par.map(
      x => Thread.currentThread().getId
    )
    println(longs) //ParVector(11, 11, 11, 21, 21,...)
  }

}
