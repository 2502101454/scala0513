package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-02-09 1:31 下午
 * @desc:
 */
object Offer14_2 {
  def main(args: Array[String]): Unit = {
    println(Integer.MAX_VALUE)
    println(Long.MaxValue)
    println(Double.MaxValue)
  }

  /**
   * 剪绳子的版本2，这次输入n的范围太大了，无法使用动态规划了，时间复杂度太大
   * 题目要求：答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1
   * 贪心解法，数论结论：
   * 切分长度3的段越多，则最后各段乘积越大，但是不能留下1的段，所以1的段就和3一起，作为长度为4的段即可
   * @param n
   * @return
   */
  def cuttingRope(n: Int): Int = {
    if (n <= 3 ) return n - 1
    if (n == 4) return 4

    // 对于大于4的 可以如下处理，保证至少切出一个3
    // 3的个数
    var num3 = n / 3
    val mod3: Int = n % 3
    // 不能留下1，得和3一起拼成4
    //先前的计算方法存在问题，如果在求n次方的过程已经超出精度了呢？我们无法控制该过程中的精度溢出所以得改进下
    val res: Double = if (mod3 == 1) {
      num3 -= 1
      math.pow(3, num3) * 4
    } else if (mod3 == 2) {
      math.pow(3, num3) * 2
    } else {
      math.pow(3, num3)
    }
    (res % 1000000007).toInt
  }


  def cuttingRope2(n: Int): Int = {
    var num = n
    if (num <= 3) return n - 1
    if (num == 4) return 4
    // 10: 3 3 4
    // 11: 3 3 3 2
    // 9: 3 3 3
    // 5: 3 2

    /* Int的最大整数是2147483647，即20亿，下面res * 3随时超过这个范围，因为取余10亿，
     * 所以res不会超过10亿，如果是9亿，下一轮计算，9亿 * 3=27亿，也已经超过了Int精度了，
     * 此时精度溢出了，值就不对了，所以要换成Long类型，long的最大值9亿兆，存30亿随随便便
     * var res: Int = 1
     */

    var res: Long = 1
    // 不断的从num身上划3，做乘积的过程控制精度始终不溢出
    while (num > 4) {
      // 5
      num = num - 3
      res = (res * 3) % 1000000007
    }
    // 对最后剩下的4，3，2单独处理
    res = (res * num) % 1000000007
    if (res == 1000000008) 1 else res.toInt
  }
  }
