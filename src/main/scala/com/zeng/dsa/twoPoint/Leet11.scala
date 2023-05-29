package com.zeng.dsa.twoPoint

/**
 *
 * @author zengwang
 * @create 2022-03-24 1:48 下午
 * @desc:
 */
object Leet11 {
  def main(args: Array[String]): Unit = {

  }

  /**
   * 求任意两根柱子组成的容器的最大盛水量
   *
   * 使用双指针：
   * 1.初始，i， j为两端，盛水量即为木桶原理： 容器宽度* 最短的那根柱子 =  (j-i) * min(height(i), height(j))，
   * 2.判断i，j两根柱子的高度：
   *  2.1 i为较短的那根柱子，那么j-- 移动，则宽度减少，此时容器的水位`上限`是 height(i)，
   *    因此j--是无意义的(盛水量只会更小)， i, j 组成的容器最大盛水量就是  (j-i) * height(i)
   *    应该移动i++
   *  2.2 j 为较短的那根柱子，那么i++移动，宽度减少，此时容器水位`上限` 是height(j),
   *    因此i++无意义(盛水量只会更小)，i, j 组成的容器最大盛水量就是  (j-i) * height(j)
   *    应该移动j--
   *
   * 3.循环处理2，直到i，j相遇，每轮不断更新最大盛水量
   *
   * 较短的柱子决定了水位上限，因此移动较高的柱子无用，盛水只会更少，这相当于剪枝，
   * 所以为了找到更多盛水的可能，接着移动较短的柱子(搏一搏，原先的位置相当于消失了)，缩小问题范围
   *
   * 证明这块，可以看官方题解的简单证明
   * @param height
   * @return
   */
  def maxArea(height: Array[Int]): Int = {
    var i = 0
    var j = height.length - 1
    var maxCap = 0
    while (i < j) {
      val width = j - i
      if (height(i) < height(j)) {
        if (width * height(i) > maxCap) maxCap = width * height(i)
        i += 1
      } else {
        if (width * height(j) > maxCap) maxCap = width * height(j)
        j -= 1
      }
    }
    return maxCap
  }
}



