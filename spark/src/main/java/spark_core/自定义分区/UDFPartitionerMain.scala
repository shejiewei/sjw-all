package spark_core.自定义分区

/**
  * Created by shejiewei on 2021/3/13.
  */
import org.apache.spark.{SparkConf, TaskContext}
import org.apache.spark.sql.SparkSession
object UDFPartitionerMain {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName(this.getClass.getSimpleName)
    val ssc = SparkSession
      .builder()
      .config(conf)
      .getOrCreate()
    val sc = ssc.sparkContext
    sc.setLogLevel("WARN")

    val rdd = ssc.sparkContext.textFile("./data/cookie.txt")
    val transform = rdd.filter(_.split(",").length == 3).map(x => {
      val arr = x.split(",")
      (arr(0), (arr(1), arr(2)))
    })

    val keys: Array[String] = transform.map(_._1).collect()
    print(keys.toString)
    val partiion = transform.partitionBy(new UDFPartitioner(keys))
    partiion.foreachPartition(iter => {
      println(s"**********分区号：${TaskContext.getPartitionId()}***************")
      iter.foreach(r => {
        println(s"分区:${TaskContext.getPartitionId()}###" + r._1 + "\t" + r._2 + "::" + r._2._1)
      })
    })
    ssc.stop()
  }
}
