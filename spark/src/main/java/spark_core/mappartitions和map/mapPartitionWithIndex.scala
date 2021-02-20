package spark_core.mappartitions和map

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2021/2/17.
  */
object mapPartitionWithIndex {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext(new SparkConf().setAppName("map_mapPartitions_demo").setMaster("local"))
    var rdd1 = sc.makeRDD(1 to 5, 2)
    //rdd1有两个分区


    var rdd2 = rdd1.mapPartitionsWithIndex {
      (x, iter) => {
        var result = List[String]()
        var i = 0
        while (iter.hasNext) {
          i += iter.next()
        }
        result.::(x + "|" + i).iterator

      }
    }

    rdd2.foreach(println)
  }
}
