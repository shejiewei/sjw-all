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
  val checkDir="E:\\data\\sparkcheckpoint11"



  def main(args: Array[String]): Unit = {
    //从checkPoint中恢复数据

    val conf=new SparkConf()
      .setAppName("sparkSreamKafka")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val ssc=new StreamingContext(sc,Seconds(5))
    ssc.checkpoint(checkDir)
    val kafkaParams=Map[String,Object](
      "bootstrap.servers" -> "192.168.93.5:9092",
      "key.deserializer" -> classOf[StringDeserializer],
      "value.deserializer" -> classOf[StringDeserializer],
      "group.id" -> "test-consumer-group",
      "auto.offset.reset" -> "earliest",
      "enable.auto.commit" -> (false: java.lang.Boolean)
    )
    val topics=Array("test")
    val stream=KafkaUtils.createDirectStream[String,String](
      ssc,
      PreferConsistent,
      Subscribe[String,String](topics,kafkaParams)
    )

   val kmodel=KMeansModel.load(sc,"file:///E:\\data\\model\\kmeansModel")






    stream.foreachRDD(kafkaRDD=>{

        val lines= kafkaRDD.map(_.value())
     // println(lines)

        lines.map(line=>{
          println(line)
          /*val vector = Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))
          val predictedClusterIndex:
            Int =kmodel.predict(vector)
          println("The data " + vector.toString + " belongs to cluster " +
            predictedClusterIndex)*/
        })

    })
/*    val lines =Array(stream.map(_.value).flatMap(_.split(" ").map(_.toDouble)))
    val rdd3=sc.makeRDD(lines)
    val rdd = sc.textFile("file:///home/xuqm/ML_Data/input/synthetic_control.data").map(_.split("\\s+"))
    // 将rdd转换成LabeledPoint类型的RDD
    val LabeledPointRdd = rdd3.map(x=>Vectors.dense(x.map(_.toDouble))*/



/*
    val rdd = sc.makeRDD(Array(Array(1.0,10.1,2.5),Array(2.0,5.2,3.8)))
    val rdd2 = sc.makeRDD(Array(1.2,2.3,3.4))

    val data = rdd3.map(f=>Vectors.dense(f))

    Vectors.dense(lines.map(_.toDouble))
*/

   stream.map(_.value).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).print()
   /* val lines =stream.map(_.value)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1L)).reduceByKey(_ + _)
    wordCounts.print()*/
    // println("lines:"+lines)




    ssc.start()
    ssc.awaitTermination()
  }

}
