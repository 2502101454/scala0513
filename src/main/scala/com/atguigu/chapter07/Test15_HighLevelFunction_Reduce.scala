package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-11 11:06 下午
 * @function:
 */
object Test15_HighLevelFunction_Reduce {
  def main(args: Array[String]): Unit = {
    val list = List(3, 4, 5, 8)
    // 1.reduce
    println(list.reduce(_ + _ ))
    println(list.reduce(_ - _ ))
    // reduce 底层其实就是reduceLeft，从左往右结合
    println(list.reduceLeft(_ - _ ))
    // reduce right的结合顺序，不是单纯的从右往左那么简单
    println(list.reduceRight(_ - _)) // -4
    /** reduceRight
     * 整体是从右向左，但是先取(5, 8) 传入匿名函数_ - _
     * 就是-3，然后从右向左，不断取前一个元素再和当前的结果 一起传入匿名函数
     * 即 4 - -3 = 7; 3 - 7 = -4
     */

    println(" =================== ")
    // 2.fold 设置初始值，然后参与到reduce中去
    println(list.fold(10)(_ + _)) //  10 + 3 + 4 + 5 + 8
    // fold底层就是fold left
    println(list.foldLeft(10)(_ - _)) //  10 - 3 - 4 - 5 - 8
    // 注意顺序，初始值放在最后一组的右边，从最后一组开始往前reduce
    println(list.foldRight(11)( _ - _)) // (3 - (4 - (5- (8 - 11))))

  }
}
