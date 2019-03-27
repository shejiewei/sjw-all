package java.wordcount;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;


import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class WordCountLocal {
    public static void main(String[] args){

        //设置spark的配置信息
        //setMaster()可以设置spark程序要连接的spark集群的master的节点url
        //local则表示在本地运行
        SparkConf conf=new SparkConf()
                .setAppName("WordCountLocal")
                .setMaster("local");
    //创建javaSparkContext对象
    //sql程序,就是SQLContext,HiveContext
    //spark stream,SparkContext
        //每一个RDD,就相当于文件里的一行
        JavaSparkContext sc = new JavaSparkContext();
        JavaRDD<String> lines = sc.textFile("G://test.txt");
        //每一行拆分为单个的单词
      /*  JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(s.split(" "));
            }
        });
*/
    }

}
