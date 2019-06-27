package dataframe.读取json文件

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

/**
  * Created by shejiewei on 2019/6/26.
  */
object test {

  case class Person(name:String,age:Long)

  def main(args: Array[String]): Unit = {

    var conf =new SparkConf().set("spark.master","local")
   val spark=SparkSession.builder().appName("read").config(conf)
         .getOrCreate();

    val df=spark.read.json("e:\\data\\people.json");
    println("df.show()")
    df.show();

    import spark.implicits._
    println("df.printSchema()")
    df.printSchema()
    println("df.printSchema()")
    df.printSchema()
    println("df.select($\"name\",$\"age\"+1).show")
    df.select($"name",$"age"+1).show
    println("df.filter($\"age\">21).show()")
    df.filter($"age">21).show()

    println("   df.groupBy(\"age\").count().show()")
    df.groupBy("age").count().show()

    // Register the DataFrame as a SQL temporary view
   df.createOrReplaceTempView("people")
    println("val sqlDF=spark.sql(\"select * from people\")")
    val sqlDF=spark.sql("select * from people")
    sqlDF.show()
    // Global temporary view is tied to a system preserved database `global_temp`
    df.createGlobalTempView("people")
    println("  spark.sql(\"select * from global_temp.people\").show")
    spark.sql("select * from global_temp.people").show
    // Global temporary view is cross-session

    println("spark.newSession().sql(\"select * from global_temp.people\").show()")
    spark.newSession().sql("select * from global_temp.people").show()
    spark.stop();
  }

}
