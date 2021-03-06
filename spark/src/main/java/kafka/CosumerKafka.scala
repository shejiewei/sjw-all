package kafka
import ml.kafka聚类.SparkStreamKafka.checkDir
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable
 /**
  * Created by shejiewei on 2020/5/8.
  */
object CosumerKafka {
  def main(args: Array[String]): Unit = {

/*    val conf=new SparkConf()
      .setAppName("sparkSreamKafka")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val ssc=new StreamingContext(sc,Seconds(1))
    ssc.checkpoint(checkDir)
    val kafkaParams=Map[String,Object](
      "bootstrap.servers" -> "192.168.93.5:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test-consumer-group",
      //如果没有记录偏移量,就消费最新的数据
      //spark 消费kafka中的偏移量自动维护: kafka 0.10之前的版本自动维护在zookeeper  kafka 0.10之后偏移量自动维护topic(__consumer_offsets)
      //开启自己动维护偏移量
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (true: java.lang.Boolean))*/



    val conf = new SparkConf()
      .setAppName("DirectKafka")
      .setMaster("local[2]")

    val ssc = new StreamingContext(conf, Seconds(10))

    val topicsSet = Array("test")
    val kafkaParams = mutable.HashMap[String, String]()
    //必须添加以下参数，否则会报错
    kafkaParams.put("bootstrap.servers", "192.168.93.6:9092")
   // kafkaParams.put("bootstrap.servers", "192.168.93.5:9092")
    kafkaParams.put("group.id", "test-consumer-group")
    kafkaParams.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    kafkaParams.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    val topics = Array("test")
    //直连方式
    val messages = KafkaUtils.createDirectStream[String, String](
      ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String, String](topics, kafkaParams
      )
    )

    // Get the lines, split them into words, count the words and print
    val lines = messages.map(_.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()

    // Start the computation
    ssc.start()
    ssc.awaitTermination()


   /* val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String,String](ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics,kafkaParams))
    stream.map(cr => cr.value()).print()
    //启动
    ssc.start()
    ssc.awaitTermination()*/
  }
}
