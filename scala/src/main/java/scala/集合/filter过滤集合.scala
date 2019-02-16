package scala.集合

/**
  * Created by vi on 2018/6/14.
  */
object filter过滤集合 {

  def main(args: Array[String]): Unit = {

    val x=List.range(1,10)

    val evens=x.filter(_%2==0)

    for(i<-evens) println(i)

    val list=Array("aa",32,34,"fd")
   val strings=list.filter {
     case s: String => true
     case _ => false
   }
   for(i<-list) println(i)

  }

}
