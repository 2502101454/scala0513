package com.zeng.dsa.string

import scala.util.control.Breaks


/**
 *
 * @author zengwang
 * @create 2021-11-20 8:28 下午
 * @function:
 */
object StringMatch01 {
  def main(args: Array[String]): Unit = {
//    val res = bf("12134512345", "123")
    val res = bf("15", "123")
    println(res)
  }

  /**
   * 字符串BF 暴力匹配算法
   * @param mainString 主串
   * @param regString 模式串
   * @return 在mainString中查找regString,找到返回true, 否则false
   */
  def bf(mainString: String, regString: String): Boolean = {
    val mainChars: Array[Char] = mainString.toArray
    val regChars: Array[Char] = regString.toArray
    // 设主串长度为n，模式串长度为m, 只需要从主串第1 到 第(n - m + 1)元素位置可作为匹配起点
    // 转为下标（统一减去一）则是0 到 (n - m)

    // 多重循环，如果Breaks加在最外边，那么内层的循环一旦break，则会整个循环跳出
    for (i <- 0 to mainChars.length - regChars.length) {
      Breaks.breakable(
        for (j <- regChars.indices) {
          val mainPoi = i + j
          if (mainChars(mainPoi) != regChars(j)) Breaks.break()

          if (j == regChars.length - 1) return true
        }
      )
    }

//    println(regChars.mkString("="))
    false
  }
}
