package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-06 2:49 下午
 * @function:
 */
object Test07_Inherit {
  def main(args: Array[String]): Unit = {
    var e1 = new Emp("wz", 20, 110)
    e1.printInfo()
    println("===" * 8)
    var e2 = new Emp("wz2", 20)
    e2.printInfo()

    println("----" * 8)
    // 多态：编译的时候是Person7类型，运行的时候根据传入的类型做调用
    def personInfo(person: Person7): Unit = {
      person.printInfo()
    }
    val teacher = new Teacher()
    val person = new Person7(nameParam = "wz3")
    personInfo(person)
    personInfo(e2)
    personInfo(teacher)
  }
}

// 同一包下面有别的Person类声明了，竟然不让重复定义
class Person7(nameParam: String) {
  var name: String = nameParam
  var age: Int = _

  def this(nameParam: String, ageParam: Int) {
    this(nameParam) //调用自身主构造器
    this.age = ageParam
    println("2.父类辅助构造器")
  }
  // 除了辅助构造器方法之外 都是主构造器方法体内容
  println("1.父类的主构造器")

  def printInfo(): Unit = {
    println(s"Person $name $age")
  }
}

// 调用子类的主构造器时，先调用父类的构造器(那么到底调用的是父类的主构造器呢还是辅助构造器呢？
// 由这里的extends在再声明的时候指定)
//class Emp(nameParam: String, ageParam: Int) extends Person7(nameParam, ageParam) {
class Emp(nameParam: String, ageParam: Int) extends Person7(nameParam) {
  var empNo: Int = _

  def this(nameParam: String, ageParam: Int, empNoParam: Int) {
    this(nameParam, ageParam)
    this.empNo = empNoParam
    println("4.子类的辅助构造器")
  }

  override def printInfo(): Unit = {
    // 不用写this也行，谁调就取谁的属性
    println(s"Emp ${name} ${this.age} ${this.empNo}")
  }
  println("3.子类主构造器")

}


class Teacher extends Person7(nameParam = "testTeacher") {
  override def printInfo(): Unit = {
    println("Teacher ...")
  }
}
