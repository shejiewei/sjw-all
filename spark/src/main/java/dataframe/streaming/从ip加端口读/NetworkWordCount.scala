package dataframe.streaming.从ip加端口读

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by shejiewei on 2019/7/12.
  */
object NetworkWordCount {

  def main(args: Array[String]): Unit = {


    var sparkconf =new SparkConf().set("spark.master","local")
      .setAppName("hdfswordcount")
    val  ssc=new StreamingContext(sparkconf,Seconds(1))

    val lines=ssc.socketTextStream("192.168.2.45",9999.toInt,StorageLevel.MEMORY_ONLY)
    val words=lines.flatMap(_.split(" "))
   val wordcount=words.map(x=>(x,1)).reduceByKey(_+_)
   wordcount.print()
    ssc.start()
    ssc.awaitTermination()

  }


}
