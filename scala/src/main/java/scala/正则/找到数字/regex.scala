package scala.正则.找到数字

import scala.util.matching.Regex

/**
  * Created by vi on 2018/5/25.
  */
object regex {
  def main(args: Array[String]): Unit = {
   //匹配所有数字
     val numPattern=new Regex("[0-9]+")

     val address="123 cds f d"

     val address1="adf sdfd f fd f fd"
     val address2="df fd 23 4 5 5"  //foreach循环输出数字
     val match1=numPattern.findFirstIn(address) //找到第一个
     val match2=numPattern.findFirstIn(address1).getOrElse("not find") //没有找到
     val  match3=numPattern.findAllIn(address2).foreach{
      e=>println(e)
    }
     println(match1)
     println(match2)



  }
}
