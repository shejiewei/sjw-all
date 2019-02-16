package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object testyeild {
  def upperReverse(s:String)={
   val upper= s.toUpperCase
    upper
  }
  def main(args: Array[String]): Unit = {

    val fruits=Array("aa","bb","cc")



    val newArray=for(e<-fruits) yield upperReverse(e)

    for(new1<-newArray) println(new1)


  }

}
