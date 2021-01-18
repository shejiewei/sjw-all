package spark_mlib

import org.apache.spark.mllib.linalg.{Vector, Vectors}


object local_vector {
  def main(args: Array[String]): Unit = {
    //创建密集向量(1.0, 0.0, 3.0)
    val dv: Vector = Vectors.dense(1.0, 0.0, 3.0)
    //给向量(1.0, 0.0, 3.0)创建疏向量
    val svl: Vector = Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0))
    //通过指定非0的项目，创建稀疏向量(1.0, 0.0, 3.0)
    val sv2: Vector = Vectors.sparse(3, Seq((0, 1.0), (2, 3.0)))

    //Scala默认会导入scala.collection.immutable.Vector，所以必须显式导入org.apache.spark.mllib.linalg.Vector才能使用MLlib才能使用MLlib提供的Vector。
    println(dv, svl, sv2)
  }
}
