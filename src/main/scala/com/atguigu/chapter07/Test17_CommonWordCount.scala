package com.atguigu.chapter07


/**
 *
 * @author zengwang
 * @create 2021-11-12 9:11 上午
 * @function:
 */
object Test17_CommonWordCount {
  def main(args: Array[String]): Unit = {
    val stringList: List[String] = List(
      "hello",
      "hello Scala",
      "hello Spark",
      "Scala niubi",
      "choose Scala",
      "hello Spark niubi"
    )
    val words: List[String] = stringList.flatMap(_.split(" "))
    val wordTuples: List[(String, Int)] = words.map((_, 1))
    // 按照tuple中的第一个元素分组
    val groupWords: Map[String, List[(String, Int)]] = wordTuples.groupBy(_._1)
    val wordCount = groupWords.map(
      (mapKV: (String, List[(String, Int)])) => {
        // 对每一个mapKV进行操作，返回一个新的mapKV
        val key = mapKV._1
        val value: List[(String, Int)] = mapKV._2
//        println(value)
        //
        /**
         * val count: Int = value.reduce(_.productElement(1) + _.productElement(1))
         * 不行，请注意，当前聚合返回Int，那么下一次聚合就是这个Int和下一个tuple做参数，
         * 代入匿名函数参数列表，自然是不行的。
         * 所以再map一次
         */
        val value2 = value.map(_._2)
        (key, value2.sum)
      }
    )
    wordCount.foreach(println)
    // 排序
    val listB: List[(String, Int)] = wordCount.toList
    val listC: List[(String, Int)] = listB.sortWith(_._2 > _._2)
    // 取前三
    println(listC.take(3))
  }
}
