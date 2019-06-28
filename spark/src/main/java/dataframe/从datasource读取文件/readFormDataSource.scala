package dataframe.从datasource读取文件

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by shejiewei on 2019/6/27.
  */
object readFormDataSource {


  def main(args: Array[String]): Unit = {


    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();

    runBasicDataSourceExample(spark)

    spark.stop()
  }

  private def runBasicDataSourceExample(spark: SparkSession): Unit = {

    val userDF=spark.read.load("e:\\data\\users.parquet")
   // userDF.select("name","favorite_color").write.save("namesAndFav111.parquet")

    val peopleDF=spark.read.format("json").load("e:\\data\\people.json")
   //peopleDF.select("name","age").write.format("parquet").save("nameage.parquet")

    peopleDF.show()

/*    val peopleDFCsv=spark.read.format("csv")
              .option("sep","")
              .option("inferSchema","true")
              .option("header","true")
               .load("e:\\data\\people.csv")*/
  //peopleDFCsv.show()


    peopleDF.write.bucketBy(42,"name").sortBy("age").saveAsTable("people_bucket")
    userDF.write.partitionBy("favorite_color").format("parquet").save("namesPartByColor.parquet")
    // $example off:write_partitioning$
    // $example on:write_partition_and_bucket$
    userDF
      .write
      .partitionBy("favorite_color")
      .bucketBy(42, "name")
      .saveAsTable("users_partitioned_bucketed")
    // $example off:write_partition_and_bucket$

    spark.sql("DROP TABLE IF EXISTS people_bucketed")
    spark.sql("DROP TABLE IF EXISTS users_partitioned_bucketed")
  }


}
