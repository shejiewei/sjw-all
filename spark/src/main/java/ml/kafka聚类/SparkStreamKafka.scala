package ml.kafka聚类

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.kafka010._
import org.apache.spark.streaming.kafka010.LocationStrategies.PreferConsistent
import org.apache.spark.streaming.kafka010.ConsumerStrategies.Subscribe
import org.apache.spark.mllib.clustering.KMeansModel
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.rdd.RDD

/**
  * Created by shejiewei on 2020/5/7.
  */
object SparkStreamKafka {
  val checkDir="E:\\data\\sparkcheckpoint"

  def functionToCreateContext():StreamingContext={
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
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
   val topics=Array("kmeansTest")
    val stream=KafkaUtils.createDirectStream[String,String](
      ssc,
      PreferConsistent,
      Subscribe[String,String](topics,kafkaParams)
    )

    val kmodel=KMeansModel.load(sc,"file:///E:\\data\\model\\kmeansModel")

    stream.map(record=>(record.key,record.value)).foreachRDD(

      x=>{
        val y=x.filter(x=>x._2.nonEmpty) //非空判断

      }
    )


      ssc
  }

}
