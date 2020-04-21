package sql;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.Properties;

/**
 * Created by shejiewei on 2020/4/21.
 */
public class FilnkCostKafka {
    public static void main(String[] args) throws Exception {
        /*final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(1000);

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "192.168.93.6:9092");
        properties.setProperty("zookeeper.connect", "192.168.93.6:2181");
        properties.setProperty("group.id", "test-consumer-group");
        FlinkKafkaConsumer<String> myConsumer=new FlinkKafkaConsumer<String>("test", new SimpleStringSchema(), properties);
        //FlinkKafkaConsumer09<String> myConsumer = new FlinkKafkaConsumer09<String>("test", new SimpleStringSchema(), properties);

        DataStream<String> stream = env.addSource(myConsumer);
        DataStream<Tuple2<String, Integer>> counts = stream.flatMap(new LineSplitter()).keyBy(0).sum(1);

        counts.print();

        env.execute("WordCount from Kafka data");*/

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        //@TODO checkpoint配置
        //@TODO 设置statebackend
        String topic = "test";
        Properties prop = new Properties();
        prop.setProperty("bootstrap.servers","192.168.93.6:9092");
        prop.setProperty("group.id","test-consumer-group");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic, new SimpleStringSchema(), prop);
        //设置消费策略
        consumer.setStartFromGroupOffsets();
        DataStreamSource<String> text = env.addSource(consumer);
        //将并行度设置为1
        text.print().setParallelism(1);
        env.execute("KafkaSourceExample");
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

