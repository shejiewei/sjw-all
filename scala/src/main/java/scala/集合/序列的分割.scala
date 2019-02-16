package scala.集合

/**
  * Created by vi on 2018/6/14.
  */
object 序列的分割 {

  def main(args: Array[String]): Unit = {
    val x=List(12,33,44,5,56,67)
    val y=x.groupBy(_>10)
    for(i<-y)  print(i)

    val y2=x.partition(_>10)


  }

}
