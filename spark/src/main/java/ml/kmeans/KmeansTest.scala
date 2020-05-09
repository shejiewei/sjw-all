package ml.kmeans

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}
import org.apache.spark.mllib.linalg.Vectors


/**
  * Created by shejiewei on 2020/5/6.
  */
object KmeansTest {

  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setAppName("kmeans test").setMaster("local")
    val sc = new SparkContext(conf)
    val rawTrainingData = sc.textFile("data/mllib/customer_data")
    val parsedTraingingData = rawTrainingData.filter(!isColumnNameLine(_)) //去掉文件头
      .map(line => {
      Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))
    }).cache()
    parsedTraingingData.foreach(println(_))
    /*
    numClusters = 8表示一共聚成了多少个簇，val numIterations = 30表示迭代的次数，val runTimes = 3表示运行的时间，
    var clusterIndex: Int = 0用来记录簇的索引。
在设置完聚类参数后，我们通过具体的代码利用KMeans.train()传入参数对数据进行聚类分析，并返回聚类模型，输出每一个簇的中心点。
     */
    val numClustes = 8 //聚集中心的个数
    val numIterations = 30 //迭代次数
    val runTimes = 3
    var clusterIndex: Int = 0
    val clusters: KMeansModel = KMeans.train(parsedTraingingData, numClustes, numIterations, runTimes)
    println("Cluster Number:" + clusters.clusterCenters.length)
    clusters.clusterCenters.foreach(
      x => {
        println("Center point of cluster:" + clusterIndex)
        println(x)
        clusterIndex += 1
      }
    )
    println("clusterIndex=" + clusterIndex)
    //clusters.save(sc,"file:///E:\\data\\model\\kmeansModel")  //保存模型

    /**
      * 在聚类完成后，我们再次读取数据集合中的数据信息，通过数据集合中的数据信息，并通过predict来预测每一个数据对象所属的簇。具体
      */
  //加载模型
  val model = KMeansModel.load(sc,"file:///E:\\data\\model\\kmeansModel")
    val rawTestData = sc.textFile("data/mllib/customer_data")
    val parsedTestData = rawTestData.filter(!isColumnNameLine(_)).map(line => {
      Vectors.dense(line.split(",").map(_.trim).filter(!"".equals(_)).map(_.toDouble))

    }).cache()

    parsedTestData.collect().foreach(testDataLine => {
      val predictedClusterIndex:
        Int =model.predict(testDataLine)
      println("The data " + testDataLine.toString + " belongs to cluster " +
        predictedClusterIndex)
    })
    println("Spark MLlib K-means clustering test finished.")

  }

  private def isColumnNameLine(line: String): Boolean = {
    //去掉文件头
    if (line != null && line.contains("Channel")) true
    else false
  }
}
