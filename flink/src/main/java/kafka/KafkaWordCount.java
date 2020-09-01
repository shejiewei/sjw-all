package kafka;

/**
 * Created by shejiewei on 2020/5/9.
 */
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.util.Collector;

import java.util.Properties;
public class KafkaWordCount {
    private static final String READ_TOPIC = "test1";
    //这里要注意在本地window的host文件配置ip和hostname
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.93.5:9092");
        props.put("zookeeper.connect", "192.168.93.5:2181");
        props.put("group.id", "test-consumer-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest");
        DataStreamSource<String> student = env.addSource(new FlinkKafkaConsumer<>(
                READ_TOPIC,
                new SimpleStringSchema(),
                props)).setParallelism(1);
        DataStream<Tuple2<String, Integer>> counts = student.flatMap(new LineSplitter()).keyBy(0).sum(1);
        counts.print();
        env.execute("flink custom kafka");
    }
    public static final class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) {
            String[] tokens = value.toLowerCase().split("\\W+");
            for (String token : tokens) {
                if (token.length() > 0) {
                    out.collect(new Tuple2<String, Integer>(token, 1));
                }
            }
        }
    }
}