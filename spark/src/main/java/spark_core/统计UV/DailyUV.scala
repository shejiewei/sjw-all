package spark_core.统计UV

/**
  * Created by shejiewei on 2021/2/26.
  */

  import org.apache.spark.sql.{Row, SQLContext}
  import org.apache.spark.sql.types._
  import org.apache.spark.{SparkConf, SparkContext}
  import org.apache.spark.sql.functions._

  object DailyUV {
    def main(args: Array[String]): Unit = {
      val conf = new SparkConf().setAppName("dailyuv")
      val sc = new SparkContext(conf)
      val sqlContext = new SQLContext(sc)

      val userAccesslog = Array(
        "2017-01-01,1122",
        "2017-01-01,1122",
        "2017-01-01,1123",
        "2017-01-01,1124",
        "2017-01-01,1124",
        "2017-01-02,1122",
        "2017-01-01,1121",
        "2017-01-01,1123",
        "2017-01-01,1123"

      )
      val AccesslogRDD = sc.parallelize(userAccesslog,2)
      //val AccesslogRDD = sc.textFile("hdfs://master:9000/student/2016113012/data/userAccesslog.txt").map(_.split(","))
      //通过StructType直接指定每个字段的schema
      val schema = StructType(
        Array(
          StructField("date",StringType,true),
          StructField("userid",IntegerType,true)
        )
      )

      //j将普通rdd映射到rowRDD
      val RowRDD = AccesslogRDD.map(log => Row(log.split(",")(0),log.split(",")(1).toInt))
      //将schema信息映射到RowRDD上,即创建dataframe
      val df = sqlContext.createDataFrame(RowRDD,schema)
      //要使用spark SQL的内置函数需导入SQLContext下的隐士转换

      import sqlContext.implicits._
    //  df.groupBy("date") //根据日期分组
    //    .agg('date,countDistinct('userid))//根据日期聚合，然后根据用户id，注意这里的语法是‘引号
     //   .map(row => Row(row(1),row(2))).collect().foreach(println)


      //uv含义和业务，每天都有很多用户访问，每个用户可能每天访问很多次，uv指的是对用户进行去重以后的访问次数




    }

  }


