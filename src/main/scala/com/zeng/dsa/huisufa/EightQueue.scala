package com.zeng.dsa.huisufa

import scala.language.postfixOps

/**
 *
 * @author zengwang
 * @create 2021-11-27 9:42 上午
 * @function:
 */
object EightQueue {
  def main(args: Array[String]): Unit = {
    cal8Queues(0)

  }

  // 8个皇后的行列下标存储，result[i]，下标i代表行，result[i]存储列下标
  val result: Array[Int] = new Array[Int](8)
  /**
   * 如何递归：每一行有8种选择，我们先从第一行选择一个合适的位置后，接着走下一行，依次类推
   * 递归结束条件：当前处理的行下标已经 >7
   * @param row 当前行下标, 函数的调用从cal8Queues(0)开始
   */
  def cal8Queues(row: Int): Unit = {
    if (row == 8) {
      println("find a plan")
      printQueuePlans(result)
      return
    }

    for (column <- 0 to 7) {
      // 检查当前行位置ok，那么加入result，继续下一行
      if (isOK(row, column)) {
        result(row) = column
        cal8Queues(row + 1)
      }
    }
  }

  /**
   * 行列即可确定一个皇后位置，检查当前位置是否可用，
   * 即同一行、同一列、同对两条角线(以当前位置为基准)上都无其他皇后
   * @param row
   * @param column
   */
  def isOK(row: Int, column: Int): Boolean = {
    // 初始化两个左右列的下标，后面沿着对角线做检查用
    var (columnUPL, columnUPR) = (column - 1, column + 1)

    /*检查当前位置的上面区域, Range 和python的用法基本一样，左闭右开
    检测顺序：是从下往上, 每行可以检测3个位置，正上方(同列)、两条对角线上的位置
    */
    for (rowUP <- Range(0, row).reverse) {
      if (result(rowUP) == column) return false // 检查正上方同列
      // 检查两条对角线上的位置, 注意棋盘边界
      if (columnUPL >= 0) {
        if (result(rowUP) == columnUPL) return false
      }
      if (columnUPR <= 7) {
        if (result(rowUP) == columnUPR) return false
      }

      columnUPL += 1
      columnUPR += 1
    }

    true
  }

  def printQueuePlans(result: Array[Int]): Unit = {
    for (i <- 0 to 7){
      for (j <- 0 to 7){
        if (result(i) == j) print("* ") else print("- ")
      }
      println()
    }
  }
  println()
}


