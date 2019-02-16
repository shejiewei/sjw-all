package scala.函数式

/**
  * Created by vi on 2018/6/11.
  */
object 闭包 {

  class Foo {

    def exec(f:(String)=>Unit,name:String): Unit =
    {
      f(name)
    }
  }

  object ClosureExample extends  App{

    var hello="Hello"
    def sayHello(name:String): Unit ={

      println(s"$hello,$name")
    }
     val foo=new Foo
     foo.exec(sayHello,"AI")
     hello="hola"
    foo.exec(sayHello,"LL")
  }

  def main(args: Array[String]): Unit = {
    ClosureExample


  }

}
