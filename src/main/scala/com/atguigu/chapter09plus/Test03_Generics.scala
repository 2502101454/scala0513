package com.atguigu.chapter09plus

/**
 *
 * @author zengwang
 * @create 2021-11-13 2:25 下午
 * @function:
 */
object Test03_Generics {
  def main(args: Array[String]): Unit = {
    // 1. 协变和逆变
    val child: Parent = new Child
    val childList: MyCollection[Parent] = new MyCollection[Parent]
    val childList1: MyCollection1[Parent] = new MyCollection1[Child]
    val childList2: MyCollection2[SubChild] = new MyCollection2[Child]

    // 2. 上下限
    // 函数参数的泛型，写在参数列表前面
    def test[A <: Child](a: A): Unit = {
      println(a.getClass.getName)
    }
    // test[Parent](new Parent) // error
    // test[Parent](new Child) // error
    test[Child](new Child) // com.atguigu.chapter09plus.Child
    test[Child](new SubChild) // com.atguigu.chapter09plus.SubChild
    test[SubChild](new SubChild) // com.atguigu.chapter09plus.SubChild
    test(new SubChild) // com.atguigu.chapter09plus.SubChild

  }
}

// 定义继承关系
class Parent {}
class Child extends Parent {}
class SubChild extends Child {}

// 定义带泛型的集合类型, 一般是大写的字母
// 这里只是声明我需要一个类型，用的时候传给我具体的类型即可

class MyCollection[E] {} // 不变，泛型集合类之间无父子关系
class MyCollection1[+E] {} // 协变
class MyCollection2[-E] {} // 逆变