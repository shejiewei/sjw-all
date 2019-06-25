package scala.枚举

/**
  * Created by shejiewei on 2019/6/11.
  */
object test {


  def main(args: Array[String]): Unit = {

     println(Level2.High)
     val ll=   new Level()
    ll.High
    val level=  checkLevel(ll)
    println(level)
  }
  object Level2 extends  Enumeration{


    type  Level=Value
    val High,Low,Mid=Value

  }

 def checkLevel(level:Level)=level match{
   case level.Low=>"Low"
   case level.High=>"High"
 }




}
