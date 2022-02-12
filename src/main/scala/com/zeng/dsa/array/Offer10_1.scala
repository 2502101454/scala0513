package com.zeng.dsa.array

/**
 *
 * @author zengwang
 * @create 2022-01-16 1:34 下午
 * @function:
 */
object Offer10_1 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 求第N项 斐波那契数
   * !!答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
   *
   * 分析:
   * 不要轻视简单题，这题关键在于，按照F(N) = F(N - 1) + F(N - 2) 的公式顺序，
   * 写几组输入，f(n-1)位置即为a, f(n-2)位置即为b, 观察变量a, b 变化即可实现编码
   * f2 = f1 + f0
   * f3 = f2 + f1
   * ！！！而不是写成f3 = f1 + f2 这个顺序写反对自己a、b变量赋值推导有障碍
   *
   * 同时注意f(n)值 会超过 Int32 甚至 Int64 的取值范围，导致最终的返回值错误
   * 题目要求取模，所以sum取值刚超过1e9+7的时候，我们就得立刻取模，不然对于此题而言，我们sum当前就是错误的，
   * 继续参与下一轮计算，也就把错误代入了下一轮中，从而每轮都错了，最后返回的时候再取模也没有意义了
   *
   * @param n
   * @return
   */
  def fib(n: Int): Int = {
    if (n == 0 || n == 1) return n
    var a: Int = 1 // f1
    var b: Int = 0 // f0
    var res: Int = 0
    for (i <- 2 to n) {
      // f2 = f1 + f0
      // 注意取模生效在刚超过1e9+7的时候，这样对于**此题而言**，res是正确的答案
      // --2147483647这是java的int上线，20亿--
      res = (a + b) % 1000000007

      b = a
      a = res
      // f3 = f2 + f1
    }
    res
  }
}
