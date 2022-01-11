package com.zeng.dsa.string

/**
 *
 * @author zengwang
 * @create 2022-01-09 5:35 下午
 * @function:
 */
object Offer05 {
  def main(args: Array[String]): Unit = {
    println(replaceSpaceTheory("we are h"))
    println(replaceSpaceTheory("we "))
    println(replaceSpaceTheory("we"))
  }

  /**
   * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
   * 思路：
   * 对原数组进行扩容(不要申请额外的空间)
   * 双指针，i, j，i指向扩容后的数组末尾，j指向提老数组的末尾下标位置
   * i 代表下次要被覆盖的位置
   *
   * 1.初始有空格，i在j之后 (i > j)
   * 2.从后向前移动j，判断j元素的值:
   *  2.1 如果j不是空格，则i的位置复制j 元素值，i前进1，j前进1 ,
   *  2.2 如果j是空格，则i位置前面(包含i) 3个位置替换为%20 , i = i - 3, j 前进1
   *  当i == j时，则停止（这个是我们通过样例分析出来的，所以大胆写，不要老是多疑）
   *  比如 we'空格',最后i = j，还有样例输入，也是如此
   *
   * 问题： 为什么要从后面往前面遍历？因为从前面往后遍历，遇到空格覆盖时还需要移动后面的元素
   * 时间复杂度是O(n*n)
   * @param s
   * @return
   */
  def replaceSpace(s: String): String = {
//    s.toCharArray().
//    s.replace(" ", )
    s
  }


  def replaceSpaceTheory(s: String): String = {
    val chars: Array[Char] = s.toCharArray()
    // 进行数组扩容
    var spaceCount = 0
    for (elem <- chars) {
      if (elem == ' ') spaceCount += 1
    }
    if (spaceCount == 0) return s

    val newChars: Array[Char] = new Array[Char](spaceCount * 2 + chars.length)
    println(newChars.length)
    var j = 0
    for (elem <- chars) {
      newChars(j) = elem
      j += 1
    }
    j -= 1

    var i = newChars.length - 1
    // 初始化i,j位置完毕

    while (i > j) {
      if (newChars(j) != ' ') {
        newChars(i) = newChars(j)
        i -= 1
      } else {
        newChars(i) = '0'
        newChars(i - 1) = '2'
        newChars(i - 2) = '%'
        i -= 3
      }
      j -= 1
    }

    newChars.mkString("")
  }

}
