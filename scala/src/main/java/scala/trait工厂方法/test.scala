package scala.trait工厂方法

/**
  * Created by vi on 2018/6/8.
  */
object test {


  def main(args: Array[String]): Unit = {
    val cat=ani("cat")
    cat.speak
    val dog=ani("dog")
    dog.speak
  }


}
