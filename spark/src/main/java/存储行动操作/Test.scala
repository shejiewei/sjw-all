package 存储行动操作

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/18.
  */
object Test {
  def main(args: Array[String]): Unit = {

    val conf=new SparkConf().setAppName("Test").setMaster("local[2]");
    val sc=new SparkContext(conf);
    var rdd1=sc.makeRDD(1 to 10,2)
    var rdd2=sc.makeRDD(1 to 10,2)
    var rdd3=sc.makeRDD(1 to 10,2)
    rdd1.saveAsTextFile("hdfs://192.168.93.128:8020/data/file1.txt")
    rdd2.saveAsObjectFile("hdfs://192.168.93.128:8020/data/file2.txt")

  }
}
