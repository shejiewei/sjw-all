package scala.伴随对象

/**
  * Created by shejiewei on 2019/6/11.
  */
object test {

  def main(args: Array[String]): Unit = {

    var a=new X;
    var b=new X;
    a.increment()
    b.increment()

    println(a.increment())


  }
  //伴随对象
  object X{
    var n:Int=0
  }
 class X{
    def increment()={
      X.n+=1;
      X.n
    }
  }


}
