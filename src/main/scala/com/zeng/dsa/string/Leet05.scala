package com.zeng.dsa.string

import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2021-12-15 9:16 上午
 * @function:
 */
object Leet05 {

  def main(args: Array[String]): Unit = {
    var s = "babad"
    s = "babadacd"
//    s = "a"
    s = "aa"
    s = "cbbd"
    println(longestPalindrome(s))
  }

  /**
   * 最长回文子串
   *
   * 题解: 中心扩散法，(耐心多分情况)
   *
   * 1.分析，回文的情况有两种:
   *  1.1单字符: a
   *  1.2双字符: aa
   *
   * 2.遍历整个字符串，依次取单、双字符:
   *  2.1 不是回文则直接返回
   *  2.2 是回文则向两头扩散, 找到第一次两头字符不相等的情况，记做当前左端下标s，右端下标e，
   *  那么[s+1, e-1] 一定是回文，更新最长回文范围举。例如下输入：
   *    有字符串abc， 当前s、e在b的位置
   *    有双字符abbc，当前s、e在b、b的位置
   *    边界条件bbc, 当前s、e在b、b的位置
   *    上面情况都是可以处理的
   *
   * @param s
   * @return
   */
  def longestPalindrome(s: String): String = {
    if (s == "" || s == null) return ""

    // [ x, y) 左闭右开，收集回文子串的区间下标
    var rangeIndex = Array(0, 0)

    val chars: Array[Char] = s.toCharArray
    for (i <- 0 until chars.length) {
      // 注意过滤掉双子符超下标范围的情况，比如abc， s在c，e=s+1就超了
      inspect(chars, i, i, rangeIndex)
      if (i + 1 < chars.length) {
        inspect(chars, i, i + 1, rangeIndex)
      }
    }

    chars.slice(rangeIndex(0), rangeIndex(1)).mkString("")

  }

  /**
   * 自中心开始检测, start end是选择的中心下标
   * @param chars
   * @param start
   * @param end
   */
  def inspect(chars: Array[Char], start: Int, end: Int, rangeIndex: Array[Int]): Unit = {
    // 当前选择的中心自身不是不处理
    if (chars(start) != chars(end)) return

    var s = start
    var e = end

    // 找到第一次两头不相等的情况
    Breaks.breakable(
      while (0 <= s && e <= chars.length - 1) {
      // 中心是回文，向两头扩散
      if (chars(s) == chars(e)) {
        s -= 1
        e += 1
      } else {
        Breaks.break()
      }
    }
    )

    // [s+1, e-1]是回文, 换算成开区间[s+1, e), 串长度e - (s+1) = e-s-1
    // (边界情况也可以处理，比如abb，s、e中心在bb位置)
    // 注意这里的串长度的计算都是在用左闭右开的区间计算方式
    if (e - s - 1 > rangeIndex(1) - rangeIndex(0)) {
      rangeIndex(0) = s + 1
      // 方便后面数组切片，右端点是开区间
      rangeIndex(1) = e
    }
  }
}
