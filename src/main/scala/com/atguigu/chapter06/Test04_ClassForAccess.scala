package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-04 10:58 下午
 * @function:
 */
object Test04_ClassForAccess {

}


// 定义一个父类
class Person {
  private var idCard: String = "3829384"
  // 同类、子类可以访问，同包不可以
  protected var name: String = "alice"
  var sex: String = "female"
  private [chapter06] var age: Int = 18

  def printInfo(): Unit = {
    println(s"Person: $idCard $name $sex $age")
  }
}