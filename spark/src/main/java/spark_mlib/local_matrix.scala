package spark_mlib

import org.apache.spark.mllib.linalg.{Matrix, Matrices}


object local_matrix {
  def main(args: Array[String]): Unit = {

    //创建密矩阵（（1.0，2.0），（3.0, 4.0），（5.0, 6.0））
    val dm: Matrix = Matrices.dense(3, 2, Array(1.0, 3.0, 5.0, 2.0, 4.0, 6.0))

    println(dm)
  }
}
