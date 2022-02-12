package com.atguigu.chapter07

/**
 *
 * @author zengwang
 * @create 2021-11-08 11:02 下午
 * @function: 测试不可变数组
 */
object Test01_ImmutableArray {
  def main(args: Array[String]): Unit = {
    // 1.创建数组, int默认是0
    val arr: Array[Int] = new Array[Int](5)
    println(arr.mkString("-"))
    // 另一种方法创建，使用伴生对象的apply方法
    // 这里是不可变的数组，所以初始化时直接把值都给定了
    val arr2: Array[Int] = Array(12, 34, 21, 16, 10)
    // 数组内容的引用可以修改，但是数组自身无法修改，意味着不能插入、删除元素，只能修改元素的值
    // 2. 访问元素
    println(arr(0))
    arr(0) = 12
    println(arr(0))

    // 3. 数组的遍历
    // 1) 普通for循环
    // to 代表包含最后一个下标，until不包含
//    for (i <- 0 to arr.length) {
    for (i <- 0 until arr.length) {
      println(arr(i))
    }
    // 推荐使用这个 arr.indices = 0 until arr.length
    for (i <- arr.indices) println(arr(i))

    // 2) 直接打印所有元素，增强for循环
    for (elem <- arr2) println(elem)

    // 3) 迭代器
    val iter = arr2.iterator
    while (iter.hasNext) println(iter.next())

    println("---------------")
    // 4) 调用foreach 方法
    arr2.foreach( (elem: Int) => println(elem))
    //函数至简原则
    arr2.foreach(println)

    println(arr2.mkString("--"))

    // 4. 添加元素，返回一个新数组
    // +号在后，给数组末尾添加
    val newArr: Array[Int] = arr2.:+(73)
    println(newArr.mkString("--"))
    // +号在前，给数组头部添加
    val newArr2: Array[Int] = arr2.+:(30)
    println(newArr2.mkString("--"))

    // 简写
    // 给数组末尾加元素
    val newArr3 = newArr :+ 15
    println(newArr3.mkString(", "))
    // 给前面加元素 +:  给后面加是 :+ 可以追加多个元素，注意顺序
    val newArr4 = 19 +: 20 +: newArr2 :+ 21 :+ 22
    println(newArr4.mkString(", "))

    // 查找数组第一个元素给定元素值的下标，找不到返回-1
    val newArr5 = Array(1,2,3,4,2,1,6)
    println(newArr5.indexOf(2)) // 1
    println(newArr5.indexOf(3)) // 2
    println(newArr5.indexOf(5)) // -1
  }
}
