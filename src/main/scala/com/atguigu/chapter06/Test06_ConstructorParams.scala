package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-05 1:24 下午
 * @function:
 */
object Test06_ConstructorParams {
  def main(args: Array[String]): Unit = {
    val student = new Student2
    student.name = "alice"
    student.age = 22
    println(s"${student.name} ${student.age}")

    val bob = new Student3(name = "Bob", age = 20)
    println(s"${bob.name} ${bob.age}")

    val sam = new Student4(name = "Sam", age = 28)
    // error 局部变量不是属性，无法实例调用
//    println(sam.age)
    sam.printInfo()

    val dean = new Student5("Dean", 20)
    // dean.age = 21 // error val 修饰参数，作为类只读属性使用，不能修改

    val faker = new Student6("Faker", 25, "LPL")
    faker.printInfo()
    // 调用对象方法修改对象的属性
    faker.setAttr("Faker liulang", "LPL Colleague")
    faker.printInfo()
  }
}


// 定义类
// 无参构造器
class Student2 {
  // 单独定义属性
  var name: String = _
  var age: Int = _
}

// 上面的定义等价于
class Student3(var name: String, var age: Int)

// 主构造器参数无修饰，只作为局部变量
class Student4(name: String, age: Int) {
  println(s"${name} ${age}")

  def printInfo(): Unit = {
    println(s"${name} ${age}")
  }

}
//这样也可以，但是非常不推荐，推荐使用Student3的方式
//class Student44(_name: String, _age: Int) {
//  var name: String = _name
//  var age: Int = _age
//}

// val 修饰参数，作为类只读属性使用，不能修改
class Student5(val name: String, val age: Int)


class Student6(var name: String, var age: Int) {
  var school: String = _

  def this(name: String, age: Int, school: String) {
    this(name, age)
    this.school = school
  }

  def printInfo(): Unit = {
    println(s"${this.name} ${this.age} ${this.school}")
  }

  // 方法中可以修改对象的属性
  def setAttr(_name: String, _school: String): Unit = {
    name = _name
    school = _school
  }
}