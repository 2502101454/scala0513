package com.atguigu.chapter06

/**
 *
 * @author zengwang
 * @create 2021-11-07 10:14 下午
 * @function:
 */
object Test15_TraitOverlying2 {
  def main(args: Array[String]): Unit = {
    println(new MyBall().describe())
  }
}

trait Ball {
  def describe(): String = "ball"
}

trait Color extends Ball {
  override def describe(): String = {
    "blue-" + super.describe()
  }
}
trait Category extends Ball {
  override def describe(): String = {
    "foot-" + super.describe()
  }
}

class MyBall extends Category with Color {
  override def describe(): String = {
//    "my ball is a " + super.describe()
    // 钻石问题，super默认是特质叠加顺序中的下一个特质
    // super[xxx] 可以指定调用顺序中的某个特质
    "my ball is a " + super[Category].describe()
  }
}