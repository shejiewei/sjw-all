package sparksql

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * Created by shejiewei on 2019/4/12.
  */
object SQLContextApp {

  def main(args: Array[String]): Unit = {
    val sparkConf=new SparkConf().setAppName("SQLContextApp");
    //val sparkConf=new SparkConf().setAppName("SQLContextApp").setMaster("local[2]");
    val sc=new SparkContext(sparkConf)
    val sQLContext=  new SQLContext(sc);

    val word= sQLContext.read.format("json").load("hdfs://192.168.93.131:8020/word.txt");
    word.printSchema();
    word.show();

    sc.stop();

  }


}
