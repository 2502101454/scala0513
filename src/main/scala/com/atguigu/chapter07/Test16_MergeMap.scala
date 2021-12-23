package com.atguigu.chapter07

import scala.collection.mutable

/**
 *
 * @author zengwang
 * @create 2021-11-12 8:37 上午
 * @function:
 */
object Test16_MergeMap {
  def main(args: Array[String]): Unit = {
    val map1 = Map("a" -> 1, "b" -> 3, "c" -> 6)
    val map2 = mutable.Map("a" -> 1, "b" -> 3, "c" -> 6)
    // fold底层虽然是foldLeft，但是foldLeft定义的参数类型可以不相同
    // 参考源码就能理解这里的参数排列
    val map3: mutable.Map[String, Int] = map1.foldLeft(map2)(
      (mergedMap, map1KV) => {
        val key = map1KV._1
        val value = map1KV._2
        mergedMap(key) = mergedMap.getOrElse(key, 0) + value
        mergedMap
      }
    )

    println(map3)
  }
}
