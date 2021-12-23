package com.atguigu.chapter01


/**
 *
 * @author zengwang
 * @create 2021-09-30 4:35 下午
 *
 */

/*
  object: 关键字，声明一个单例对象(伴生对象)
 */
object HelloWorld {
  /*
    main 方法：从外部可以直接调用执行的方法
    方法的定义:
      def 方法名称(参数名: 参数类型): 返回值类型 = { 方法体 }

      (Unit代表空返回值)
   */
  def main(args: Array[String]): Unit = {
    println("Hello scala")
    // 使用java 特有的语法
    System.out.println("hello scala")
  }
}
