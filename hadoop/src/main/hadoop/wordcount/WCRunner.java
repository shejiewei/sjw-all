package wordcount;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class WCRunner {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        Configuration conf = new Configuration();
        Job wcjob=Job.getInstance(conf);
       //设置整个job所用的类在哪个jar包
        wcjob.setJarByClass(WCRunner.class);

        wcjob.setMapperClass(WCMapper.class);
        wcjob.setReducerClass(WCReducer.class);

        //指的reduce
        wcjob.setOutputKeyClass(Text.class);
        wcjob.setOutputValueClass(LongWritable.class);
        //指的mapper
        wcjob.setMapOutputKeyClass(Text.class);
        wcjob.setMapOutputValueClass(LongWritable.class);

        //指定原始数据存放路径
        FileInputFormat.setInputPaths(wcjob,new Path("/wc1/srcdata"));
         //设置处理结果输出路径
        FileOutputFormat.setOutputPath(wcjob,new Path("/wc1/output"));

         wcjob.waitForCompletion(true);
    }


}
