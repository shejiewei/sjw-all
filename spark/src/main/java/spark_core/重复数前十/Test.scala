package spark_core.重复数前十

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * Created by shejiewei on 2021/2/25.
  */
object Test {
  def main(args: Array[String]): Unit = {


    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建Spark上下文对象

    var sc: SparkContext = new SparkContext(config)
    //map算子
    var listRDD: RDD[String] =  sc.makeRDD( List("a","b","a")) //这里的to 是包含  10的， unto 是不包含10 的

     listRDD.map(a=>(a,1)).reduceByKey((a,b)=>(a+b)).map(a=>(a._2,a._1)).sortByKey().take(3).foreach(println)
    sc.stop()
  }
}
