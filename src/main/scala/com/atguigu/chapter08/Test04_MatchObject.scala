package com.atguigu.chapter08

/**
 *
 * @author zengwang
 * @create 2021-11-12 11:14 下午
 * @function:
 */
object Test04_MatchObject {
  def main(args: Array[String]): Unit = {
    val student = new Student("bob", 18)

    // 针对实例对象的 内容 进行匹配
    val result: String = student match {
      case Student("bob", 28) => "bob, 28"
      case _ => "Else"
    }
    println(result)
  }
}

class Student(val name: String, val age: Int)

object Student {
  def apply(name: String, age: Int): Student = new Student(name, age)
  // 必须实现一个unapply方法， 用来对对象属性进行拆解，从而比对，
  // 传入的student可能是null, 拆解就会报错，返回Option兼容一下
  def unapply(student: Student): Option[(String, Int)] = {
    if (student == null ) {
      // case object None extends Option[Nothing]
      None
    } else {
      // final case class Some[+A] extends Option[A]
      Some((student.name, student.age))
    }
  }
}