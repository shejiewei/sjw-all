package test

import org.apache.spark._

/**
  * Created by shejiewei on 2019/6/7.
  */
object sparkconfTest {

  @transient var sc: SparkContext = _
  def main(args: Array[String]): Unit = {

    test()

  }

  def test() {
    // Regression test for SPARK-4180
    val conf = new SparkConf().setAppName("test").setMaster("local")
    sc = new SparkContext(conf)
    val envBefore = SparkEnv.get
    // A SparkContext is already running, so we shouldn't be able to create a second one
  //  interept[SparkException] { new SparkContext(conf) }
    val ecnvAfter = SparkEnv.get
    // SparkEnv and other context variables should be the same
   // assert(envBefore == envAfter)
    // After stopping the running context, we should be able to create a new one
    resetSparkContext()
    sc = new SparkContext(conf)
  }
  def resetSparkContext(): Unit = {
  //  LocalSparkContext.stop(sc)
    sc = null
  }
}
