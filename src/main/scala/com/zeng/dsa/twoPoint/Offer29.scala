package com.zeng.dsa.twoPoint

import scala.collection.mutable.ArrayBuffer
import scala.util.control.Breaks

/**
 *
 * @author zengwang
 * @create 2022-02-22 8:37 下午
 * @desc:
 */
object Offer29 {
  def main(args: Array[String]): Unit = {
    val array: Array[Nothing] = Array()
    Range(1, 1).foreach(println)
  }

  /**
   * 顺时针打印矩阵
   *
   * 分析： 使用双指针，上下边界双指针，左右边界双指针
   * 按照上、右、下、左四条边的方式 顺时针打二维数组，每打印完一条边(遍历到底，不留角)，就收缩这条边
   *
   * 画图分析！！！ 3*3、3*4，4*3， 4*4
   * 设上右下左分别为: t, r, b, l
   * 1.打印t这条边: 遍历l->r, t++(收缩)
   * 2.打印r, 遍历t->b, r--
   * 3.打印b, 遍历r->l, b--
   * 4.打印l, 遍历b->t, l++
   *
   * 循环条件：t <= b && l <= r
   * 规律(按照case推出来的)： 每收缩一条边，都要检查，一旦发现缩边后，t > b or l > r 就要立马退出while，这时候刚好遍历完所有位置
   * 不然，再继续遍历，指针错位就会重复加入元素
   *
   * @param matrix
   * @return
   */
  def spiralOrder(matrix: Array[Array[Int]]): Array[Int] = {
    if (matrix.length == 0) return Array()
    val ints: ArrayBuffer[Int] = ArrayBuffer[Int]()

    // 初始化上右下左四个指针
    var t = 0
    var r = matrix(0).length - 1
    var b = matrix.length - 1
    var l = 0

    Breaks.breakable(
      while (t <= b && l <= r) {
        // 上
        for (j <- l to r) {
          ints.append(matrix(t)(j))
        }
        t += 1
        if (t > b) Breaks.break()

        // 右
        for (i <- t to b) {
          ints.append(matrix(i)(r))
        }
        r -= 1
        if (l > r) Breaks.break()

        // 下
        for (j <- (l to r).reverse) {
          ints.append(matrix(b)(j))
        }
        b -= 1
        if (t > b) Breaks.break()

        for (i <- (t to b).reverse) {
          ints.append(matrix(i)(l))
        }
        l += 1
        if (l > r) Breaks.break()

      }
    )


    ints.toArray
  }

}
