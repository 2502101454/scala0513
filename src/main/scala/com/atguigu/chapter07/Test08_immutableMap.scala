package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-10 11:22 下午
 * @function:
 */
object Test08_immutableMap {
  def main(args: Array[String]): Unit = {
    // 1. 创建map
    val map1: Map[String, Int] = Map("a" -> 1, "b" -> 3, "c" -> 2)
    println(map1)
    // contains判断是否存在key
    println(map1.contains("c"))
    println(map1.getClass)
    // 不可变map不可以put元素
//    map1.put("v", 1)

    // 2. 遍历元素
    // 打印键值对 (a,1) ....
    map1.foreach(println)

    // 3. 取map中所有的key 或者 value
    for (key <- map1.keys) {
      println(s"$key ---> ${map1.get(key)}")
    }

    //4. 访问某一个key的value, map.get(key) 返回some(v)，再次调用get即v
    println("a: " + map1.get("a").get) //key不存在返回None，再get就报错
    println("d: " + map1.get("d"))
    // 常用: 如果key不存在则返回0，存在就直接返回v了，不用再get
    println("d: " + map1.getOrElse("d", 0))

    // 常用: 直接取v，key不存在抛异常，上面太麻烦了，还返回包装类型
    println(map1("a"))
  }

}
