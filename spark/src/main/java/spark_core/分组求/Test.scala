package spark_core.分组求

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession



/**
  * Created by shejiewei on 2021/3/14.
  */
object Test {

  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder()
      .appName(s"${this.getClass.getSimpleName}")
      .master("local[2]")
      .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      .config("spark.sql.parquet.compression.codec", "gzip")
      .getOrCreate()
    val sc: SparkContext = spark.sparkContext
    sc.setLogLevel("WARN")
    val inputPath = "./data/TopN.txt"
    //-------------------------------------------------------------------------------------------
    //以数组得形式进行返回
    val fileRdd= sc.textFile(inputPath)
    val rdd1 = fileRdd.map(v => {
      val strings = v.split(" ")
      (strings(0), strings(1))
    })
    rdd1.foreach(println)
    val rdd2 = rdd1.groupByKey().map(line => {
      (line._1, line._2.toList.sortWith(_.toInt > _.toInt).take(3))
    })
    rdd2.map(line=>{
      val list = line._2.toList
      import scala.collection.mutable.ListBuffer
      var buffer = new ListBuffer[Int]
      for(element <- list) buffer += element.toInt + 100
       var aa=buffer.toList

      (line._1,aa)
    }).collect().foreach(println)
    sc.stop()
  }

}
