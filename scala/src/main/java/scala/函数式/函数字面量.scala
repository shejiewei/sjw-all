package scala.函数式

/**
  * Created by vi on 2018/6/8.
  */
object 函数字面量 {

  def main(args: Array[String]): Unit = {
    val  x=List.range(1,10)
    val evens=x.filter((i:Int)=>i%2==0)  //得到偶数

    evens.foreach((i:Int)=>println(i))
     evens.foreach(println(_))
  }


}
