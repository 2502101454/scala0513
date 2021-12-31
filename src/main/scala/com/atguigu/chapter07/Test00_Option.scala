package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-12-26 5:18 下午
 * @function: Option类型的使用 和 Map的引入
 */
object Test00_Option {
  def main(args: Array[String]): Unit = {
    /**
     *  Option类型 表示一个值是可选的，可有值或者无值
     *  使用时通常带泛型如Option[T]
     *     如果有值，Option[T]就是一个Some[T]；
     *     如果无值，Option[T]就是None
     *
     *  Some和None都继承子Option
     */
    val a: Option[Int] = Some(3)
    val b: Option[Int] = None

    // Option 的一些api
    // get方法，获取自身封装的值，注意如果是None的话，get就报错了
    println("a value get " + a.get) // 3
//    println("b value get " + b.get) // exception

    // getOrElse(默认值) 获取自身封装的值，有值返回原值，没值就返回默认值
    println("a value getOrElse " + a.getOrElse(0)) // 3
    println("b value getOrElse " + b.getOrElse(0)) // 0

    // isEmpty 判断有没有值
    println("a.isEmpty " + a.isEmpty) // false
    println("b.isEmpty " + b.isEmpty) // true

    // map.get(k) 返回的就是Option类型，用来兼容返回值为None的情况
    var map:Map[String, String] = Map("name"->"wz", "age"->"19")
    val name: Option[String] = map.get("name")
    // 获取封装的value太麻烦，一般使用map自身提供的取值方式: map(key)
    // map(key) 直接获取value，key不存在就exception
    println(map("name")) // wz
    // println(map("address")) // exception
    // map自身的getOrElse(key, 默认值)，也是直接获取value, 当前key不存在，返回默认值
    println(map.getOrElse("name", "xx"))
    println(map.getOrElse("address", "西安"))
  }
}
