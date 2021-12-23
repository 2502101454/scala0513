package com.atguigu.chapter07


/**
 *
 * @author zengwang
 * @create 2021-11-11 1:05 下午
 * @function: 集合的公共操作
 */
object Test11_CommonOp {
  def main(args: Array[String]): Unit = {
    val list = List(1, 2, 3)
    val set = Set(1, 2, 3)
    // (1)获取集合长度
    println(list.length)
    println(list.size)
    // (2)获取集合大小
    println(set.size)
    // (3)循环遍历
    for (elem <- list) println(elem)
    println("=========")
    for (elem <- set) println(elem)
    // (4)迭代器
    for (elem <- list.iterator) println(elem)
    set.iterator
    // (5)生成字符串
    println(list.mkString("--"))
    // (6)是否包含
    println(list.contains(1))
    println(set.contains(12))

    // new by wz: 集合切片,已知Array、list、String都支持
//    set.slice()  //set也有这个api，什么鬼。。
    var list2 = List(1,2,3,4,2,36,7)
    // 和python一样，末尾下标是不包括的，[start, end)，支持首、尾下标越界
    println(list2.slice(0, 3)) //List(1, 2, 3)
    println(list2.slice(1, 20)) // List(2, 3, 4, 2, 36, 7)
    println(list2.slice(-1, 20)) // List(1, 2, 3, 4, 2, 36, 7)
    // 但是不支持倒着切
    println(list2.slice(-5, -1)) // List(2, 3, 4, 2, 36, 7)

  }
}
