package ml.kmeans

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2020/5/6.
  */
object KmeansTest {

  def main(args: Array[String]): Unit = {

    val conf=new SparkConf().setAppName("kmeans test").setMaster("local")
    val sc=new SparkContext(conf)
    val rawTrainingData=sc.textFile("data/mllib/Wholesale customers data.csv")
    val parsedTraingingData= rawTrainingData.filter(!isColumnNameLine(_))  //去掉文件头
           .map(line=>{
       Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))
   }).cache()
     parsedTraingingData.foreach(println(_))

  }
  private def isColumnNameLine(line: String): Boolean = {//去掉文件头
    if (line != null && line.contains("Channel")) true
    else false
  }
}
