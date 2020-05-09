package kafka
import ml.kafka聚类.SparkStreamKafka.checkDir
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
/**
  * Created by shejiewei on 2020/5/8.
  */
object CosumerKafka {
  def main(args: Array[String]): Unit = {

    val conf=new SparkConf()
      .setAppName("sparkSreamKafka")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val ssc=new StreamingContext(sc,Seconds(1))
    ssc.checkpoint(checkDir)
    val kafkaParams=Map[String,Object](
      "bootstrap.servers" -> "192.168.93.6:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test-consumer-group",
      //如果没有记录偏移量,就消费最新的数据
      //spark 消费kafka中的偏移量自动维护: kafka 0.10之前的版本自动维护在zookeeper  kafka 0.10之后偏移量自动维护topic(__consumer_offsets)
      //开启自己动维护偏移量
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (true: java.lang.Boolean))

    val topics = Array("test")
    //直连方式
    val stream: InputDStream[ConsumerRecord[String, String]] = KafkaUtils.createDirectStream[String,String](ssc,
      LocationStrategies.PreferConsistent,
      ConsumerStrategies.Subscribe[String,String](topics,kafkaParams))
    stream.map(cr => cr.value()).print()
    //启动
    ssc.start()
    ssc.awaitTermination()
  }
}
