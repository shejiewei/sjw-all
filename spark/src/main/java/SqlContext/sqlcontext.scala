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
    val sqlcontext=new org.apache.spark.sql.SQLContext(sc)

    case class Person(name:String,age:Int)

    val people=sc.textFile("hdfs://192.168.93.128:8020/data/people.txt").map(_.split(",")).map(p=>Person(p(0),p(1).trim.toInt)).toDF()
  //  people.registerTempTable("people")

    val teenagers=sqlcontext.sql("select name,age from people where age >=10")

  // teenagers.map(t=>"Name"+t(0).collect().foreach(println))


  }
}