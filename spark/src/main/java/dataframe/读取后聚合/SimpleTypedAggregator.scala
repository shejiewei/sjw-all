package dataframe.读取后聚合

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.catalyst.expressions.MutableDouble
import org.apache.spark.sql.{Encoder, Encoders, SparkSession}
import org.apache.spark.sql.expressions.Aggregator
/**
  * Created by shejiewei on 2019/7/12.
  */
object SimpleTypedAggregator {

   class TypeSum[IN](val f:IN=>Long)extends Aggregator[IN,Long,Long] {
     override def zero: Long = 0L

     override def reduce(b: Long, a: IN): Long = b+f(a)

     override def merge(b1: Long, b2: Long): Long =b1+b2

     override def finish(reduction: Long): Long = reduction

     override def bufferEncoder: Encoder[Long] = Encoders.scalaLong

     override def outputEncoder: Encoder[Long] = Encoders.scalaLong
   }

class TypedCount[IN](val f:IN=>Any) extends Aggregator[IN,Long,Long]{
  override def zero: Long = 0

  override def reduce(b: Long, a: IN): Long = {
    if (f(a)==null) b else b+1
  }

  override def merge(b1: Long, b2: Long): Long = b1+b2

  override def finish(reduction: Long): Long =reduction

  override def bufferEncoder: Encoder[Long] = Encoders.scalaLong

  override def outputEncoder: Encoder[Long] = Encoders.scalaLong
}

class TypedAverage[IN](val f:IN=>Double) extends Aggregator[IN,(Double,Long),Double]
  {
    override def zero: (Double, Long) = (0.0,0L)

    override def reduce(b: (Double, Long), a: IN): (Double, Long) = (f(a)+b._1,1+b._2)

    override def merge(b1: (Double, Long), b2: (Double, Long)): (Double, Long) = {
      (b1._1+b2._1,b1._2+b2._2)
    }

    override def finish(reduction: (Double, Long)): Double = reduction._1/reduction._2

    override def bufferEncoder: Encoder[(Double, Long)] ={
      Encoders.tuple(Encoders.scalaDouble,Encoders.scalaLong)
    }

    override def outputEncoder: Encoder[Double] = Encoders.scalaDouble

  }

  class TypedMin[IN](val f: IN => Double) extends Aggregator[IN,MutableDouble,Option[Double]] {
    override def zero: MutableDouble = null

    override def reduce(b: MutableDouble, a: IN): MutableDouble = {
      if (b == null) {
        new MutableDouble(f(a))
      } else {
        b.value = math.min(b.value, f(a))
        b
      }
    }

    override def merge(b1: MutableDouble, b2: MutableDouble): MutableDouble = {
      if (b1==null){
        b2
      }
      else if(b2==null)
        {
          b1
        }
      else
        {
          b1.value=math.min(b1.value,b2.value)
         b1
        }
    }

    override def finish(reduction: MutableDouble): Option[Double] =
    {
      if(reduction!=null){
        Some(reduction.value)
      }
      else
        {
          None
        }
    }

    override def bufferEncoder: Encoder[MutableDouble] =  Encoders.kryo[MutableDouble]

    override def outputEncoder: Encoder[Option[Double]] =  Encoders.product[Option[Double]]
  }
  class MutableLong(var value: Long) extends Serializable
  class MutableDouble(var value: Double) extends Serializable


  def main(args: Array[String]): Unit = {

    var conf =new SparkConf().set("spark.master","local")
    val spark=SparkSession.builder().appName("read").config(conf)
      .getOrCreate();

    import spark.implicits._
    val ds=spark.range(20).select(('id %3).as("key"),'id).as[(Long,Long)]

    println("input data")
    ds.show()

    println("running typed sum:")
    ds.groupByKey(_._1).agg(new TypeSum[(Long,Long)](_._2).toColumn).show()

   println("running typed count")

     ds.groupByKey(_._1).agg(new TypedCount[(Long,Long)](_._2).toColumn).show()
 println("running typed average")

    ds.groupByKey(_._1).agg(new TypedAverage[(Long,Long)](_._2.toDouble).toColumn).show()

    println("running typed min")

    ds.groupByKey(_._1).agg(new TypedMin[(Long,Long)](_._2).toColumn).show()
    spark.stop()
  }
}
