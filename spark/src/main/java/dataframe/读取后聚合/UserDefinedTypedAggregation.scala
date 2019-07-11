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

    override def finish(reduction: Average): Double = ???

    override def bufferEncoder: Encoder[Average] = ???

    override def outputEncoder: Encoder[Double] = ???
  }

  def main(args: Array[String]): Unit = {
    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();

    import spark.implicits

    val ds=spark.read.json("e:\\data\\employees.json")



  }


}
