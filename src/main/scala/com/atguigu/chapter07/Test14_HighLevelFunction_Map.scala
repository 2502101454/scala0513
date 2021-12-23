package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-11 10:29 下午
 * @function:
 */
object Test14_HighLevelFunction_Map {
  def main(args: Array[String]): Unit = {
    val list  = List(1, 2, 3, 4, 5)
    // 1. 过滤
    // 选取偶数
    val eventList: List[Int] = list.filter(_ % 2 == 0)
    println(eventList)

    // 2. map
    // 把集合中每个数乘以2
    val list2: List[Int] = list.map(_ * 2)
    println(list2)

    // 3.扁平化
    val nestedList: List[List[Int]] = List(List(1,2), List(3, 4))
    val flatList = nestedList.flatten
    println(flatList) // List(1, 2, 3, 4)

    // 4.扁平化 + 映射，flatMap先进行map操作，再进行flattn操作
    val wordList = List("Hello World", "Hello fighting")
    // 每个元素都变成了list，然后对每个元素进行flaten
    val words = wordList.flatMap(_.split(" "))
    println(words)

    // 5. 分组groupBy, 指定的func的返回值做为分组的组名，
    // func对应的输入元素是当前组的值, 返回一个Map类型，k是组名，v是当前组的元素组成list
    // 给定一组词汇，按照单词的首字母进行分组
    val wordList1 = List("china", "clice", "love", "wangzeng")
    val wordGroups: Map[Char, List[String]] = wordList1.groupBy((word: String) => {
      word.charAt(0)
    })
    println(wordGroups)
    println(wordList1.groupBy(_.charAt(0)))
  }
}
