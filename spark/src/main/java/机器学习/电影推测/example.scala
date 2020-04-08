package 机器学习.电影推测

import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by shejiewei on 2020/3/1.
  */
object example {
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf)
    val data=sc.textFile("./data/mllib/als/test.data")
    val ratings=data.map(_.split(',')match{
      case Array(user,item,rate)
        =>Rating(user.toInt,item.toInt,rate.toDouble)
    })
    ratings.collect().foreach {println}
    val rank=10
    val numIterations=10
    val model=ALS.train(ratings,rank,numIterations,0.01)

    val usersProducts=ratings.map{case Rating(user,product,rate)=>
    (user,product)}
    val predictions=model.predict(usersProducts).map{
      case Rating(user,product,rate)=>
        ((user,product),rate)
    }
    val ratesAndPreds=ratings.map{case Rating(user,product,rate)=>
      ((user,product),rate)
    }.join(predictions)

    val MSE=ratesAndPreds.map{case ((user,product),(r1,r2))=>
     val err=(r1-r2)
        err*err
    }.mean()
    println("Mean Squared Error="+MSE)
    model.save(sc,"file:///D:/tmp/myCollaborativeFilter")
    val sameModel=MatrixFactorizationModel.load(sc,"file:///D:/tmp/myCollaborativeFilter")
   val d = model.predict(1,2)
      print(d)
  }
}
