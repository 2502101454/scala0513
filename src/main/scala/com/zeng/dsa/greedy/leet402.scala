package com.zeng.dsa.greedy

import scala.collection.mutable.ListBuffer

/**
 *
 * @author zengwang
 * @create 2021-11-24 1:19 下午
 * @function:
 */
object leet402 {
  def main(args: Array[String]): Unit = {
    println("000111".toInt.toString)
//    println(removek("6142324", 3))
//    println(removek("1432219", 3))
//    println(removek("10200", 1))
//    println(removek("10", 2))
//    println(removek("10001", 1))
  }

  /**
   * 题：给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，
   * 使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
   *
   * 分析：转换问题，假设原来整数是n个数字组成，我们按顺序(数字间的相对顺序依然保证不变)选择
   * n - k个数子，最后组成数值最小
   *
   * 真正影响整体数值大小的因素是高位，次高位，低位，这是一个优先级，所以我们每次在当前可选的范围内，
   * 选择数值最小的数字(期望贡献最大)
   *
   * 1. 冷静分析真正影响期望的因素
   * 2. 分析每次输入的范围
   * 3. 在每次输入的范围中选择对期望贡献最大的数据
   * @param num
   * @param k
   * @return
   */
  def removek(num: String, k: Int): String = {
    val n = num.length
    // 剩余位数
    var restN = n - k
    if (restN == 0) return "0"
    // 可用空串把字符串切割成每个字符作为一个串
    val ints: Array[Int] = num.split("").map(_.toInt)
    // println(ints.mkString("-"))
    var listBuffer: ListBuffer[Int] = new ListBuffer[Int]()
    // 这是后面的边界值处理，去除前缀0用的
    // li是listBuffer的元素下标，firstN0是其第一个不等于0的下标
    var firstN0 = -1

    val aLength = ints.length
    // 当前的输入范围, [begin, end]是数组的下标, 闭区间
    var begin = 0
    // 个数- 个数 = 下标 - 下标  ，反正得出的是下标，你自己算
    var end = aLength - restN
    while (restN > 0) {

      var p = begin
      var minIndex = begin
      // 在当前的区间中选择最小的数字
      while(p < aLength && end < aLength && p <= end ) {
        if (ints(minIndex) > ints(p)) {
          minIndex = p
        }
        p += 1
      }

      listBuffer.append(ints(minIndex))
      restN -= 1
      // 根据新加入元素来更新firstN0
      if (listBuffer(listBuffer.length - 1) != 0 && firstN0 == -1) {
        firstN0 = listBuffer.length - 1
      }

      // 更新下次的输入范围
      begin = minIndex + 1
      end = aLength - restN
    }
//    var res = listBuffer.mkString("").stripPrefix("0")
    if (firstN0 != -1) listBuffer.slice(firstN0, listBuffer.length).mkString("") else "0"
  }
}
