package spark_core.保存到mysql

/**
  * Created by shejiewei on 2021/3/13.
  */
import java.net.URL
import java.sql.{Connection, DriverManager}
import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object UrlGroupCount {
  def main(args: Array[String]): Unit = {
    //1.创建spark的程序入口
    val conf:SparkConf = new SparkConf().setAppName("UrlGroupCont1").setMaster("local[2]")
    val sc:SparkContext = new SparkContext(conf)

    //2.将数据切分
    val rdd1:RDD[String] = sc.textFile("./data/itstar.log")

    //3.将数据切分
    val rdd2:RDD[(String,Int)] = rdd1.map(line => {
      val s: Array[String] = line.split("\t")
      //元组输出
      (s(1), 1)
    })

    //4.累加求和
    val rdd3:RDD[(String,Int)] = rdd2.reduceByKey(_+_)

    //5.分组
    val rdd4:RDD[(String,Int)] = rdd3.map(x => {
      val url = x._1
      val host = new URL(url).getHost.split("[.]")(0)
      //元组输出
      (host, x._2)
    })

    //6.根据学院分组
    val rdd5: RDD[(String, List[(String, Int)])] = rdd4.groupBy(_._1).mapValues(it => {
      //根据访问量排序，倒序
      it.toList.sortBy(_._2).reverse.take(1)
    })

    //7.把计算结果保存到mysql中
    rdd5.foreach(println)
   rdd5.foreach(x => {
      //把数据写道mysql
      val conn: Connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?charatorEncoding=utf-8","root","123")
      //把spark结果插入到mysql中
      val sql = "INSERT INTO url_data (xueyuan,number_one) VALUES (?,?)"
      //执行sql
      /**prepareStatement 预编译 无论多少次地使用同一个SQL命令，PreparedStatement
        * 都只对它解析和编译一次。当使用Statement对象时，
        * 每次执行一个SQL命令时，都会对它进行解析和编译。
        * PreparedStatement对象将会大大降低运行时间，当然也加快了访问数据库的速度。
        **/
      val statement = conn.prepareStatement(sql)

      statement.setString(1,x._1)
      statement.setString(2,x._2.toString())
      statement.executeUpdate()
      statement.close()
      conn.close()
    })
    sc.stop()
  }
}
