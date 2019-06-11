package scala.元组

/**
  * Created by shejiewei on 2019/6/11.
  */
object tunple {

  def main(args: Array[String]): Unit = {

    data(7)
    data(2)
    //运用元组之后
    println(data2(6))
    println(data2(3))

    //元组的拆包机制
    def f=(1,3.14,"aaa",false,"ss")
    val (a,b,c,d,e)=f

    println((a,b,c,d,e))
    val all=f
    println(f._1,f._2,f._3,f._4)



  }

   def data(input:Double)=
     if (input>5)
       ReturnBlob(input*2,"High")
     else
       ReturnBlob(input*2,"Low");


   def data2(input:Double):(Double,String)=
     if(input>5.0)
       (input*2,"High")
     else
       (input*2,"Low");




  case class ReturnBlob(data:Double,info:String)
  {
    println(data,info)
  }

}
