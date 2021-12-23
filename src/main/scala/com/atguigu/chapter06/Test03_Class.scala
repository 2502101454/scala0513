package com.atguigu.chapter06

import scala.beans.BeanProperty

/**
 *
 * @author zengwang
 * @create 2021-11-04 1:03 下午
 * @function:
 */
object Test03_Class {
  def main(args: Array[String]): Unit = {
    val student = new Student()

    // student.name // error，不能访问private属性
    println(student.age) // 0
    println(student.sex) // null
    student.setAge(20)
    println(student.age) // 20
    student.age = 21
    println(student.age) // 21
  }
}

// 定义一个类
class Student {
  // 定义属性
  private var name: String = "alice"
  // _ 表示默认值，只能用于变量，默认值意味着后面需要修改，所以无法用于常量
  // Int 默认值 0, Double是 0.0， String是null, Boolean 是false
  @BeanProperty
  var age: Int = _
  var sex: String = _
}
