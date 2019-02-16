package scala.val和var

/**
  * Created by vi on 2018/6/8.
  */
object 构造类 {

   class Socket(val timeout:Int=1000,val linger:Int=2000){
     override def toString=s"timeout:$timeout,linger:$linger"

   }

  def main(args: Array[String]): Unit = {
    println(new Socket())
    println(new Socket(1003))
    println(new Socket(1004,2343))
  }

}
