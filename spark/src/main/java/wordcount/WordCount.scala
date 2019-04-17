package wordcount

import org.apache.spark.{SparkConf, SparkContext}


/**
  * Created by shejiewei on 2019/3/27.
  */
object WordCount {

  def main(args: Array[String]){

    val conf=new SparkConf().setAppName("WordCount").setMaster("local[2]");
    val sc=new SparkContext(conf);

    val lines=sc.textFile("hdfs://192.168.93.128:8020/data/word.txt");
    val wordCounts= lines.flatMap(line=>line.split(" ")).map(word=>(word,1)).reduceByKey(_+_);

     wordCounts.foreach(wordcount=>println(wordcount._1+"appeared"+wordcount._2));

    sc.stop();
  //spark-submit --class wordcount.WordCount --master local[2] /data/spark-1.0-SNAPSHOT.jar

  }

}
