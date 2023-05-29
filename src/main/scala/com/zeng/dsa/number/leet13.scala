package com.zeng.dsa.number

import scala.collection.mutable.ArrayBuffer

/**
 *
 * @author zengwang
 * @create 2022-03-25 1:16 下午
 * @desc:
 */
object leet13 {
  def main(args: Array[String]): Unit = {
    println(romanToInt("MCMXCIV"))
  }

  /**
   * 罗马数字转整数
   *
   * 1.从字符串头部开始，每次取两个字符先进行匹配，匹配不到则只取一位
   * 2.依次累加
   * @param s
   * @return
   */
  def romanToInt(s: String): Int = {
    val map = Map("M" -> 1000, "CM" -> 900, "D" -> 500,
                "CD" -> 400, "C" -> 100, "XC" -> 90,
                "L" -> 50, "XL" -> 40, "X" -> 10,
                "IX" -> 9, "V" -> 5, "IV" -> 4, "I" -> 1)

    var res = 0
    val chars: Array[Char] = s.toCharArray()
    var i = 0
    while (i < chars.length) {
      val part = chars.slice(i, i + 2)
      val partStr = part.mkString("")
      if (map.contains(partStr)) {
        res += map(partStr)
        i += 2
      }
      else {
        res += map(chars(i).toString)
        i += 1
      }
    }

    res
  }
}
