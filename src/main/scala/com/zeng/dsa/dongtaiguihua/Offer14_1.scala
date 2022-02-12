package com.zeng.dsa.dongtaiguihua

/**
 *
 * @author zengwang
 * @create 2022-01-29 10:01 上午
 * @desc:
 */
object Offer14_1 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 1.确定dp以及下标含义
   * dp(i) 代表长度i的绳子，切分m段(m > 1)后，各段长度乘积的最大值
   *
   * 2.转换方程
   *  因为必切，设切分的第一段，长度为j
   *  2.1 当只切分第一段为j，剩余i-j 这部分不再切分，乘积为 j * (i-j)
   *  2.2 当只切分第一段为j，剩余i-j 这部分继续切分，则乘积是j * dp(i-j)
   * 因此dp(i) 需要从上面两种方式中，取最大的乘积 Max(j * (i-j), j * dp(i-j))
   *
   * 但是 这个第一段长度j 可以是1，2，3...., i-1，所以 我们需要挨个取j，
   * 计算每次dp(i)的长度，最后在这么多次的dp(i)中挑个最大的，因此使用一层循环来做，记做A
   *
   * dp(i) = Max(dp(i), Max(j*(i-j), j*dp(i-j)))
   * dp(i) 默认是0了，递推公式带有循环性质
   *
   * 注意: j的范围可以是[2, i/2]， 因为经过举例，发现dp(i)在j是1的时候，不可能得到最大
   * i/2，举例推出来的，假设i是10，
   * j是1时和j是9时，公式j * (i-j) 正反是一样的
   * 对于公式j * dp(i-j)，在j 超过i/2的情况，i-j变小dp(i-j)的值也会很小，
   * 比如7 * dp(3) = 14，但是 3 * dp(7) = 36，所以我们只需要求前半部分就可以确定最大值了，求后面没啥用
   *
   * 3.初始化
   * dp(2) = 1
   * dp(3) = 2
   *
   * 4.遍历顺序
   * 现有长度为n的绳子，因此就是dp(n)，dp(n)也是经过j 从 [2, n/2]的切分过程，这个过程中需要用到dp(n-j)
   * 所以从前向后遍历
   *
   * 5.打印验证
   *
   * @param n
   * @return
   */
  def cuttingRope(n: Int): Int = {
    if (n <=3 ) return n - 1
    val dp = new Array[Int](n + 1)

    dp(2) = 1
    dp(3) = 2

    for (i <- 4 to n) { // 从前向后计算dp(i)
      var dpi = 0
      for (j <- 2 to i/2) { // 求每个dp(i)，在j的不同长度下，取乘积的最大值
        val cur:Int = math.max(j * (i -j), j * dp(i - j))
        if (cur > dpi) {
          dpi = cur
        }
        // 这个写法不好理解，不这么写
        // dp(i) = math.max(dp(i), math.max(j * (i -j), j * dp(i - j)))
      }
      dp(i) = dpi
    }

    dp(n)
  }

  /**
   * 贪心解法，数论结论：
   * 切分长度3的段越多，则最后各段乘积越大，但是不能留下1的段，所以1的段就和3一起，作为长度为4的段即可
   *
   * @param n
   * @return
   */
  def cuttingRopeV2(n: Int): Int = {
    if (n <= 3 ) return n - 1
    if (n == 4) return 4

    // 对于大于4的 可以如下处理，保证至少切出一个3
    // 3的个数
    var num3 = n / 3
    val mod3: Int = n % 3
    // 不能留下1，得和3一起拼成4
    val res = if (mod3 == 1) {
      num3 -= 1
      math.pow(3, num3) * 4
    } else if (mod3 == 2) {
      math.pow(3, num3) * 2
    } else {
      math.pow(3, num3)
    }
    res.toInt
  }
}
