/*
package kafka;

import lombok.val;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;
import org.apache.flink.table.api.TableConfig;
import org.apache.flink.table.api.TableEnvironment;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.util.Properties;
import java.util.stream.Collector;

*/
/**
 * Created by shejiewei on 2020/5/14.
 *//*

public class flinkTableKafka {

     public static void main(String[] args) {
         final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
         Properties props = new Properties();
         props.put("bootstrap.servers", "192.168.93.5:9092");
         props.put("zookeeper.connect", "192.168.93.5:2181");
         props.put("group.id", "test-consumer-group");
         props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
         props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
         props.put("auto.offset.reset", "latest");

         DataStream<Tuple2<String, Integer>> counts = student.flatMap(new KafkaWordCount.LineSplitter()).keyBy(0).sum(1);
         counts.print();


         StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);

         // 连接kafka
         DataStreamSource<String> student = env.addSource(new FlinkKafkaConsumer<>(
                 "test1",
                 new SimpleStringSchema(),
                 props)).flatMap(new FlatMapFunction<String, Object>() {
             @Override
             public void flatMap(String s, org.apache.flink.util.Collector<Object> collector) throws Exception {
                               for (String word:s.split(" "))
                               {
                                   collector.collect(word.trim());
                               }
             }
         }).map(new MapFunction<Object,Object>() {
             @Override
             public Object map(Object o) throws Exception {
                 return o+1;
             }
         })




      .flatMap(x => x.split(" "))
      .map(x => (x, 1L))

         val table = sTableEnv.registerDataStream("Words", input, 'word, 'frequency)

         val result = sTableEnv
                 .scan("Words")
                 .groupBy("word")
                 .select('word, 'frequency.sum as 'cnt)
         sTableEnv.toRetractStream[(String, Long)](result).print()

         sTableEnv.sqlQuery("select * from Words").toAppendStream[(String, Long)].print()

         sEnv.execute("TableDemo")

      }
}
*/
