package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 5:28 下午
 * @function:
 */
object Test15_TraitOverlying {
  def main(args: Array[String]): Unit = {
    val student1 = new Student15
    student1.increase()
  }
}
trait Knowledge15 {
  var amount: Int = _
  def increase(): Unit = println("Knowledge increased")
}
trait Talent15 {
  def singing(): Unit
  def dancing(): Unit
  def increase(): Unit = println("Talent increased")
}
class Student15 extends Person13 with Talent15 with Knowledge15{
  override def singing(): Unit = println("singing")
  override def dancing(): Unit = println("dancing")

  override def increase(): Unit = {
    // 继承的特质之间没有任何关系时，super代表继承关系中最后一个特质
    super.increase()
  }
}