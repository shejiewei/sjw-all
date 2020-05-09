package sql;

import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.table.api.EnvironmentSettings;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;


public class StreamSqlKafka {

	private static final String KAFKASERVER = "192.168.93.6:9092";
	private static final String KAFKATOPIC = "test";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		//StreamTableEnvironment tableEnv = TableEnvironment.getTableEnvironment(env);
		EnvironmentSettings fsSettings = EnvironmentSettings.newInstance().useOldPlanner().inStreamingMode().build();
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		StreamTableEnvironment tableEnv = StreamTableEnvironment.create(env, fsSettings);
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
	    String schema = "{\"id\":\"int\",\"name\":\"string\",\"score\":\"int\",\"currentTimeStamp\":\"long\"}";

	 
	    Properties properties = new Properties();
	    properties.setProperty("bootstrap.servers", KAFKASERVER);
	    properties.setProperty("zookeeper.connect", "192.168.93.5:2181");
	    properties.setProperty("group.id", "test");
	        //DataStream<String> stream = env
	        //		.addSource(new FlinkKafkaConsumer08<>("topic", new SimpleStringSchema(), properties))
//	        DataStream<Tuple3<Long, String, Integer>> ds = env.addSource(null, null);
	
	    FlinkKafkaConsumer<Map> myConsumer =
		new FlinkKafkaConsumer<>(KAFKATOPIC, new SchemaUT(), properties);
	        	    
	    DataStream<Map> ds =  env.addSource(myConsumer);
	    DataStream<Order> orderB = env.fromCollection(Arrays.asList(
					new Order(2L, "pen", 3),

					new Order(2L, "rubber", 3),
					new Order(4L, "beer", 1)));
				// register DataStream as Table
		tableEnv.registerDataStream("OrderB", orderB, "users, product, amount");
	        //Table table = tableEnv.fromDataStream(ds, "user");
	    Table tableA = tableEnv.fromDataStream(ds, "users, product, amount");
	    Table result = tableEnv.sqlQuery("select * from OrderB");
	    tableEnv.toAppendStream(result, Order.class).print();
	     // execute
	    try {
	    	env.execute();
		} catch (Exception e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
