package com.zeng.dsa.greedy

/**
 * 柠檬水找零:
 * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 *  注意，一开始你手头没有任何零钱。
 *
 * 我的思考:
 * 1.首先分情况:
 *  5元直接收
 *  10元找5元
 *  20元先找(10, 5) 没有10了再找(5, 5, 5)
 *    这里先10元其实就是利用的贪心的思想: 都是找钱，能够让5元省下找给更多的顾客
 *  2. 对 5\10\20 分别维护一个数组，然后找零时维护游标找过的就游标后移，找钱的时候做优先级先10后5
 *
 *  点评：这样写肯定可以实现，但是时空复杂度大
 *
 *
 * -------巧解:---------
 *  在需要找钱的时候，5元总要被找出去，所以我们维护一下5 10 20元的个数，
 *  最后在找钱的时候如果5元不够了，那肯定无法找零了
 * @author zengwang
 * @create 2021-12-02 10:57 下午
 * @function:
 */
object leet860 {
  def main(args: Array[String]): Unit = {

  }

  def lemonadeChange(bills: Array[Int]): Boolean = {
    var five: Int = 0
    var ten: Int = 0
    var twenty: Int = 0
    for (elem <- bills) {
      if (elem == 5) {
        five += 1
      } else if (elem == 10) {
        ten += 1
        five -= 1
        if (five < 0) return false
      } else if (elem == 20) {
        twenty += 1
        // five 必出一张
        five -= 1
        if (five < 0) return false

        // 优先出(10, 5)
        if (ten >= 1) {
          ten -= 1
        }else{
          // 没有10元，则继续出两个5 组成(5, 5, 5)
          five -= 2
          if (five < 0) return false
        }
      }
    }

    true
  }
}
