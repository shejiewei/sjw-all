package scala.trait工厂方法

/**
  * Created by vi on 2018/6/8.
  */
object ani {
  class Dog extends Animal{

    override def speak ={
      println("dog")
    }

  }
  class Cat extends  Animal{
    override def speak ={
     println("cat")
    }
  }

  def apply(s:String): Animal = {
    if(s=="dog") new Dog
    else new Cat

  }

}
