package spark_core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}
import scala.util.parsing.json.JSON

object Spark17_JSON {
  def main(args: Array[String]): Unit = {

    val config :SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建spark上下文对象
    val sc = new SparkContext(config)

    val json = sc.textFile("in/user.json")

    val result  = json.map(JSON.parseFull)

    result.foreach(println)
    //释放资源
    sc.stop()
  }


}