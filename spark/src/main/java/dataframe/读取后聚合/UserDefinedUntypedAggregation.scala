package dataframe.读取后聚合

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.expressions.{MutableAggregationBuffer, UserDefinedAggregateFunction}
import org.apache.spark.sql.types._

/**
  * Created by shejiewei on 2019/7/11.
  */
object UserDefinedUntypedAggregation {

  object MyAverage extends UserDefinedAggregateFunction
{
  override def inputSchema: StructType = StructType(StructField("inputColumn",LongType)::Nil)

  override def bufferSchema: StructType = {
    StructType(StructField("sum", LongType) :: StructField("count", LongType) :: Nil)
  }

  override def dataType: DataType = DoubleType

  override def deterministic: Boolean = true

  override def initialize(buffer: MutableAggregationBuffer): Unit = {
    buffer(0)=0L
    buffer(1)=0L
  }

  override def update(buffer: MutableAggregationBuffer, input: Row): Unit = {
    if(!input.isNullAt(0))
      {
        buffer(0)=buffer.getLong(0)+input.getLong(0)
        buffer(1)=buffer.getLong(1)+1
      }

  }

  override def merge(buffer1: MutableAggregationBuffer, buffer2: Row): Unit = {

    buffer1(0)=buffer1.getLong(0)+buffer2.getLong(0)
    buffer1(1)=buffer1.getLong(1)+buffer2.getLong(1)
  }

  override def evaluate(buffer: Row): Double=buffer.getLong(0).toDouble/buffer.getLong(1)
}

  def main(args: Array[String]): Unit = {

    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();
    spark.udf.register("myAverage",MyAverage)

    val df=spark.read.json("e:\\data\\employees.json")

    df.createOrReplaceTempView("employees")
    df.show()
    // +-------+------+
    // |   name|salary|
    // +-------+------+
    // |Michael|  3000|
    // |   Andy|  4500|
    // | Justin|  3500|
    // |  Berta|  4000|
    // +-------+------+


    //自定义udf

    val  result=spark.sql("select myAverage(salary) as average_salary FROM employees")

    result.show()
    // +--------------+
    // |average_salary|
    // +--------------+
    // |        3750.0|
    // +--------------+
    // $example off:untyped_custom_aggregation$
    spark.stop()
  }


}
