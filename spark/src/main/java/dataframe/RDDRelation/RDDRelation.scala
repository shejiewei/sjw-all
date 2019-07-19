package dataframe.RDDRelation

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.SaveMode

/**
  * Created by shejiewei on 2019/7/12.
  */
object RDDRelation {



  case class Record(key: Int, value: String)
  def main(args: Array[String]): Unit = {
    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();
    import spark.implicits._
    val df = spark.createDataFrame((1 to 100).map(i => Record(i, s"val_$i")))
    // Any RDD containing case classes can be used to create a temporary view.  The schema of the
    // view is automatically inferred using scala reflection.
    df.createOrReplaceTempView("records")

    println("result of select *")
    spark.sql("SELECT * FROM records").collect().foreach(println)

    val  count=spark.sql("select count(*) from records").collect().head.getLong(0)
   println(s"count(*):$count")

  }

}
