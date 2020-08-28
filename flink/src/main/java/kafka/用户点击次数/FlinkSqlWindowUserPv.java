package kafka.用户点击次数;

/**
 * Created by shejiewei on 2020/5/14.
 */
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.tuple.Tuple5;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.TableConfig;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.sql.Timestamp;
import java.util.Properties;




public class FlinkSqlWindowUserPv{

    public static void main(String[] args) throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
        env.setParallelism(1);

        TableConfig tc = new TableConfig();

        StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env);


        Properties properties = new Properties();
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        properties.put("zookeeper.connect", "127.0.0.1:2181");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("group.id", "test6");

        FlinkKafkaConsumer010<String> myConsumer = new FlinkKafkaConsumer010<String>("myItems_topic5", new SimpleStringSchema(),
                properties);

        DataStream<String> stream = env.addSource(myConsumer);

        DataStream<Tuple5<String, String, String, String, Long>> map = stream.map(new MapFunction<String, Tuple5<String, String, String, String,Long>>() {

            private static final long serialVersionUID = 1471936326697828381L;

            @Override
            public Tuple5<String, String, String, String,Long> map(String value) throws Exception {
                String[] split = value.split(" ");
                return new Tuple5<String, String, String, String,Long>(split[0],split[1],split[2],split[3],Long.valueOf(split[4])*1000);
            }
        });

        map.print(); //打印流数据


        //注册为user表
        tableEnv.registerDataStream("Users", map, "userId,itemId,categoryId,behavior,timestampin,proctime.proctime");

        //执行sql查询     滚动窗口 10秒    计算10秒窗口内用户点击次数
        Table sqlQuery = tableEnv.sqlQuery("SELECT TUMBLE_END(proctime, INTERVAL '10' SECOND) as processtime,"
                + "userId,count(*) as pvcount "
                + "FROM Users "
                + "GROUP BY TUMBLE(proctime, INTERVAL '10' SECOND), userId");


        //Table 转化为 DataStream
        DataStream<Tuple3<Timestamp, String, Long>> appendStream = tableEnv.toAppendStream(sqlQuery,Types.TUPLE(Types.SQL_TIMESTAMP,Types.STRING,Types.LONG));

        appendStream.print();


        //sink to mysql
        appendStream.map(new MapFunction<Tuple3<Timestamp,String,Long>, UserPvEntity>() {

            private static final long serialVersionUID = -4770965496944515917L;

            @Override
            public UserPvEntity map(Tuple3<Timestamp, String, Long> value) throws Exception {

                return new UserPvEntity(Long.valueOf(value.f0.toString()),value.f1,value.f2);
            }
        }).addSink(new SinkUserPvToMySQL2());

        env.execute("userPv from Kafka");

    }

}
