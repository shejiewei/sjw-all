package scala.函数式

/**
  * Created by vi on 2018/6/8.
  */
object 将函数作为变量 {

  def main(args: Array[String]): Unit = {

   def modMethod(i:Int)=i%2==0  //list数组传入modMethod,调用该方法
    val list=List.range(1,10)
    list.filter(modMethod)
  }


}
