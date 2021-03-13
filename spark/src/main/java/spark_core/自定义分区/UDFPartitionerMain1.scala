package spark_core.自定义分区

/**
  * Created by shejiewei on 2021/3/13.
  */
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, TaskContext}

object UDFPartitionerMain1 {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName(this.getClass.getSimpleName)
    val ssc = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()
    val sc = ssc.sparkContext
    sc.setLogLevel("WARN")

    val rdd = ssc.sparkContext.textFile("./data/cookie.txt")
    val rdd1 = rdd.filter(_.split(",").length == 3).map(value => {
      val strings = value.split(",")
      (strings(0), (strings(1), strings(2)))

    })
    val keys = rdd1.map(_._1).collect()

    val partitionRDD = rdd1.partitionBy(new UDFPartitioner(keys))

    partitionRDD.foreachPartition(iter=>
      {
        iter.foreach(r=>{
          println(TaskContext.getPartitionId()+":"+ r._1 + "\t" + r._2 + "::" + r._2._1)
        })
      }
    )


    ssc.stop()
  }
}
