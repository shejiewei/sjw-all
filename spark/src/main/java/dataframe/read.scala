package dataframe

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/6/26.
  */
object read {


  def main(args: Array[String]): Unit = {

    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);
   // val lines=sc.textFile("e:\\data\\test.txt");

  //  val df=lines.toDF("line");



  }

}
