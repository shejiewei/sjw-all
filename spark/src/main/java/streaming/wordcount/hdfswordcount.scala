package streaming.wordcount

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by shejiewei on 2019/6/28.
  */
object hdfswordcount {

  def main(args: Array[String]): Unit = {

    var conf = new SparkConf().set("spark.master", "local")
      .setAppName("hdfswordcount")

    val ssc = new StreamingContext(conf, Seconds(2))

    val lines = ssc.textFileStream("e:\\data\\test.txt")

    val words = lines.flatMap(_.split(" "))

    val wordCounts = words.map(x => (x, 1))
      .reduceByKey(_ + _)
    wordCounts.print()

    ssc.start()
    ssc.awaitTermination()

  }
  }
