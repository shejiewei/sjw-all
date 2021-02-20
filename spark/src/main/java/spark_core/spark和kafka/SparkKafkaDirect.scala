package spark_core.spark和kafka

/**
  * Created by shejiewei on 2021/2/17.
  */
import kafka.common.TopicAndPartition
import kafka.message.MessageAndMetadata
import kafka.serializer.StringDecoder
import org.I0Itec.zkclient.ZkClient
import org.I0Itec.zkclient.exception.{ZkNoNodeException, ZkNodeExistsException}
import org.apache.log4j.Logger
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.InputDStream
import org.apache.spark.streaming.kafka.{HasOffsetRanges, KafkaUtils, OffsetRange}
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.zookeeper.data.Stat

object SparkKafkaDirect {
  private val zkHosts = "node01:2181,node02:2181,node03:2181"
  private val logger = Logger.getLogger("SparkKafkaDirect")
  private val zkPath = "/kafka-direct-test"
  private val topic = Set("direct-test")
  private val HDFS_PATH = "hdfs://node01:9000/kafka-ck"

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("receiver")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(5))
 //   val ssc = new StreamingContext(sc, Seconds(5))

    val kafkaParams = Map(
      "metadata.broker.list" -> "node01:9091,node02:9092,node03:9092",
      "group.id" -> "direct"
    )

    val zkClient: ZkClient = new ZkClient(zkHosts)

    // 读取 offset
    val offsets: Option[Map[TopicAndPartition, Long]] = readOffset(zkClient)

    // 获取到kafka数据
    val kafkaDStream: InputDStream[(String, String)] = offsets match {
      // 使用 direct方式消费kafka数据
      case None =>
        print("start from scratch")
        KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topic)
      case Some(offset) =>
        print("start with the offset")
        val messageHeader = (mmd: MessageAndMetadata[String, String]) => (mmd.key(), mmd.message())
        KafkaUtils.createDirectStream[String, String, StringDecoder,
          StringDecoder, (String, String)](ssc, kafkaParams, offset, messageHeader)
    }

    // word count
    kafkaDStream.map(_._2) // 1是分区号,2是具体kafka中数
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .foreachRDD(print(_)) // 输出结果

    // 保存偏移量到zk中 , 也可自定义到其他存储介质
    kafkaDStream.foreachRDD(rdd =>
      saveOffset(zkClient, zkHosts, zkPath, rdd)
    )

    ssc.start()
    ssc.awaitTermination()

  }

  // 保存 offset
  def saveOffset(zkClient: ZkClient, zkHost: String, zkPath: String, rdd: RDD[_]): Unit = {
    logger.info("save offsets to Zookeeper")
    val stopwatch = new Stopwatch()
    val offsetsRanges: Array[OffsetRange] = rdd.asInstanceOf[HasOffsetRanges].offsetRanges
    offsetsRanges.foreach(offsetRange => logger.debug(s"  Using $offsetRange"))
    val offsetsRangesStr = offsetsRanges.map(offsetRange => s"${offsetRange.partition}:${offsetRange.fromOffset}")
      .mkString(",")
    logger.info("writing offsets to Zookeeper zkClient=" + zkClient + "  zkHosts=" + zkHosts + "zkPath=" + zkPath + "  offsetsRangesStr:" + offsetsRangesStr)
    updatePersistentPath(zkClient, zkPath, offsetsRangesStr)
    logger.info("done updating offsets in zookeeper. took " + stopwatch)
  }

  // 读取 offset
  def readOffset(zkClient: ZkClient): Option[Map[TopicAndPartition, Long]] = {

    val stopwatch = new Stopwatch()

    val stat = new Stat()
    val dataAndStat: (Option[String], Stat) = try {
      (Some(zkClient.readData(zkPath, stat)), stat)
    } catch {
      case _ => (None, stat)
      case e2: Throwable => throw e2
    }

    // 获取offset
    dataAndStat._1 match {
      case Some(offsetsRangeStr) =>
        logger.info(s" Read offset ranges: $offsetsRangeStr")
        val offset: Map[TopicAndPartition, Long] = offsetsRangeStr.split(",")
          .map(str => str.split(":"))
          .map {
            case Array(partitions, offset) =>
              TopicAndPartition(topic.last, partitions.toInt) -> offset.toLong
          }.toMap
        logger.info("Done reading offsets from Zookeeper. Took " + stopwatch)
        Some(offset)
      case None =>
        logger.info(" No offsets found in Zookeeper. Took " + stopwatch)
        None
    }
  }

  // 更新 zk中的 offset
  def updatePersistentPath(zkClient: ZkClient, zkPath: String, offsetsRangesStr: String): Unit = {
    try {
      zkClient.writeData(zkPath, offsetsRangesStr)
    } catch {
      // 如果失败了 ==> 没有此目录,则创建目录
      case _: ZkNoNodeException =>
        createParentPath(zkClient, zkPath)
        try {
          // 创建一个持久的节点 ==> 即 目录
          // 在offset写入到 该节点
          zkClient.createPersistent(zkPath, offsetsRangesStr)
        } catch {
          case _: ZkNodeExistsException =>
            zkClient.writeData(zkPath, offsetsRangesStr)
          case e2: Throwable => throw e2
        }
      case e2: Throwable => throw e2
    }
  }

  // 如果path不存在,则创建
  def createParentPath(zkClient: ZkClient, zkPath: String): Unit = {
    val parentDir = zkPath.substring(0, zkPath.lastIndexOf('/'))
    if (parentDir.length != 0)
      zkClient.createPersistent(parentDir, true)
  }

  // 过程时间
  class Stopwatch {
    private val start = System.currentTimeMillis()

    override def toString: String = (System.currentTimeMillis() - start) + " ms"
  }

}