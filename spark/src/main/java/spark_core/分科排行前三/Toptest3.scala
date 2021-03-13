package spark_core.分科排行前三

/**
  * Created by shejiewei on 2021/3/13.
  */
import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object Toptest3 {


  def main(args: Array[String]) {

    val sparkConf: SparkConf = new SparkConf().setAppName("SparkTop3").setMaster("local[*]")

    val sc: SparkContext = new SparkContext(sparkConf)

    val file: RDD[String] = sc.textFile("./data/subject.txt")

    //1.先reduceByKey
    val res=file.map(x=>{
      val split: Array[String] = x.split(" ")
      val url: String = split(1)
      (url,1)
    })

    val sumedUrls: RDD[(String, Int)] = res.reduceByKey(_+_)

    //取出学科
    val res2=sumedUrls.map(x=>{
      val url=x._1
      val count=x._2
      val project=new URL(url).getHost
      (project,url,count)
    })

    //分组统计
    val values: RDD[(String, List[(String, String, Int)])] = res2.groupBy(_._1).mapValues(_.toList.sortBy(_._3).reverse.take(3))
    println(values.collect().toBuffer)
  }



}