package scala.val和var

/**
  * Created by vi on 2018/6/8.
  */
object 伴生类 {
  class Person{
    var name:String =_
    var age=0
  }

  object Person
  {
    def apply(name: String): Person = {
    var p=new Person
    p.name=name
      p
  }

    def apply(name:String,age:Int): Person = {
      var p=new Person
      p.name=name
      p.age=age
      p

    }
  }

  def main(args: Array[String]): Unit = {
    //伴生类可以不用new关键字

    val p=Person("aa")
    val p2=Person("aa",22)
    println(p.name)
    println(p2.age)

  }
}
