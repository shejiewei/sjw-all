package spark_core.mapjoin和commonjoin

/**
  * Created by shejiewei on 2021/2/17.
  */
import org.apache.spark.{SparkContext, SparkConf}
import scala.collection.mutable.ArrayBuffer

object joinTest extends App {


  val conf = new SparkConf().setMaster("local[2]").setAppName("test")
  val sc = new SparkContext(conf)

  /**
    * map-side-join
    * 取出小表中出现的用户与大表关联后取出所需要的信息
    **/
  //部分人信息(身份证,姓名)
  val people_info = sc.parallelize(Array(("110", "lsw"), ("222", "yyy"))).collectAsMap()
  //全国的学生详细信息(身份证,学校名称,学号...)
  val student_all = sc.parallelize(Array(("110", "s1", "211"),
    ("111", "s2", "222"),
    ("112", "s3", "233"),
    ("113", "s2", "244")))

  //将需要关联的小表进行关联
  val people_bc = sc.broadcast(people_info)

  val res11=student_all.mapPartitions(
    iter=>{
      val arrayBuffer=new ArrayBuffer[(String,String,String)]();
      val stuMap=people_bc.value
      iter.foreach{
        case(idCard,school,sno)=>{
             if(stuMap.contains(idCard))
               {
                 arrayBuffer.+=((idCard,stuMap.getOrElse(idCard,""),school))
               }

        }
      }

      arrayBuffer.iterator
    }

  )
   res11.foreach(println)

  /**
    * 使用mapPartition而不是用map，减少创建broadCastMap.value的空间消耗
    * 同时匹配不到的数据也不需要返回（）
    **/
  val res = student_all.mapPartitions(iter => {
    val stuMap = people_bc.value
    val arrayBuffer = ArrayBuffer[(String, String, String)]()
    iter.foreach { case (idCard, school, sno) => {
      if (stuMap.contains(idCard)) {
        arrayBuffer.+=((idCard, stuMap.getOrElse(idCard, ""), school))
      }
    }
    }
    arrayBuffer.iterator
  })

  /**
    * 使用另一种方式实现
    * 使用for的守卫
    **/
  val res1 = student_all.mapPartitions(iter => {
    val stuMap = people_bc.value
    for {
      (idCard, school, sno) <- iter
      if (stuMap.contains(idCard))
    } yield (idCard, stuMap.getOrElse(idCard, ""), school)
  })

  res.foreach(println)
  res1.foreach(println)
}
