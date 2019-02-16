package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object 列表的扁平化 {


  def main(args: Array[String]): Unit = {

    val a=Array(Array(1,2),Array(3,4))
     val newa=a.flatten
    for(e<-newa) println(e)

    val list=List("dfdf","fdsfesdvxc")
    val newlist=list.flatten

    for(e<-newlist) println(e)
  }
}
