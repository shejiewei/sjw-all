package sink.mysql_sink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * Created by shejiewei on 2020/5/11.
 */
public class TestSinkToMySQL {
     public static void main(String[] args) throws Exception {
         final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

         Properties props = new Properties();
         props.put("bootstrap.servers", "192.168.93.5:9092");
         props.put("zookeeper.connect", "192.168.93.5:2181");
         props.put("group.id", "test-consumer-group");
         props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
         props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
         props.put("auto.offset.reset", "latest");

         FlinkKafkaConsumer010<String> myConsumer = new FlinkKafkaConsumer010<>("test", new SimpleStringSchema(), props);

         SingleOutputStreamOperator<Student> source = env.addSource(myConsumer).setParallelism(1).map(
                 string -> JSON.parseObject(string, Student.class)
         );
         source.print();
        // String name = student.getName();
         //System.out.println("name="+name);
         //student.addSink(new SinkToMySql());
         env.execute("Flink add sink");
     }
}
