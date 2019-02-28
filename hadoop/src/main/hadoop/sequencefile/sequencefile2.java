package sequencefile;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by Administrator on 2019/2/28.
 */
public class sequencefile2 {

    public static String uri="hdfs://192.168.93.131:8020";

    public static String[] data={
            "one,two",
            "afdd,fd",
            "fd,fd"
    };
    @SuppressWarnings("deprecation")
    public static void write() throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path path = new Path("/tmp.seq");
        IntWritable key = new IntWritable();
        Text value = new Text();
        SequenceFile.Writer write=SequenceFile.createWriter(fs,conf,path
                ,key.getClass(),value.getClass());

        for (int i=0;i<100;i++){
            key.set(100-i);
            value.set(data[i%data.length]);
            write.append(key,value);
        }

        IOUtils.closeStream(write);
    }


    @SuppressWarnings("deprecation")
    public static void read(String path1) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path path = new Path("/"+path1);

        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

        Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
        Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
        while (reader.next(key, value)) {
            System.out.printf("%s\t%s\n", key, value);
        }
        IOUtils.closeStream(reader);
    }



    public static void main(String[] args) throws IOException {

     //write();
      read(args[0]);
    }


}
