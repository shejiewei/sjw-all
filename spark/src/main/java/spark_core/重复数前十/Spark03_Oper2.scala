package spark_core.重复数前十

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}


object Spark03_Oper2 {
  def main(args: Array[String]): Unit = {
    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建Spark上下文对象

    var sc: SparkContext = new SparkContext(config)
    //map算子
    var listRDD: RDD[String] =  sc.makeRDD( List("a","b","a")) //这里的to 是包含  10的， unto 是不包含10 的

    var r=  listRDD.map(a=>(a,1)).reduceByKey((a,b)=>(a+b)).map(a=>(a._2,a._1)).sortByKey(false).take(10)
      .map(a=>(a._1,a._2))

     r.foreach(println)

  }
}
