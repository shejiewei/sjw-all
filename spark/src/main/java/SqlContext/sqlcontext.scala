package SqlContext

import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/15.
  */
object sqlcontext {

  def main(args: Array[String]): Unit = {


    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);





  }
}