package scala.集合

/**
  * Created by vi on 2018/6/11.
  */
object  list {

  trait  Animal
  trait FurryAnimal extends Animal
    case  class Dog(name:String)extends Animal
    case class Cat(name:String)extends  Animal



  def main(args: Array[String]): Unit = {

    val x=List[Number](1,2,3,4)
    x.foreach(x=>println(x))
    val x2=Array(Dog("dog"),Cat("fd"))

    x2.foreach(x=>println(x))
  }


}
