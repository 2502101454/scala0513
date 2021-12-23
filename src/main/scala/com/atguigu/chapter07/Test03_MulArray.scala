package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-10 9:25 上午
 * @function 测试多维数组
 */
object Test03_MulArray {
  def main(args: Array[String]): Unit = {
    // 1.创建二维数组, 2行3列
    val array: Array[Array[Int]] = Array.ofDim[Int](2, 3)
    // 2. 访问元素
    array(0)(2) = 19
    array(1)(0) = 25

    for (i <- 0 until array.length; j <- 0 until array(i).length) {
      println(array(i)(j))
    }
    for (i <- array.indices; j <- array(i).indices) {
      print(array(i)(j) + "\t")
      if (j == array(i).length - 1) println()
    }

    println("=============")
    array.foreach(line => line.foreach(println))
    println("=============")
    array.foreach(_.foreach(println))
  }
}
