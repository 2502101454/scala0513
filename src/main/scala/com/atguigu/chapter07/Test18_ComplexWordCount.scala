package com.atguigu.chapter07

import scala.collection.mutable
import scala.collection.immutable
import scala.collection.immutable.Queue


/**
 *
 * @author zengwang
 * @create 2021-11-12 1:15 下午
 * @function:
 */
object Test18_ComplexWordCount {
  def main(args: Array[String]): Unit = {
    // 需求对于上面的每句话及该句话出现的次数，在进行word Count
    val stringList: List[(String, Int)] = List(
      ("hello", 1),
      ("hello Scala", 2),
      ("hello Spark", 2),
      ("Scala niubi", 3),
      ("choose Scala", 4),
      ("hello Spark niubi", 1)
    )

    val wordCount: List[(String, Int)] = stringList.flatMap((tuple: (String, Int)) => {
      // 拆分句子中的每个词，分配次数
      val _wordList = tuple._1.split(" ")
      val _wordCount: Array[(String, Int)] = _wordList.map((_, tuple._2))
      _wordCount
    })

    val wordCountGroup: Map[String, List[(String, Int)]] = wordCount.groupBy(_._1)
    println(wordCountGroup)
    // Map(Scala -> List((Scala,2), (Scala,3), (Scala,4)), niubi -> List((niubi,3), (niubi,1)), xxxxx)
    // 针对Map里面的kv整体做map
    val finalWordCount: Map[String, Int] = wordCountGroup.map((kv: (String, List[(String, Int)])) => {
      val key = kv._1
      val sameCountList: List[Int] = kv._2.map(_._2)
      (key, sameCountList.sum)
    })
    println(finalWordCount)
    // 优化: 只对v 做map即可，使用mapValues
    val finalWordCount2: Map[String, Int] = wordCountGroup.mapValues(tupleList => tupleList.map(_._2).sum)
    println(finalWordCount2)

    val queue: Queue[Int] = immutable.Queue[Int](1, 2, 3)
    println(queue) // Queue(1, 2, 3)
    println(queue.dequeue) // (1,Queue(2, 3))，注意这个返回有元素，还有新的队列
  }
}
