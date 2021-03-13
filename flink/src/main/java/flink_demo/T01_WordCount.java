package flink_demo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;



/*
 Flink 普通版WordCount
 */
public class T01_WordCount {
    public static void main(String[] args) throws Exception {
        // 获取flink执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 加载数据源
        DataStreamSource<String> lines = env.socketTextStream("localhost", 7777);

        // 数据分析、转换、计算
        SingleOutputStreamOperator<Tuple2<String, Integer>> sum = lines.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = line.split(" ");
                for (String word : words) {
                    out.collect(Tuple2.of(word, 1));
                }
            }
        }).keyBy(0).sum(1);


        lines.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {

              String[] words=  line.split(" ");
              for(String word:words){
                  collector.collect(Tuple2.of(word,1));
              }
            }
        }).keyBy(0).sum(1);
        sum.print();

        // 数据输出
        sum.print();

        // 任务执行
        env.execute("T01_WordCount");
    }
}
