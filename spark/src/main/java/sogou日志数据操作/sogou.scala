package sogou日志数据操作

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/17.
  */
object sogou {
  def main(args: Array[String]): Unit = {
     val conf=new SparkConf().setAppName("sogou").setMaster("local[2]");
    val sc=new SparkContext(conf);

    val sogou=sc.textFile("hdfs://192.168.93.128:8020/data/sogou.txt");

    //缓存
    sogou.cache();
    println(sogou.count())

  }
}
