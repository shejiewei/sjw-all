package 机器学习.电影推测

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2020/3/1.
  */
object example {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf)
    val data=sc.textFile("./data/mllib/als/test.data")
    val ratings=data.map(_.split(',')match{
      case Array(user,item,rate)
        =>Rating(user.toInt,item.toInt,rate.toDouble)
    })
    ratings.collect().foreach {println}
      print()
  }
}
