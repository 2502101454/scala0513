package com.atguigu.chapter02

import java.io.{File, PrintWriter}
import scala.io.Source

/**
 *
 * @author zengwang
 * @create 2021-10-29 12:46 下午
 * @function:
 */
object Test06_FileIO {
  def main(args: Array[String]): Unit = {
    // 1. 此文件中读取数据，但是是一个字符一个字符的读取
    Source.fromFile("src/main/resources/test.txt").foreach(print)

    // 2.将数据写入文件，Scala没有提供，所以使用Java的文件流
    val writer = new PrintWriter(new File("src/main/resources/output.txt"))
    writer.write("Hello Scala from java writer")
    writer.close()

  }
}
