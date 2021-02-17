package spark_core.reduceBykey和groupbykey

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

/**
  * Created by shejiewei on 2021/2/17.
  */
object test {
  def main(args: Array[String]): Unit = {
    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建Spark上下文对象

    var sc: SparkContext = new SparkContext(config)
    val list = List("hadoop","spark","hive","spark")
    val rdd = sc.parallelize(list)
    val pairRdd = rdd.map((_,1))
    pairRdd.reduceByKey(_+_).collect.foreach(println)
/**
    pairRdd.reduceByKey(_+_).collect.foreach(println)
    等同于
    pairRdd.groupByKey().map(t => (t._1,t._2.sum)).collect.foreach(println)
*/

    val lis1t1 = List("hadoop","spark","hive","spark")
    val rdd1 = sc.parallelize(list)
    val pairRdd1 = rdd1.map(x => (x,1))
    //pairRdd1.groupByKey().collect.foreach(println)
    pairRdd.groupByKey().map(t => (t._1,t._2.sum)).collect.foreach(println)

    /**
      * 使用reduceByKey()的时候，本地的数据先进行merge然后再传输到不同节点再进行merge，最终得到最终结果。

而使用groupByKey()的时候，并不进行本地的merge，全部数据传出，得到全部数据后才会进行聚合成一个sequence，

groupByKey()传输速度明显慢于reduceByKey()。

虽然groupByKey().map(func)也能实现reduceByKey(func)功能，但是，优先使用reduceByKey(func
      */
  }
}
