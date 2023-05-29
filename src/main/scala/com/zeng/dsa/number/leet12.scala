package com.zeng.dsa.number

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @author zengwang
 * @create 2022-03-24 8:37 下午
 * @desc:
 */
object leet12 {
  /***
   * 字符          数值
      I             1
      V             5
      X             10
      L             50
      C             100
      D             500
      M             1000
   * @param args
   */
  def main(args: Array[String]): Unit = {
//    println("11" * 2)
    println(intToRoman(1234))
    println(intToRoman(12))
    println(intToRoman(1072))
    println(intToRoman(1994))
  }

  /**
   * 从高位开始计算，先凑高位，当前位不满足(太大了)，则进行低位的尝试
   * @param num
   * @return
   */
  def intToRoman(num: Int): String = {
    // Array要求内容类型必须一样，所以里面的只能用tuple了
//    var units = Array((1000, "M"), (500, "D"), (100, "C"),
//                (50, "L"), (10, "X"), (5, "V"), (1, "I"))
    // 把特殊位看成普通的一种进制,当做低位，合理插入
    var units = Array((1000, "M"), (900, "CM"), (500, "D"), (400, "CD"), (100, "C"), (90, "XC"),
      (50, "L"), (40, "XL"), (10, "X"), (9, "IX"), (5, "V"), (4, "IV"), (1, "I"))
    var uChars: ArrayBuffer[String] = ArrayBuffer()
    var res = num
    var i = 0
    while (res > 0) {
      val elem = units(i)
      val unitN = elem._1
      val unitC = elem._2

      val div = res / unitN
      if (div >= 1) {
        uChars.append(unitC * div)
      }
      res = res - div * unitN

      i += 1
    }

    return uChars.mkString("")
  }
}
