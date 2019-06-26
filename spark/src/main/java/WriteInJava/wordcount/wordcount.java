package WriteInJava.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shejiewei on 2019/6/26.
 */
public class wordcount {
    private static final Pattern SPACE = Pattern.compile(" ");
     public static void main(String[] args) {
         SparkConf conf=new SparkConf()
                 .setAppName("WordCountLocal")
                 .set("spark.master","local");

         //JavaSparkContext sc = new JavaSparkContext();
        // JavaRDD<String> lines = sc.textFile("G://test.txt");
        SparkSession spark = SparkSession.builder().config(conf).appName("wrodcount").getOrCreate();
      JavaRDD<String> lines = spark.read().textFile("e:\\data\\test.txt").javaRDD();
         JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator());

         JavaPairRDD<String, Integer> ones = words.mapToPair(s -> new Tuple2<>(s, 1));

         JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> i1 + i2);

         List<Tuple2<String, Integer>> output = counts.collect();

         for(Tuple2<String,Integer> tuple:output)
         {
             System.out.println(tuple._1()+":"+tuple._2());
         }

         spark.stop();

     }

}
