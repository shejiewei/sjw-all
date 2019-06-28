package HiveContext

import java.io.File

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}

/**
  * Created by shejiewei on 2019/6/28.
  */
object SparkHiveExample {

  case class Record(key:Int,value:String)
  def main(args: Array[String]): Unit = {

    val warehouseLocation = new File("spark-warehouse").getAbsolutePath

    var conf = new SparkConf().set("spark.master", "local")
    val spark = SparkSession.builder().appName("read").config(conf)
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .enableHiveSupport()
      .getOrCreate();

    import spark.implicits._
    import spark.sql

    sql("create table if not exists src(key INT,value STRING) USING hive")
    sql("load data local inpath '/data/kv1.txt' into table src")
    sql("select * from src").show()
    // +---+-------+
    // |key|  value|
    // +---+-------+
    // |238|val_238|
    // | 86| val_86|
    // |311|val_311|
    // ...

    sql("select count(*)from src").show()
    // +--------+
    // |count(1)|
    // +--------+
    // |    500 |
    // +--------+
    val sqlDF = sql("select key ,value from src where key <10 order by key")
    val stringDs = sqlDF.map{
      case Row(key:Int,value:String)=>s"Key:$key,Value:$value"
    }
   stringDs.show()
    // +--------------------+
    // |               value|
    // +--------------------+
    // |Key: 0, Value: val_0|
    // |Key: 0, Value: val_0|
    // |Key: 0, Value: val_0|



  }

}
