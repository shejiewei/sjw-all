package spark_core.分科排行前三

/**
  * Created by shejiewei on 2021/3/13.
  */
import java.net.URL

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, Partitioner, SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer


object Partion3 {


  def main(args: Array[String]) {

    val sparkConf: SparkConf = new SparkConf().setAppName("TestHashPartionTop3").setMaster("local[*]")

    val sc: SparkContext = new SparkContext(sparkConf)

    val file: RDD[String] = sc.textFile("./data/subject.txt")

    //1.先reduceByKey
    val res = file.map(x => {
      val split: Array[String] = x.split(" ")
      val url: String = split(1)
      (url, 1)
    })

    val sumedUrls: RDD[(String, Int)] = res.reduceByKey(_ + _)

    sumedUrls.foreach(println)

    //取出学科,用上缓存
    val cacheProjects:RDD[(String, (String, Int))] = sumedUrls.map(x => {
      val url = x._1
      val count = x._2
      val project = new URL(url).getHost
      (project, (url, count))
    }).cache()

    //利用hashPartition
    //    val partitionBy: RDD[(String, (String, Int))] = cacheProjects.partitionBy(new HashPartitioner(3))
    //    println(partitionBy.collect().toBuffer)

    //自定义Partition
    //先产生一个partition
    //先获得不同的key,用collect可以将RDD转化Array
    val keys: Array[String] = cacheProjects.map(x=>x._1).distinct().collect()
    println(keys)
    val myPartition: MyPartition = new MyPartition(keys)

    val by: RDD[(String, (String, Int))] = cacheProjects.partitionBy(myPartition)

    println(by.collect().toBuffer)
/*   by.mapPartitions(
     x=>{
       x.toList.sortBy(_._2._2).reverse.take(3).iterator
     }
   )*/

    val res2=by.mapPartitions(x=>{
      x.toList.sortBy(_._2._2).reverse.take(3).iterator
    })
    res2.foreach(println)

    //目的的不能存在，否则报错
  //  res2.saveAsTextFile("d:\\temp\\111\\out")
    sc.stop()
  }

}

/**
  *
  */
class MyPartition(myProjects: Array[String]) extends Partitioner {

  val projectsAndNum = new scala.collection.mutable.HashMap[String, Int]

  var n = 0//这个地方其实不应该代表数量，而是代表下标而已

  for (project <- myProjects) {
    projectsAndNum += (project -> n)
    n += 1
    println("===")
  }


  override def numPartitions: Int = myProjects.length

  //  override def getPartition(key: String): Any = {
  //    projectsAndNum.get(key)
  //  }
  override def getPartition(key: Any): Int = {
    projectsAndNum.getOrElse(key.toString,0)
  }
}
