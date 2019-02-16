package scala.函数式

/**
  * Created by vi on 2018/6/11.
  */
object 返回函数的函数 {


  def saySomething(language:String)=(name:String)=>{
  language match {
    case "english" =>"hello"+name
    case "spanish"=>"hello"+name
  }

  }

  def main(args: Array[String]): Unit = {
    val hello=saySomething("english")
    val aa=hello("aa")
    println(aa)
    val span=saySomething("spanish")
    val bb=span("bb")
    println(bb)
  }


}
