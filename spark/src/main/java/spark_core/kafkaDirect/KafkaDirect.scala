package spark_core.kafkaDirect

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}


/**
  * Created by shejiewei on 2021/2/17.
  */
object KafkaDirect {
  def main(args: Array[String]): Unit = {



    val Array(brokers,topics) = Array("127.0.0.1:9092","test")

    val sparkConf = new SparkConf().setAppName("KafkaReceiver").setMaster("local[3]")
    val ssc = new StreamingContext(sparkConf,Seconds(5))


    val topicsSet = topics.split(",").toSet
    val kafkaParams = Map[String,String]("metadata.broker.list"-> brokers)
    val messages= KafkaUtils.createDirectStream[String,String,StringDecoder,StringDecoder](
      ssc,kafkaParams,topicsSet
    )

    messages.print()
    messages.map(_._2).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()

    ssc.start()
    ssc.awaitTermination()
  }

}
