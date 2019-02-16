package scala.val和var

/**
  * Created by vi on 2018/6/8.
  */
object person {

  class Person(var name:String)
  class Person2(val name:String)
  def main(args: Array[String]): Unit = {
    //当变量var name 为var类型时可以修改
    val p=new Person("fdsf")
    println(p.name)
    p.name="fdsfsdfdfsd"
    println(p.name)
    //当变量var name 为val类型时不可以修改
    val p2=new Person2("fdsf")
    println(p2.name)
    //p2.name="fdsfsdfdfsd"
    println(p2.name)
  }

}
