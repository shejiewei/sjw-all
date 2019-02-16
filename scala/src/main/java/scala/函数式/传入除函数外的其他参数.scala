package scala.函数式

import com.sun.glass.ui.MenuItem.Callback

/**
  * Created by vi on 2018/6/11.
  */
object 传入除函数外的其他参数 {

  val sayHello=()=>println("hello")

  def executeTime(callback:()=>Unit,numTimes:Int)
  {
    for(i<-1 to numTimes)callback()
  }

  def main(args: Array[String]): Unit = {

    executeTime(sayHello,3)

  }

}
