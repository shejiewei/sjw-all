package spark_core.自定义分区

/**
  * Created by shejiewei on 2021/3/13.
  */
import org.apache.spark.Partitioner
import scala.collection.mutable.HashMap
class UDFPartitioner(args: Array[String]) extends Partitioner {

  private val partitionMap: HashMap[String, Int] = new HashMap[String, Int]()
  var parId = 0
  for (arg <- args) {
    if (!partitionMap.contains(arg)) {
      partitionMap(arg) = parId
      parId += 1
    }
  }

  override def numPartitions: Int = partitionMap.valuesIterator.length

  override def getPartition(key: Any): Int = {
    val keys: String = key.asInstanceOf[String]
    val sub = keys
    partitionMap(sub)
  }
}