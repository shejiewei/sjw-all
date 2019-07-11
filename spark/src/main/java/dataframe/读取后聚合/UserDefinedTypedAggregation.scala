package dataframe.读取后聚合

import org.apache.spark.SparkConf
import org.apache.spark.sql.{Encoder, Encoders, SparkSession}
import org.apache.spark.sql.expressions.Aggregator

/**
  * Created by shejiewei on 2019/7/11.
  */
object UserDefinedTypedAggregation {

  case class Employee(name:String,salary:Long)
  case class Average(var sum:Long,var count:Long)

  object MyAverage extends Aggregator[Employee,Average,Double]
  {

    def zero:Average=Average(0L,0L)

    def reduce(buffer:Average,employee:Employee):Average={

      buffer.sum+=employee.salary
      buffer.count+=1
      buffer
    }
    def merge(b1:Average,b2:Average):Average={
      b1.sum+=b2.sum
      b1.count+=b2.count
      b1
    }

   def finish(reduction: Average): Double=reduction.sum.toDouble/reduction.count

  def bufferEncoder: Encoder[Average] = Encoders.product

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble
  }

  def main(args: Array[String]): Unit = {
    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();

    import spark.implicits._

    val ds=spark.read.json("e:\\data\\employees.json").as[Employee]

    ds.show()
    // +-------+------+
    // |   name|salary|
    // +-------+------+
    // |Michael|  3000|
    // |   Andy|  4500|
    // | Justin|  3500|
    // |  Berta|  4000|
    // +-------+------+
    val averageSalary=MyAverage.toColumn.name("average_salary")
    val result=ds.select(averageSalary)

    result.show()
    // +--------------+
    // |average_salary|
    // +--------------+
    // |        3750.0|
    // +--------------+
    // $example off:typed_custom_aggregation$
    spark.stop()

  }


}
