package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-12 9:11 上午
 * @function:
 */
object Test17_CommonWordCount2 {
  def main(args: Array[String]): Unit = {
    val stringList: List[String] = List(
      "hello",
      "hello Scala",
      "hello Spark",
      "Scala niubi",
      "choose Scala",
      "hello Spark niubi"
    )
    // 上一个版本搞的有点复杂，这里是教程的版本
    val wordsList: List[Array[String]] = stringList.map(_.split(" "))
    val words: List[String] = wordsList.flatten
    // 注意对于只有一个参数传入，直接返回这个参数自身的，匿名函数，不允许直接写 _ ，编译器是不认识的
//    words.groupBy(_).var
    // 巧妙的思路！
    val wordGroup: Map[String, List[String]] = words.groupBy(word => word)
    val wordCount: Map[String, Int] = wordGroup.map(kv => (kv._1, kv._2.length))

    // 按照count降序，排序取前三
    val list: List[(String, Int)] = wordCount.toList
    println(list)
    val sortList: List[(String, Int)] = list.sortWith(_._2 > _._2)
    println(sortList.take(3))
  }
}
