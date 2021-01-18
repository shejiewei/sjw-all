package spark_core

import java.sql.PreparedStatement

import org.apache.spark.rdd.RDD
import org.apache.spark.util.LongAccumulator
import org.apache.spark.{SparkConf, SparkContext}



object Spark20_ShareData {
  def main(args: Array[String]): Unit = {

    val config: SparkConf = new SparkConf().setMaster("local[*]").setAppName("WordCount")

    //创建spark上下文对象
    val sc = new SparkContext(config)

    val dataRDD:RDD[Int] = sc.makeRDD(List(1,2,3,4),2)

    var sum :Int  = 0
    //使用累加器来共享变量，来累加数据

    //创建累加器对象
    var accumulator: LongAccumulator = sc.longAccumulator
    
    dataRDD.foreach {
      case i => {
        //执行累加器的累加功能
        accumulator.add(i)
      }
    }
    //获取累加器的值
    print("sum =" +accumulator.value)
    //释放资源
    sc.stop()
  }


}