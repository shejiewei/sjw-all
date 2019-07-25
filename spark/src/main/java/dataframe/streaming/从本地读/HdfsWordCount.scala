package dataframe.streaming.从本地读

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Created by shejiewei on 2019/7/12.
  */
object HdfsWordCount {

  def main(args: Array[String]): Unit = {


    var sparkconf =new SparkConf().set("spark.master","local")
      .setAppName("hdfswordcount")

    val ssc = new StreamingContext(sparkconf, Seconds(2))

    val lines=ssc.textFileStream("e:\\data")

    val words=lines.flatMap(_.split(" "))
    val wordcount=words.map(x=>(x,1)).reduceByKey(_+_)
    wordcount.print()
    ssc.start()
    ssc.awaitTermination()

  }


}
