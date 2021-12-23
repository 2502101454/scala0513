package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-04 10:58 下午
 * @function:
 */
object Test04_Access {
  def main(args: Array[String]): Unit = {
    // 创建类对象
    val person: Person = new Person()
    // person.idCard // error 类的私有属性不能在类的外部访问
    // person.name // error 类的protected 属性只能在自己或者其子类中访问，这里是子类伴生对象了
    println(person.sex)
    println(person.age)

    person.printInfo()

    val worker = new Worker()
    worker.printInfo()

  }
}

// 定义一个子类
class Worker extends Person {
  // 重写父类的方法
  override def printInfo(): Unit = {
    // 访问父类私有属性
    // println(idCard) //  私有属性只能在类内部或者其伴生对象中访问

    // 访问父类protected属性
    name = "bob"
    // 访问父类的public属性
    age = 25
    // 访问父类定义的包访问的属性
    sex = "male"
    println(s"Worker: $name $sex $age")
  }
}
