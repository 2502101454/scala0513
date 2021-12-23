package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 2:20 下午
 * @function:
 */
object Test11_Object {
  def main(args: Array[String]): Unit = {
//    val wz = new Student11(name = "wz", age = 20)
//    wz.printInfo()

//    val sam: Student11 = Student11.newStudent("sam", 20)
//    sam.printInfo()

//    val sam: Student11 = Student11.apply("sam", 20)
    //  编译器底层有调用时对apply有简化, 可以省略apply
    val sam: Student11 = Student11("sam", 20)
    sam.printInfo()

  }
}


// 定义类
//class Student11 (val name: String, val age: Int){
// 构造方法私有化
class Student11 private(val name: String, val age: Int){
  def printInfo(): Unit ={
    // 访问伴生对象的属性
    println(s"student: name = ${name}, age = ${age}," +
      s" school = ${Student11.school}")
  }
}

// 伴生对象,里面都是全局只有一份的静态内容
object Student11{
  val school: String = "atguigu"

  // 定义一个类的对象实例的创建方法, 这里访问了伴生类的私有的构造器
  def newStudent(name: String, age: Int): Student11 = new Student11(name, age)

  // 定义apply方法.
  def apply(name: String, age: Int): Student11 = new Student11(name, age)
}