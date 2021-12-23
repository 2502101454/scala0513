package com.zeng.dsa.string

import scala.collection.mutable
import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-11-21 5:08 下午
 * @function:
 */
object StringMatch02 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 构建模式串的字符和索引的hash表，方便后面O(1)的检索坏字符在模式串中的位置
   * @param bString 模式串
   * @return
   */
  def hashBChar(bString: String): mutable.Map[Char, Int] = {
    val map: mutable.Map[Char, Int] = mutable.Map()
    val bChars: Array[Char] = bString.toCharArray
    // 从前向后的构建，这样后面重复的元素下标就会覆盖前面的，在坏字符检索的时候正好取这个靠后的字符
    for (i <- bChars.indices) {
      map.put(bChars(i), i)
    }
    map
  }
  /**
   * 字符串匹配值BM算法
   * @param mString  主串
   * @param bString  模式串
   * @return 主串中有匹配到模式串后，首个元素的下标；不匹配则返回 -1
   */
  def bm(mString: String, bString: String): Int = {
    // 只实现了坏字符的规则，好后缀规则实现太难了。。。
    val bMap: mutable.Map[Char, Int] = hashBChar(bString)
    val mChars: Array[Char] = mString.toCharArray
    val bChars: Array[Char] = bString.toCharArray
    val (m: Int, b: Int) = (mChars.length, bChars.length)

    // 坏字符规则: si - xi
    var i = 0
    // 定义主串的游标的最大可达位置
    while (i <= m - b) {
      var j = b - 1
      // 模式串 匹配顺序从后向前
      Breaks.breakable(
        while (j >= 0) {
          // 查找坏字符
          if (bChars(j) != mChars(i + j)) Breaks.break()
          j -= 1
        }
      )
      // 是模式串整体匹配成功了，则返回主串的游标
      if (j < 0) return i

      // 否则，查看 主串中坏字符 在模式串中的位置
      val badChar: Char = mChars(i + j)
      val xi = bMap.getOrElse(badChar, -1)
      // 更新主串的游标，就相当于让模式串向后滑动了(si - xi)单位, j 就是 si
      i += (j - xi)
    }
    -1
  }
}
