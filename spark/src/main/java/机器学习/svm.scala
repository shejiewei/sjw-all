package 机器学习


import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.mllib.util.MLUtils
import org.apache.spark.rdd.RDD


/**
  * Created by shejiewei on 2020/2/29.
  */
object svm {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);
    val pos = LabeledPoint(1.0, Vectors.dense(1.0, 0.0, 3.0))
    // Create a labeled point with a negative label and a sparse feature vector.

  val examples:RDD[LabeledPoint]= MLUtils.loadLibSVMFile(sc,"/data/mllib/sample_libsvm_data.txt");
   examples
    val neg = LabeledPoint(0.0, Vectors.sparse(3, Array(0, 2), Array(1.0, 3.0)))

    //分布式矩阵
    val v0=Vectors.dense(1.0,2.0,3.0)
    val v1=Vectors.dense(3.0,2.0,3.0)
    val rows=sc.parallelize(Seq(v0,v1))
    print(rows.collect())
    print(rows)
  }
}
