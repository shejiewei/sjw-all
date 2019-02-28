package sequencefile; /**
 * Created by Administrator on 2019/2/28.
 */
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;


public class SequenceFileTest {

    public static final String output_path = "xxx";
    private static final String[] DATA = { "a", "b", "c", "d"};

    @SuppressWarnings("deprecation")
    public static void write(String pathStr) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(pathStr);

        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf, path, Text.class, IntWritable.class);
        Text key = new Text();
        IntWritable value = new IntWritable();
        for(int i = 0; i < DATA.length; i++) {
            key.set(DATA[i]);
            value.set(i);
            System.out.printf("[%s]\t%s\t%s\n", writer.getLength(), key, value);
            writer.append(key, value);
        }
        IOUtils.closeStream(writer);
    }

    @SuppressWarnings("deprecation")
    public static void read(String pathStr) throws IOException {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(pathStr);
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, path, conf);

        Writable key = (Writable) ReflectionUtils.newInstance(reader.getKeyClass(), conf);
        Writable value = (Writable) ReflectionUtils.newInstance(reader.getValueClass(), conf);
        while (reader.next(key, value)) {
            System.out.printf("%s\t%s\n", key, value);
        }
        IOUtils.closeStream(reader);
    }

    public static void main(String[] args) throws IOException {
        //write(output_path);
       read(output_path);
    }
}
