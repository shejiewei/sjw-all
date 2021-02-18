package spark_core.flumePull和push

/**
  * Created by shejiewei on 2021/2/17.
  */
import java.net.{InetAddress, InetSocketAddress}
import java.nio.ByteBuffer

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.dstream.ReceiverInputDStream
import org.apache.spark.streaming.flume.{FlumeUtils, SparkFlumeEvent}
import org.apache.spark.streaming.{Seconds, StreamingContext}

//spark对接flume数据： 流计算
object FlumeStream {
  def main(args: Array[String]): Unit = {
    //spark配置
    val conf = new SparkConf().setMaster("local[*]").setAppName("test")
    //流配置
    val ssc = new StreamingContext(conf, Seconds(2))

    //接收flume数据：Push推送的方式  ( flume push数据 ---> spark 所在的sink)
    //    val inputDstream:ReceiverInputDStream[SparkFlumeEvent]
    //  = FlumeUtils.createStream(ssc, "127.0.0.1", 4444)
    //    val tupDstream = inputDstream.map(event=>{
    //      val event1 = event.event
    //      val body:ByteBuffer = event1.getBody
    //      val mesg = new String(byteBuff.array())//ByteBuff.array()
    //      (mesg,1)
    //    }).reduceByKey(_+_)

    //接收flume数据： pull 拉的方式 (spark pull 数据 <---flume的sink所在的tcp socket)
    val ncAddresses = Seq(new InetSocketAddress("127.0.0.1",4444))
    val inputDstream:ReceiverInputDStream[SparkFlumeEvent]= FlumeUtils.createPollingStream(
      ssc,
      ncAddresses,
      StorageLevel.MEMORY_ONLY
    )
    val tupDstream =inputDstream.map(event=>{
      val event1 = event.event
      val body:ByteBuffer = event1.getBody
      val mesg = new String(body.array())
      (mesg,1)
    }).reduceByKey(_+_)
    tupDstream.print()

    // 启动流
    ssc.start()
    ssc.awaitTermination()
  }
}