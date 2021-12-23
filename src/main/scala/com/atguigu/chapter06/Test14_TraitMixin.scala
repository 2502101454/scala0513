package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 4:49 下午
 * @function:
 */
object Test14_TraitMixin {
  def main(args: Array[String]): Unit = {
    val student1 = new Student14
    student1.study()
    student1.increase()
    student1.play()
    student1.increase()
    student1.dating()
    student1.increase()
    println("------------------")
    // 动态混入，不用一开始就写在类的定义处，而是需要的时候在进行继承
    // 有的同学有天赋
    val studentWithTalent: Student14 with Talent = new Student14 with Talent {
      override def singing(): Unit = println("student is good at singing")

      override def dancing(): Unit = println("student is good at dancing")
    }

    studentWithTalent.sayHello()
    studentWithTalent.play()
    studentWithTalent.dating()
    studentWithTalent.study()
    studentWithTalent.increase()
    studentWithTalent.singing()
    studentWithTalent.dancing()
  }
}

// 在定义一个特质
trait Knowledge {
  var amount: Int = _
  def increase(): Unit
}

trait Talent {
  def singing(): Unit
  def dancing(): Unit
}
class Student14 extends Person13 with Young with Knowledge {
  // 父类和特质中都有一个name属性，运行会error，不知道该用哪个name
  // 重写冲突的属性
  override val name: String = "student"

  // 实现抽象方法
  def dating(): Unit = {
    println(s"student $name is dating")
  }
  // 声明自己的方法
  def study(): Unit = {
    println(s"student $name is studying")
  }

  // 重写父类的方法
  override def sayHello(): Unit = {
    // 这里使用super调用的时候，父类sayHello中的name属性也会因为动态绑定变成student
    super.sayHello()
    println(s"hello from: student $name")
  }

  // 实现特质中的抽象方法
  def increase(): Unit = {
    // 继承了特质中的属性
    amount += 1
    println(s"student $name knowledge increased: $amount")
  }

}