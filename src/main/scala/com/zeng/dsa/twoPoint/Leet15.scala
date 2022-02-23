package com.zeng.dsa.twoPoint

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2022-02-14 1:03 下午
 * @desc:
 */
object Leet15 {
  def main(args: Array[String]): Unit = {
    val set1 = Set(Array(1, 2, 3, 2, 3)) // Set中存的元素是数组的引用
    val set2: Set[Int] = Array(1, 2, 3, 2, 3).toSet
    val set3: Set[Int] = List(2, 3, 2, 4, 5, 4).toSet
//    val set1 = Set(1, 2, 3, 2, 3)
    println(set1.mkString("--"))
    println(set2.mkString("--"))
    println(set3.mkString("--"))
    val list7 = 1 :: 2 :: 3 :: 4 :: Nil
    println(list7)

    println(threeSum(Array(-1, 0, 1, 2, -1, -4)))
  }

  /**
   * 数组nums，任选3个元素，a,b,c 使得a+b+c=0，求不重复的a，b，c组合
   *
   * (1,-1,0) (0,1,-1),(1,0,-1) 这些都算是重复的组和
   *
   * 去重分析：
   * 任意3个元素，组合不重复的话，选取规则可参考Leet16中的暴力分析那块，即
   * 先枚举一个数的可能，记做下标i；
   * 然后枚举第二个数，范围是在第一个数的后面，
   * 然后枚举第三个数，范围是在第二个数的后面
   *
   * 但是上面是根据位置标识的数据不重复，如果数组内有些位置的元素就是重复的话，组合也会出现重复！
   * 如：-4 -1 -1 0 1 2. 产出的集合为(-1, 0, 1), (-1, 0, 1), (-1, -1, 2)
   * 所以防止这种情况发生，还需要对最后的结果去重，（不能提前去重数组元素，重复元素也可以都被选中为一组解，比如(-1,-1,2)）
   *
   * 最终的处理方式是，先数组元素去重，然后再保证位置选取的不重复~
   *
   * 双指针法:
   * 1. 排序整个数组nums
   * 2. 枚举，先选取a，位置记做i
   * 3. b的指针记做pb, 初始为i+1，c的指针为pc，初始为 nums.length-1
   *
   * !!转换问题!!：b+c记为sum， 枚举sum=-a，举个例子:
   * -4, -1, 0, 1, 2
   * a为-4，所以要满足sum是4才行，初始化b为-1，c为2
   *
   * 算法过程:
   * b + c < sum, 固定pc, pb往后移动，这样b+c才能更接近sum，
   * 如果移动过程中b + c > sum了，则此时pb不动，pc往前移动，
   * 如果b + c = sum了，说明收集到一组解，接着pb向后、pc向前同时移动才有可能发现新的等于sum的机会~
   * 如此往复保持pb < pc
   *
   * 流程如下,初始pb + pc < sum
   * 1.此时，pc此时不变，pb向后移动，移动到pb1位置，pb1 + pc > sum
   * 2.接着因为太大了，所以pc向前移动到pc1，pb1不动，接着pb1 + pc1 < sum
   *
   * 此时又变小了，接着处理的话，
   * 问题1.为啥pc1不能向后移动? 想想pc为啥要向前移动，就是因为pb1 + pc > sum
   * pc1后退就是pc了，pb1 + pc > sum 无意义
   *
   * 问题2.为啥pb1不能向前移动，pc1也向后移动？
   * pb1向前移动就成pb了，pc1向后移动就是pc了， pb + pc < sum，无意义
   *
   * 所以从这两个问题角度验证了双指针在升序数组中的算法思想
   *
   * @param nums
   * @return
   */
  def threeSum(nums: Array[Int]): List[List[Int]] = {
    val res: mutable.Set[List[Int]] = mutable.Set[List[Int]]()

    val sortNums: Array[Int] = nums.sortWith(_ < _)

    val length = sortNums.length
    // 枚举第一个数
    for (i <- Range(0, length-2)) {
      val a = sortNums(i)

      var pb = i+1
      var pc = length-1
      // 转换问题，求b+c 的sum 与 -a的关系
      while (pb < pc) {
        val sumV: Int = sortNums(pb) + sortNums(pc)
        if (sumV < -a) { // 需要sumV更大一点
          pb += 1
        } else if (sumV > -a) { // 需要sumV更小一点
          pc -= 1
        } else { // sumV == -a
          // 给res这个list中，头部插入新的元素
          res.add(List(a, sortNums(pb), sortNums(pc)))
          pb += 1
          pc -= 1
        }

      }

    }
    res.toList
  }
}
