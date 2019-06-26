package rdd和索引号组成键值对

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/17.
  */
object test {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);


    val intrdd=sc.parallelize(List("a","b"));

    //intrdd.map(x=x+1).collect()


    //intrdd.map(println(x ));
   // var rdd1=sc.makeRDD(Seq("A","B","C"),2)

   // rdd1.zipWithIndex().collect()

  }
   def  addOne(x:Int): Unit =
  {
    return (x+1)
  }
}
