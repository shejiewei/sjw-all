package streaming

import org.apache.spark.streaming._
import org.apache.spark.streaming.StreamingContext._
import org.apache.spark.SparkContext
import org.apache.spark.api.java.function._
import org.apache.spark.streaming._
import org.apache.spark.streaming.api._

object SparkNCTest {
  def main(args: Array[String]): Unit = {
    // Create a StreamingContext with a local master
    // Spark Streaming needs at least two working thread
    val ssc = new StreamingContext("local[2]", "NetworkWordCount", Seconds(10))
    // Create a DStream that will connect to serverIP:serverPort, like localhost:9999
    val lines = ssc.socketTextStream("l92.168.93.5", 9999)
    // Split each line into words

    val words = lines.flatMap(_.split(" "))
    // Count each word in each batch
    val pairs = words.map(word => (word, 1))
    val wordCounts = pairs.reduceByKey(_+ _)
    wordCounts.print
    ssc.start
    ssc.awaitTermination
  }
}


