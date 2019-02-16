package scala.集合

/**
  * Created by vi on 2018/6/14.
  */
object 从集合中提取元素序列 {


  def main(args: Array[String]): Unit = {

    val x=(1 to 10).toArray
    val y=x.drop(3)
    for(i<-y) print(i)
    val y1=x.dropWhile(_<6)
    for(i<-y1) print(i)





  }
}
