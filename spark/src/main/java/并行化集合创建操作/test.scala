package 并行化集合创建操作

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/17.
  */
object test {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);
    val rdd=sc.parallelize(1 to 10);
    rdd.collect()
    println(rdd.partitions.size)

    //显示设置rdd为4个分区
    val rdd2=sc.parallelize(1 to 10,4);
    println(rdd2.partitions.size)



  }
}
