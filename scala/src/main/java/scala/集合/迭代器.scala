package scala.集合

/**
  * Created by vi on 2018/6/13.
  */
object 迭代器 {


  def main(args: Array[String]): Unit = {

    var it=Iterable(1,2,3)
    for (elem <- it) {
      print(elem)
    }
    it.foreach(println)
  }
}
