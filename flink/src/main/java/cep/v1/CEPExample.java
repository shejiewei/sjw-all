package cep.v1;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.functions.PatternProcessFunction;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.SimpleCondition;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class CEPExample {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties properties = new Properties();
        properties .put("bootstrap.servers", "127.0.0.1:9092");
        properties .put("zookeeper.connect", "127.0.0.1:2181");
        properties.put("group.id", "test-consumer-group");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("auto.offset.reset", "latest");
       // DataStream<String> stream = env
         //       .addSource(new FlinkKafkaConsumer010<>("test1", new SimpleStringSchema(), properties));
        DataStream<String> stream = env.addSource(new FlinkKafkaConsumer<>(
                "test1",
                new SimpleStringSchema(),
                properties)).setParallelism(1);
        DataStream<Event> input = stream.map(new MapFunction<String, Event>() {
            @Override
            public Event map(String value) throws Exception {
                String[] v = value.split(",");
                return new Event(v[0], EventType.valueOf(v[1]), Double.parseDouble(v[2]));
            }
        });

    Pattern<Event, ?> pattern = Pattern.<Event>begin("start").where(
                new SimpleCondition<Event>() {
                    @Override
                    public boolean filter(Event event) {
                        System.out.println(event + " from start");
                        return event.getType() == EventType.VALID && event.getVolume() < 10;
                    }
                }
        ).next("end").where(
                new SimpleCondition<Event>() {
                    @Override
                    public boolean filter(Event event) {
                        System.out.println(event + " from end");
                        return event.getType() == EventType.VALID && event.getVolume() > 100;
                    }
                }
        );

        PatternStream<Event> patternStream = CEP.pattern(input, pattern);

        DataStream<Alert> result = patternStream.process(
                new PatternProcessFunction<Event, Alert>() {
                    @Override
                    public void processMatch(
                            Map<String, List<Event>> pattern,
                            Context ctx,
                            Collector<Alert> out) throws Exception {

                        System.out.println(pattern);

                        out.collect(new Alert("111", "CRITICAL"));
                    }
                });

        result.print();

        env.execute("Flink cep example");

}

}
