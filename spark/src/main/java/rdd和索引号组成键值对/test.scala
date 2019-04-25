package rdd和索引号组成键值对

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/17.
  */
object test {
  def main(args: Array[String]): Unit = {

        val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);


    var rdd1=sc.makeRDD(Seq("A","B","C"),2)

    rdd1.zipWithIndex().collect()

  }
}
