package com.zeng.dsa.sort

/**
 *
 * @author zengwang
 * @create 2022-01-17 1:16 下午
 * @function:
 */
object O_N_Sort {
  def main(args: Array[String]): Unit = {
    val ages: Array[Int] = Array(12, 20, 21, 12, 21, 34, 45, 34, 45, 20, 20)
    ON_Sort(ages)
    println(ages.mkString("-"))
  }

  /**
   * 要求实现一个时间复杂度为O(N) 的排序
   * ----摘自剑指Offer中，11题前面的排序片段
   * 时间复杂度O(N) 比 N*logN 还小，一般需要申请额外的存储空间
   *
   * 需求: 假设公司有1w名员工，请对公司的所有员工按照年龄排序
   * 限制: 需要元素值自身是在一定范围内分布的，比如年龄
   * 分析: （元素值计次数）
   *  1.年龄范围都是在0~99之间，我们可以申请一个100大小的数组，
   *  2.下标i代表年龄，arr(i)存年龄为i的人数的个数
   *  3.然后遍历arr 根据次数把年龄展开即为排序后的结果
   * @param nums
   */
  def ON_Sort(ages: Array[Int]): Unit = {
    // 0~99 年龄出现的次数, 默认都是0
    val ageNums = new Array[Int](100)

    for (elem <- ages) {
      ageNums(elem) += 1
    }

    // 计数，下标
    var p = 0
    // 拿到0~99岁年龄的出现次数
    for (i <- ageNums.indices) {
      // 第i岁出现的次数
      for(j <- 0 until ageNums(i)) {
        ages(p) = i
        p += 1
      }
    }
  }
}
