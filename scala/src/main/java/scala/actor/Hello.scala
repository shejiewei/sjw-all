package scala.actor

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.collection.mutable
/**
  * Created by shejiewei on 2019/5/27.
  */
class Hello extends Actor{
   def receive={
     case "hello"=>println("hello aa")
     case _=> println("hi")
   }
}

object Main extends App{

  val  system=ActorSystem("helloSystem")

  val helloactor=system.actorOf(Props[Hello],name="hellcoActor")
  helloactor!"hello"
  helloactor!"aa"

  system.shutdown()

}
