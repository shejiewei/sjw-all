package spark_core.spark和kafka.直连
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka010.{ConsumerStrategies, KafkaUtils, LocationStrategies}
import org.apache.spark.streaming.{Seconds, StreamingContext}
/**
  * Created by shejiewei on 2021/2/17.
  */
object test {
  def main(args: Array[String]): Unit = {
    //入口
    val conf: SparkConf = new SparkConf().setMaster("local[*]").setAppName("SparkStreamingKafkaWithDirect")
    val ssc = new StreamingContext(conf,Seconds(2))
    val kafkaParams = Map[String, Object](
      "bootstrap.servers" -> "127.0.0.1:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test-consumer-group",
      //如果没有记录偏移量,就消费最新的数据
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (true: java.lang.Boolean)
    )

    //spark 消费kafka中的偏移量自动维护: kafka 0.10之前的版本自动维护在zookeeper  kafka 0.10之后偏移量自动维护topic(__consumer_offsets)
    //开启自己动维护偏移量
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
