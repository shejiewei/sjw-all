package HiveContext

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2019/4/15.
  */
object hiveContext {

  def main(args: Array[String]): Unit = {


    val conf=new SparkConf().setAppName("HiveContext").setMaster("local[2]");
    val sc=new SparkContext(conf);
    val hiveContext=new HiveContext(sc);

   hiveContext.table("emp").show()
    sc.stop();

  }

  /**
    *spark-submit \
    * --class hiveContext.hiveContext \
    * --jars mysql-connextor.jar   加mysql驱动
    * ./xxx.jar
    *
    *
    *
    */
}
