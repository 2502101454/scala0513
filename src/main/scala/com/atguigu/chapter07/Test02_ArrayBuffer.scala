package com.atguigu.chapter07

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

/**
 *
 * @author zengwang
 * @create 2021-11-09 12:59 下午
 * @function:
 */
object Test02_ArrayBuffer {
  def main(args: Array[String]): Unit = {
    // 1.创建可变数组
    val arr1 = new ArrayBuffer[Int]()
    val arr2: ArrayBuffer[Int] = ArrayBuffer(23, 57, 92)

    println(arr1)  // 可变数组初始为空，ArrayBuffer()
    println(arr2) // 调用ArrayBuffer.toString方法，会打印出元素

    // 2.访问数组元素
    println(arr2(1))
    arr2(1) = 39
    println(arr2(1))
    // 数组的遍历和之前的不可变数组都一样
    for (i <- arr2.indices) println(arr2(i))
    println("-------")
    for (elem <- arr2) println(elem)
    val iter = arr2.iterator
    println("--------")
    while (iter.hasNext) println(iter.next())
    println("-------")
    arr2.foreach(println)

    // 3. 添加数组元素
    // :+ 运算符会返回新的数组，所以并不会修改可变数组自身
    arr1 :+ 12
    println(arr1) // ArrayBuffer()
    val newArr1 = arr1 :+ 11
    println(newArr1) // ArrayBuffer(11)

    // 4. 正确的方法来操作可变数组自身
    // 给可变数组末尾加元素
    arr1 += 19
    println(arr1) // ArrayBuffer(19)
    // 给可变数组头部加
    20 +=: arr1
    println(arr1) // ArrayBuffer(20, 19)

    // 所以对于可变数组建议使用方法操作
    // 数组末尾添加
    arr1.append(36)
    // 数组头部添加
    arr1.prepend(11, 76)
    // 数组某个下标处插入若干元素
    arr1.insert(1, 13, 59)
    println(arr1)

    // 把整个数组插入到数组中
    arr1.insertAll(2, newArr1)
    println(arr1)
    arr1.prependAll(newArr1)
    arr1.appendAll(newArr1)
    println(arr1)

    // 4. 删除元素，根据下标
    arr1.remove(3)
    // 从下标3的位置删除2个元素
    arr1.remove(1, 2)
    println(arr1)

    // 删除元素，根据指定值删除元素，这个值找不到的话什么也不做
    // 也只是删除第一个值是 11的元素
    arr1 -= 11
    println(arr1)

    // 5. 可变数组转换为不可变数组
    val arr: ArrayBuffer[Int] = ArrayBuffer(2, 3, 4)
    val newArray: Array[Int] = arr.toArray
    println(newArray.mkString(", "))
    println(arr)

    // 6. 不可变数组转换为可变数组
    val buffer: mutable.Buffer[Int] = newArray.toBuffer
    println(buffer) // ArrayBuffer(2, 3, 4)
    println(newArray)
  }

}
