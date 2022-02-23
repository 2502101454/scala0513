package com.zeng.dsa.twoPoint

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2022-02-16 1:16 下午
 * @desc:
 */
object Leet18 {
  def main(args: Array[String]): Unit = {
    println(fourSum(Array(1, 0, -1, 0, -2, 2), target = 0))
  }

  /**
   * 四数之和，求a + b + c + d = target，求四元组(a, b, c, d) 且不重复
   * 分析：基于三数之和再套一层
   *
   * 元组不重复：
   *  基于位置的不重复选取则是，枚举第一个数a，
   *  枚举第二个数b，选取范围是a之后
   *  枚举第二个数c，选取范围是b之后
   *  枚举第二个数d，选取范围是c之后
   * 最后结果集整体做个去重
   *
   *  双指针法，只能提供c、d的选取，a、b的选取使用两层循环完成
   * @param nums
   * @param target
   * @return
   */
  def fourSum(nums: Array[Int], target: Int): List[List[Int]] = {
    val res: mutable.Set[List[Int]] = mutable.Set()

    val sortNums: Array[Int] = nums.sortWith(_ < _)
    val length: Int = sortNums.length
    // pa, pb, pc, pd 分别为a，b，c，d的指针
    for (pa <- 0 to length - 4) {
      for (pb <- pa + 1 to length - 3) {
        val subTarget = target - sortNums(pa) - sortNums(pb)
        // 双指针
        var pc = pb + 1
        var pd = length - 1
        while (pc < pd) {
          if (sortNums(pc) + sortNums(pd) < subTarget) {
            pc += 1
          } else if (sortNums(pc) + sortNums(pd) > subTarget) {
            pd -= 1
          } else {
            res.add(List(sortNums(pa), sortNums(pb), sortNums(pc), sortNums(pd)))
            pc += 1
            pd -= 1
          }
        }
      }
    }
    res.toList
  }
}
