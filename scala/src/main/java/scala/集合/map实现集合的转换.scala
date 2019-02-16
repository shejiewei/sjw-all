package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object map实现集合的转换 {


  def main(args: Array[String]): Unit = {

    val helpers=Array("aaa","fdsfs")

    val caps=helpers.map(_.length)

    for(e<-caps) println(e)

  }
}
