package wordcount;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class WCMapper extends Mapper<LongWritable,Text,Text,LongWritable> {
//每读一行,调用一次

    @Override
    protected void map(LongWritable key,Text value,Context context) throws IOException, InterruptedException {

        String line=value.toString();

        String[] words= StringUtils.split(line," ");
        //将这一行的内容转为string类型
        for (String word:words){
            context.write(new Text(word),new LongWritable(1));

        }
    }

}
