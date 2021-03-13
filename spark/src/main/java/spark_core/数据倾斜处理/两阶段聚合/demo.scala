package spark_core.数据倾斜处理.两阶段聚合

import java.util.Random

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2021/2/20.
  */
object demo {

  def main(args: Array[String]): Unit = {

    var config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")
    var sc: SparkContext = new SparkContext(config)
    val array = new Array[Int](10000)
    for(i<-0 to 9999)
    {
       array(i)=new Random().nextInt(10)
    }

    val rdd=sc.parallelize(array)

    val mapRdd = rdd.map(a=>(a,1))
    mapRdd.countByKey().foreach(print)


    val wc=rdd.map(a=>(a,1)).reduceByKey((a,b)=>(a+b)).foreach(print)


   val tmpRdd=   mapRdd.map(x=>{
      val prifix=new Random().nextInt(10)
      (prifix+"_"+x._1,x._2)
    }).reduceByKey((a,b)=>(a+b))

    tmpRdd.map(x=>(x._1.split("_")(1),x._2)).reduceByKey((a,b)=>(a+b))
      .foreach(println)



  }

}
