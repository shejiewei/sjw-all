package wordcount

/**
  * Created by shejiewei on 2019/6/6.
  */

import java.util.Locale

import scala.util.Random



import org.apache.spark._
import org.apache.spark.io.SnappyCompressionCodec
import org.apache.spark.rdd.RDD
//import wordcount.EncryptionFunSuite
import org.apache.spark.serializer.JavaSerializer
import org.apache.spark.storage._
import org.apache.spark.util.io.ChunkedByteBuffer
object partition {

  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local", "test")
 /*   val list = List[Int](1, 2, 3, 4)
    val broadcast = sc.broadcast(list)
    val results = sc.parallelize(1 to 2).map(x => (x, broadcast.value.sum))*/

    Using_TorrentBroadcast_locally(sc);
    //assert(results.collect().toSet === Set((1, 10), (2, 10)))
  }

  def Using_TorrentBroadcast_locally(sc:SparkContext) {
    val list = List[Int](1, 2, 3, 4)
    val broadcast = sc.broadcast(list)
    val results = sc.parallelize(1 to 2).map(x => (x, broadcast.value.sum))
    println(results.count())
    println(broadcast.id)
    println(results.collect().toString)
  }

}
