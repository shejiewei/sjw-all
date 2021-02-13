package sql;

import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * @Description KafkaMessageStreaming Flink入口类，封装了对于Kafka消息的处理逻辑。本例每1秒统计一次结果并写入到本地件或者打印出来
 * @Author 0262000099 Hengtai Nie
 * @CreateDate 2018/9/21 16:51
 */
public class KafkaMessageStreaming {

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 非常关键，一定要设置启动检查点！！
        env.enableCheckpointing(5000);
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        Properties props = new Properties();
        props.setProperty("bootstrap.servers", "120.78.216.6:9017");
        props.setProperty("zookeeper.connect", "120.78.216.6:9018");
        props.setProperty("group.id", "test-consumer-group");

        //    args[0] = "test-0921";  //传入的是kafka中的topic
        FlinkKafkaConsumer010<String> consumer =
                new FlinkKafkaConsumer010<>("test121", new SimpleStringSchema(), props);
       // consumer.assignTimestampsAndWatermarks(new MessageWaterEmitter());

        DataStream<Tuple2<String, Long>> keyedStream = env
                .addSource(consumer)
                .flatMap(new MessageSplitter())
                .keyBy(0)
                .timeWindow(Time.seconds(2))
                .apply(new WindowFunction<Tuple2<String, Long>, Tuple2<String, Long>, Tuple, TimeWindow>() {
                    public void apply(Tuple tuple, TimeWindow window, Iterable<Tuple2<String, Long>> input, Collector<Tuple2<String, Long>> out) throws Exception {
                        long sum = 0L;
                        int count = 0;
                        for (Tuple2<String, Long> record : input) {
                            sum += record.f1;
                            count++;
                        }
                        Tuple2<String, Long> result = input.iterator().next();
                        result.f1 = sum / count;
                        out.collect(result);
                    }
                });

        //将结果打印出来
        keyedStream.print();
        //    将结果保存到文件中
        //    args[1] = "E:\\FlinkTest\\KafkaFlinkTest";//传入的是结果保存的路径
       // keyedStream.writeAsText("E:\\FlinkTest\\KafkaFlinkTest");
        env.execute("Kafka-Flink Test");
    }
}
