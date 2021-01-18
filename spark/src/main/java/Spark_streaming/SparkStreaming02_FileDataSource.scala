package Spark_streaming

import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.{DStream}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object SparkStreaming02_FileDataSource {
  def main(args: Array[String]): Unit = {
    //使用SparkStreaming 完成WordCount

    //SparkSQL
    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming01_WordCount")

    var streamingContext: StreamingContext = new StreamingContext(config, Seconds(3))//3 秒钟，伴生对象，不需要new

    //从文件夹中采集数据
    var FileDStreaming: DStream[String] = streamingContext.textFileStream("test/test.txt")


    //将采集的数据进行分解（偏平化）
    var WordDstream: DStream[String] = FileDStreaming.flatMap(line => line.split(" "))//偏平化后，按照空格分割

    //将我们的数据进行转换方便分析
    var mapDstream: DStream[(String, Int)] = WordDstream.map((_, 1))

    //将转换后的数据聚合在一起处理
    var wordToSumStream: DStream[(String, Int)] = mapDstream.reduceByKey(_ + _)

    //打印结果
    wordToSumStream.print()

    //streamingContext.stop()  //不能停止我们的采集功能

    //启动采集器
    streamingContext.start()

    //Drvier等待采集器停止，
    streamingContext.awaitTermination()

   //nc -lc 9999  linux 下往9999端口发数据。
  }
}
