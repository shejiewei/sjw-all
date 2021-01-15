package spark_core.mappartitions

/**
  * Created by shejiewei on 2021/1/13.
  */
import org.apache.spark.{SparkConf, SparkContext}

object Ex_operate {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf(true).setMaster("local[*]").setAppName("spark demo : operate")

    val sc = new SparkContext(conf)

    //todo map 1对1的遍历
    val listRDD = sc.makeRDD(1 to 10,3)
    listRDD.map(_*2).map(_.toString+"s").collect().foreach(println)
    listRDD.map((_,1)).collect().foreach(println)
    listRDD.map(x=>(x,x*100,List((x,1)))).collect().foreach(println)

    listRDD.map(x=>(x,x*2,List((x,2)))).collect().foreach(println)

    //todo mapPartitions
    // 对一个rdd里所有分区遍历
    // 效率优于map算子，减少了发送到执行器执行的交互次数，mapPartitions是批量将分区数据一次发送
    // 但是执行器内存不够的则可能会出现内存溢出(OOM)
    // 假设有N个元素，有M个分区，那么map的函数的将被调用N次,而mapPartitions被调用M次,一个函数一次处理所有分区
    // func的函数类型必须是Iterator[T] => Iterator[U]
    // map每次拿到的数据是集合中一个元素，mapPartitions每次拿到的是一个分区里的所有元素
    // mapPartitions业务逻辑里的(_.map(_*2))的map是scala的map，不是spark的map，一定要区分清楚
    listRDD.mapPartitions(_.map(_*2)).mapPartitions(_.map(_.toString+"m")).collect().foreach(println)

    listRDD.mapPartitions(_.map(_*3)).collect().foreach(println)


    //todo mapPartitionsWithIndex
    //类似于mapPartitions，但func带有一个整数参数表示分片的索引值
    listRDD.mapPartitionsWithIndex{
      case (num,data) => {
        data.map((_,"分区号"+num))
      }
    }.collect().foreach(println)

    val listTuple = sc.parallelize(List(("kpop","female"),("zorro","male"),("mobin","male"),("lucy","female")),2)
    listTuple.mapPartitionsWithIndex((num,data)=>{
      var women = List[String]()
      while (data.hasNext){
        val next = data.next()
        next match {
          case (_,"female") => women="["+num+"]"+next._1::women
          case _ =>
        }
      }
      women.iterator
    }).collect().foreach(println)

    //todo driver和executor
    // 除了计算部分在executor（计算节点），其他部分都在driver里
    val step = 10
    listRDD.map(
      _*step     //只有这部分在executor，那么step这个变量是driver里，需要通过网络传输到executor，
      //所以step需要实现序列化
    ).collect()
    sc.stop()
  }
}

