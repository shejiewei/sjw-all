package scala.枚举

/**
  * Created by shejiewei on 2019/6/11.
  */
object test {


  def main(args: Array[String]): Unit = {

     println(Level.High)
  }

  object Level extends  Enumeration
  {
    type  Level=Value
    val High,Low,Mid=Value
  }





}
