package dataframe.读取json文件


import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{StringType, StructField, StructType}

/**
  * Created by shejiewei on 2019/6/26.
  */
object test {

  case class Person(name:String,age:Long)

  def main(args: Array[String]): Unit = {

    var conf =new SparkConf().set("spark.master","local")
   val spark=SparkSession.builder().appName("read").config(conf)
         .getOrCreate();
   //runBasicDataFrameExample(spark)
   //runDatasetCreationExample(spark)
   //runInferSchemaExample(spark)
   runProgrammaticSchemaExample(spark)

   spark.stop();
  }
 private def runProgrammaticSchemaExample(spark: SparkSession): Unit = {
  import spark.implicits._


  val peopleRDD=spark.sparkContext.textFile("e:\\data\\people.txt")
  val schemaString="name age"
  val fields=schemaString.split(" ")
            .map(fieldName=>StructField(fieldName,StringType,nullable = true))



  val schema=StructType(fields)

  val rowRDD=peopleRDD.map(_.split(","))
    .map(e=>Row(e(0),e(1).trim))

  val peopleDF=spark.createDataFrame(rowRDD,schema)

  peopleDF.createTempView("people")

  val results=spark.sql("select * from people")

  results.show()


 }


 private def runInferSchemaExample(spark: SparkSession): Unit = {

  import spark.implicits._

  val peopleDF=spark.sparkContext
                 .textFile("e:\\data\\people.txt")
                   .map(_.split(","))
                     .map(e=>Person(e(0),e(1).trim.toInt))
                       .toDF()

  peopleDF.createTempView("people")

  val teenagerDF=spark.sql("select * from people where age between 13 and 19")

  teenagerDF.map(teenager=>"Name:"+teenager(0)).show()

  teenagerDF.map(teenager=>"Name:"+teenager.getAs[String]("name")).show()

  implicit val mapEncoder=org.apache.spark.sql.Encoders.kryo[Map[String,Any]]
 teenagerDF.map(teenager=>teenager.getValuesMap[Any](List("name","age"))).collect()



 }



   private def runDatasetCreationExample(spark:SparkSession):Unit={

    import  spark.implicits._

    val caseClassDs=Seq(Person("aa",32)).toDS();
    println("  caseClassDs.show()")
    caseClassDs.show()


    val privateDS=Seq(1,2,3).toDS()

    privateDS.show()
    privateDS.map(_+1).collect()

    val path="e:\\data\\people.json"
    val peopleDS=spark.read.json(path).as[Person]

    peopleDS.show()

   }


 private def runBasicDataFrameExample(spark: SparkSession): Unit = {

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

 }


}
