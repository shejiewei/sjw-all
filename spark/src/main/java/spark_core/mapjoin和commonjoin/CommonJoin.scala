package spark_core.mapjoin和commonjoin

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2021/2/17.
  */
object CommonJoin {
  def main(args: Array[String]): Unit = {
    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建Spark上下文对象

    var sc: SparkContext = new SparkContext(config)
    val rdd1=sc.parallelize(List((1,"a"),(2,"b"),(1,"c"),(2,"d"),(3,"e")))


         val data=sc.parallelize(List((1,"AA"),(2,"BB")))


     rdd1.join(data).foreach(println(_))

    /**
      * ui
普通join会产生shuffle
      */
  }
}
