package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object yield实现集合的转换 {

  case class Person(name:String )
  def main(args: Array[String]): Unit = {

    val a=Array(1,2,3,4,5)

    for (e<-a) yield e*2

    for (e<-a) yield (e,e.toDouble)

    val friends=Vector("aa","bb")

    for (f<-friends) yield Person(f)


  }
}
