package scala.列表数组映射

/**
  * Created by vi on 2018/6/14.
  */
object 合并列表 {

  def main(args: Array[String]): Unit = {
    val a=List(1,2,3)
    val b=List(23,4)
    val c=a:::b
    val d=a++b
    val cd=List.concat(a,b)
    for(i<-c) println(c)
  }

}
