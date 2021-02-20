package spark_core.mappartitions和map
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.mutable.ArrayBuffer

/**
  * Created by shejiewei on 2021/2/17.
  */
object test {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("map_mapPartitions_demo").setMaster("local"))
    val arrayRDD = sc.parallelize(Array(1, 2, 3, 4, 5, 6, 7, 8, 9), 2) //2个分区

    //map函数每次处理一个/行数据
    /* arrayRDD.map(element=>{
      element
    }).foreach(println)
*/ var  result = new ArrayBuffer[Int]();
    val ss = arrayRDD.mapPartitions( {
      iter => {

        iter.foreach(
          element => {
            result.+=(element)
          }
        )
        result.iterator
      }

    })
    ss.foreach(println)
    //mapPartitions每次处理一批数据
    //将 arrayRDD分成x批数据进行处理
    //elements是其中一批数据
    //mapPartitions返回一批数据（iterator）
      arrayRDD.mapPartitions(elements=>{
      var result = new ArrayBuffer[Int]()
      elements.foreach(element=>{
        result.+=(element)
      })
      result.iterator
    }).foreach(println)
  }

    //mapPartitions比较适合需要分批处理数据的情况，比如将数据插入某个表，每批数据只需要开启一次数据库连接，大大减少了连接开支，伪代码如下：
    /*  arrayRDD.mapPartitions(datas=>{
    dbConnect = getDbConnect() //获取数据库连接
    datas.foreach(data=>{
      dbConnect.insert(data) //循环插入数据
    })
    dbConnect.commit() //提交数据库事务
    dbConnect.close() //关闭数据库连接
  })*/

  /**
    * mapPartitions()出现内存溢出时的解决方法：
将数据切成较多的partition：
repartition(100).mapPartitions(xx)

设置较大的处理器内存
--executor-memory 8g
    */

}
