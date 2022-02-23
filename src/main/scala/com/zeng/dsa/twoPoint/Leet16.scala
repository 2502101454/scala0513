package com.zeng.dsa.twoPoint

/**
 *
 * @author zengwang
 * @create 2022-02-10 8:41 下午
 * @desc:
 */
object Leet16 {
  def main(args: Array[String]): Unit = {
    //    println(threeSumClosest3(Array(-1,2,1,-4), 1))
    println(threeSumClosest3(Array(1, 2, 4, 8, 16, 32, 64, 128), 82))
  }

  /**
   * 长度为n的整数数组nums, 给一个目标值 target，请你从中选3个整数，使和最接近target，求和是多少
   *
   * 3 <= nums.length <= 1000
   * -1000 <= nums[i] <= 1000
   * -104 <= target <= 104
   *
   * 这个题，剑指offer 2上没有，但是leetCode有
   *
   * 分析： 这个是变相的 0 1背包问题吧
   * 翻译为: 有n个物品，物品重量都是1，价值是nums[i]，背包限重为3，请你在满足背包限重的情况下，
   * 求背包总价值最接近target的价值是多少?
   * 不过特殊点，最后我们在dp数组中找背包重量为3时的价值
   *
   * 1.明确dp数组以及下标含义
   * dp(i)(j) 代表决策完第i个物品后，当前背包重量为j，背包最接近target的价值
   * 2.状态转换公式
   * 第i个数字不选: dp(i)(j) = dp(i-1)(j)
   * 第i个数字选: dp(i)(j+weight(i)) = dp(i-1)(j) + value(i)
   * 3.初始化
   * 因为这里求一个离target最近的值，我们只能假设给一个离target最远的值，表示没有状态可达
   * dp数组整体需要初始化成Int.MaxValue，这样赌一把
   *
   * 初始化第一个数字选或者不选
   * 4.遍历顺序
   * 5.打印dp(数组)
   *
   * @param nums
   * @param target
   * @return
   */
  def threeSumClosest(nums: Array[Int], target: Int): Int = {
    val n: Int = nums.length
    val dp: Array[Array[Int]] = Array.ofDim(n, 4)
    // 初始化
    for (i <- 0 until n) {
      for (j <- 0 to 4) {
        dp(i)(j) = Int.MaxValue
      }
    }
    // 第0个数字不选、选
    // 不选也无法给一个价值，不选也是一种状态的，这里无法表示, 这题用不了动态规划，拉闸
    //    dp(0)(0) =
    1
  }

  /**
   * 暴力解法: 让选3个数，我们先选数组第一个元素作为第1个数，剩下两个数，从首位元素的后面选取；
   * 选取第二个数后，第三个数也得在第二个数后面选取
   *
   * 比如：4 5 6 7 8
   * 第一个数选4，第二个数在4之后选择，假设选6，第三个数在6之后选择，假设选7
   * 所以第一、第二、第三个数依次为 4 6 7
   * 如果第3个数在6之前选择，比如选5，那么第一、第二、第三个数依次为 4 6 5
   * 4 6 5和 4 5 6是一样的，即第一个选4 ，第二个选5，第三个选6，这组case之前出现过，所以重复了
   *
   * 选取的case不重复！！！
   * 本质： 选中第一位数后，要枚举第二位数所有的可能，选中第二位数，枚举第三位数所有的可能，层层递进
   *
   * 从数组首元素开始，向后遍历，依次选择第一个数
   *
   * @param nums
   * @param target
   * @return
   */
  def threeSumClosest2(nums: Array[Int], target: Int): Int = {
    // 初始化为第一组，循环之中再让第一组和第一组判断一下不影响
    var difference = math.abs(nums(0) + nums(1) + nums(2) - target)
    var sumValue = nums(0) + nums(1) + nums(2)
    val length: Int = nums.length
    for (i <- 0 to length - 3) {
      for (j <- i + 1 to length - 2) {
        for (k <- j + 1 to length - 1) {
          val tmp = nums(i) + nums(j) + nums(k)
          // 如果相等，则肯定是最接近的了，直接返回
          if (tmp == target) return target
          if (math.abs(tmp - target) < difference) {
            difference = math.abs(tmp - target)
            sumValue = tmp
          }
        }
      }
    }
    sumValue
  }

  /**
   * 解法： 双指针, 类似于Leet15题
   * 分析：第一第二第三个数的选取范围还是和暴力分析中的一样，不过我们先对数组进行排序
   * 对于有*序数*组：
   * 选取nums(i)作为第一个数，记做a
   * 选取nums(i+1)作为第二个数，i+1记做pb指针
   * 选取nums(end)作为第三个数，end为数组末尾下标，end记做pc指针
   *
   * sum = a + nums(pb) + nums(pc)
   * A: if sum >= target
   * pb不动，此时pc向前移动，这样nums(pc)会越来越小，sum会变小，趋近target
   *
   * B: if sum < target
   * pc不动, 此时pb向后移动，这样sum会变大，趋近target
   *
   * 外部存一个全局最接近target的sumV
   * 在pb < pc时，如此往复上述情况，此过程中不断更新sumV
   *
   * 时间复杂度，O(n2)，对于数组的每个位置可选做第一个数，后续第2、3数的选取范围中，都有一个O(n)的遍历
   *
   * @param nums
   * @param target
   * @return
   */
  def threeSumClosest3(nums: Array[Int], target: Int): Int = {
    val sortedNums: Array[Int] = nums.sortWith(_ < _)
    val length: Int = sortedNums.length

    var sumV = sortedNums(0) + sortedNums(1) + sortedNums(2)
    var diff = math.abs(sumV - target)
    for (i <- 0 to length - 3) { //枚举第一个数
      var pb = i + 1
      var pc = length - 1
      while (pb < pc) {
        val tmpSum = sortedNums(i) + sortedNums(pb) + sortedNums(pc)
        val tmpDiff = math.abs(tmpSum - target)
        if (tmpDiff < diff) {
          sumV = tmpSum
          diff = tmpDiff
        }

        // 双指针移动
        if (tmpSum > target) {
          pc -= 1
        } else if (tmpSum < target) {
          pb += 1
        } else { // tmpSum == target
          return target
        }
      }

    }
    sumV
  }

}
