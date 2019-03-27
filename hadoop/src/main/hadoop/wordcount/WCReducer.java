package wordcount;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by shejiewei on 2019/3/27.
 */
public class WCReducer extends Reducer<Text,LongWritable,Text,LongWritable> {
    //map处理完成之后调用,j传递一个组,调用一次reduce方法
 //<hello,{1,1,1,1,1...}>
    @Override
    protected  void reduce(Text key,Iterable<LongWritable> values,Context context) throws IOException, InterruptedException {
        long count=0;
        //遍历value的list,进行累加求和
        for (LongWritable value:values){

            count+=value.get();
        }

        context.write(key,new LongWritable(count));

    }
}
