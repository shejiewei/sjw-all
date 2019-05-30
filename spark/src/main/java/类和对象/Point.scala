package 类和对象

/**
  * Created by shejiewei on 2019/5/27.
  */
class Point(xc:Int,yc:Int) {
  var x:Int=xc
  var y:Int=yc

  def move(dx:Int,dy:Int): Unit =
{
   x=x+dx
  y=y+dy
   println("x的坐标点:"+x)
   println("y的坐标点:"+y)

}
}
