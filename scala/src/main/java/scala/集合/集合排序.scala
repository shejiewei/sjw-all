package scala.集合

import scala.collection.mutable.ListBuffer

/**
  * Created by vi on 2018/6/14.
  */
object 集合排序 {


  def main(args: Array[String]): Unit = {

    val  a=List(11111,34,55,65).sorted
    val b=List("fdsf","rewr","fd").sortWith(_ > _)
    for(i<-b) println(b)

    val bv=ListBuffer(12,43,5,6)
    bv-=12
    for(i<-bv) println(i)
  }
}
