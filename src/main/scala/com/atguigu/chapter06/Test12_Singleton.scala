package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 2:55 下午
 * @function:
 */
object Test12_Singleton {
  def main(args: Array[String]): Unit = {
    val student1: Student12 = Student12.getInstance()
    student1.printInfo()
    val student2: Student12 = Student12.getInstance()
    // 打印引用地址，比较地址
    println(student1)
    println(student2)
    println(student1.eq(student2))

  }
}

class Student12 private(val name: String, val age: Int){
  def printInfo(): Unit ={
    // 访问Test11中的伴生对象的属性，把伴生对象Student修饰为private 这也可以访问
    println(s"student: name = ${name}, age = ${age}," +
      s" school = ${Student11.school}")
  }
}

// 饿汉式
//object Student12 {
//  // 定义私有的单例属性，然后提供public方法给外边访问
//  private val student: Student12 = new Student12("alice", 20)
//  def getInstance(): Student12 = student
//}

// 懒汉式，用到的时候在new。不像饿汉一上来就初始化
object Student12 {
  private var student: Student12 = _
  def getInstance(): Student12 ={
    if (student == null) {
      // 如果没有实例对象的话，就创建一个
      student = new Student12("alice", 20)
    }
    student
  }
}