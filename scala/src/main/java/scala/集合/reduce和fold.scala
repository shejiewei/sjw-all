package scala.集合

/**
  * Created by vi on 2018/6/14.
  */
object reduce和fold {
  def main(args: Array[String]): Unit = {

    val a=Array(12,34,53,53,62,66,7)
    val bb=a.reduceLeft(_+_)
    println(bb)
    val min=a.reduceLeft(_ min _)
    println(min)
    val findMax=(x:Int,y:Int)=>{

      val winnne=x max y
      println(s"$x to $y ,$winnne")
      winnne
    }
    val max=a.reduceLeft(findMax)
    println(max)
  }
}
