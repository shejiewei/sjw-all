package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object for循环遍历集合 {

  def main(args: Array[String]): Unit = {
    var fruits=Array("aa","bb","cc")

    for(f<-fruits) println(f)



    val names=Map("fname"->"ff","aname"->"aa","cname"->"cc")


    for((k,v)<-names) println(s"key:$k,value:$v")


    }


}
