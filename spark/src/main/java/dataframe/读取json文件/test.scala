package dataframe.读取json文件

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by shejiewei on 2019/6/26.
  */
object test {

  def main(args: Array[String]): Unit = {

    var conf =new SparkConf().set("spark.master","local")
   val spark=SparkSession.builder().appName("read").config(conf)
         .getOrCreate();

    val df=spark.read.json("e:\\data\\people.json");
    df.show();

    spark.stop();
  }

}
