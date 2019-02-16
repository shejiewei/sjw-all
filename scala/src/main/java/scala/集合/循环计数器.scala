package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object 循环计数器 {

  def main(args: Array[String]): Unit = {
    val names = Array("aa", "bb", "cc")

    names.zipWithIndex.foreach {

      case (names, count) => println(s"$count is $names")

    }

    for((names,count)<-names.zip(Stream from 1)){
      println(s"$count is $names")
    }

  }
}
