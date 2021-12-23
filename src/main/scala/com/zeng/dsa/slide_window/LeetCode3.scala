package com.zeng.dsa.slide_window

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-12-11 9:26 上午
 * @function:
 */
object LeetCode3 {
  /**
   * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度
   * 输入: s = "pwwkew"
    输出: 3
    解释: 因为无重复字符的最长子串是"wke"，所以其长度为 3。
    请注意，你的答案必须是 子串 的长度，"pwke"是一个子序列，不是子串
   * @param args
   */
  def main(args: Array[String]): Unit = {

  }

  /**
   * 滑动窗口法:
   * 1.使用快慢两指针(两变量 s f)维护一个窗口，初始化窗口从0位置开始，窗口内始终包含不重复的子串
   * 2.快指针 f 从前向后移动：
   *  2.1 引入 map，map缓存遍历过的字符和下标，目的是支持O(1)内判断当前字符是否曾经出现过
   *  2.2 如果当前字符没出现过，就加入map中
   *  2.3 如果当前字符出现过，那么移动 s 到当前字符上次出现过的位置*后面* 如: s = map('w') + 1
   *      这样才能保证当前s ~ f 这个窗口内的没有重复字符；
   *      同时更新重复字符的最新位置，不然后面如果还有这个重复字符(重复字符出现了3次，如 abdbqbc的 b)，
   *      那么s始终是在使用字符第一次的位置 + 1，这样的窗口就包含重复字符了
   * 3.随着f的移动，每次计算当前窗口的大小，f - s + 1，(自己题解笔记中提到的长度计算)， Max来记录最大长度
   * 4.最后注意：滑动窗口，窗口的左边界不能后退！
   *    比如这组输入abba，滑动结束后，得到的是bba，
   *    所以我们在2.3移动s的时候s = Max(map(currentChar) + 1, s)
   * @param s
   * @return
   */
  def lengthOfLongestSubstring(s: String): Int = {
    if (s == "") return 0
    val chars: Array[Char] = s.toCharArray
    var (slow, fast, maxLength) = (0, 0, 0)
    val map: mutable.Map[Char, Int] = mutable.Map()
    for (fast <- 0 until chars.length) {
      val char = chars(fast)
      if (map.contains(char)) {
        // 移动慢指针
        // slow = map(char) + 1
        // 加上注意点，左边界不能后退,所以和之前的左边界做比较
        if (map(char) + 1 > slow) slow = map(char) + 1
        // 更新重复元素下标为最新
        map.put(char, fast)

      } else {
        //如果当前字符没出现过，就加入map中
        map.put(char, fast)
      }
      // 每次移动快指针后，计算当前窗口大小，尝试更新最大窗口长度
      val curLength = fast - slow + 1
      if (curLength > maxLength) maxLength = curLength
    }

    maxLength
  }
}

