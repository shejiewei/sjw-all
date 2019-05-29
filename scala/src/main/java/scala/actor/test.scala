package scala.actor

import akka.actor.{ActorSystem, Props}

/**
  * Created by shejiewei on 2019/5/27.
  */
object test {

  def main(args: Array[String]): Unit = {
    val  system=ActorSystem("HelloSystem")

    val helloactor=system.actorOf(Props[Hello],name="hellcoActor")
    helloactor!"hello"
    helloactor!"aa"

    system.shutdown()
  }
}
