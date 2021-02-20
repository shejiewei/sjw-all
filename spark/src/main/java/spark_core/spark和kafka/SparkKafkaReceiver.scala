package spark_core.spark和kafka

/**
  * Created by shejiewei on 2021/2/17.
  */
import kafka.serializer.StringDecoder
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object SparkKafkaReceiver {

  private val topics = "receiver-test"
  private val HDFS_PATH = "hdfs://node01:9000/kafka-ck"
  private val numThreads = 1

  def main(args: Array[String]): Unit = {
    //当应用程序停止的时候,会将当前批次的数据处理完成后在停止
    System.setProperty("spark.streaming.stopGracefullyOnShutdown", "true")
    //1000*分区数*采样时间=拉取数据量
    System.setProperty("spark.streaming.kafka.maxRatePerPartition", "1000")
    val conf = new SparkConf().setMaster("local[2]").setAppName("receiver")
      //设置监控级别
      .set("spark.metrics.conf.executor.source.jvm.class", "org.apache.spark.metrics.source.JvmSource")

    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))
    ssc.checkpoint(HDFS_PATH)
    val kafkaParams = Map(
      "metadata.broker.list" -> "node01:9091,node02:9092,node03:9092",
      "zookeeper.connect" -> "node01:2181,node02:2181,node03:2181",
      "group.id" -> "receiver"
    )

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap
    val kafkaDStream = KafkaUtils.createStream[String, String, StringDecoder, StringDecoder](
      ssc, kafkaParams, topicMap, StorageLevel.MEMORY_AND_DISK_2)

    // word count
    kafkaDStream
      .map(_._2) // 1是分区号,2是具体kafka中数
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .print(10) // 输出结果

    ssc.start()
    ssc.awaitTermination()
  }