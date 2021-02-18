package spark_core.flumePull和push

/**
  * Created by shejiewei on 2021/2/17.
  */
import org.apache.spark.SparkConf
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.streaming.{Seconds, StreamingContext}

//spark对接flume数据： 流计算
object test{
  def main(args: Array[String]): Unit = {
    //spark配置
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    //流配置
    val ssc = new StreamingContext(conf, Seconds(1))

    //接收数据：创建avro监听服务
    val inputDstream:ReceiverInputDStream[SparkFlumeEvent]
    = FlumeUtils.createStream(ssc, "127.0.0.1", 4444)
    val tupDstream = inputDstream.map(event=>{
      val event1 = event.event
      val byteBuff = event1.getBody
      val body = new String(byteBuff.array())//ByteBuff.array()
      (body,1)
    }).reduceByKey(_+_)

    tupDstream.print()

    // 启动流
    ssc.start()
    ssc.awaitTermination()
  }
}
