package window.sessionwindow;

import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.text.SimpleDateFormat;

/**
 * Created by shejiewei on 2020/5/21.
 */
public class FlinkStaticSessionWindowsDemo {


     public static void main(String[] args) throws Exception {
         long delay = 5000L;
         long windowGap = 10000L;

         final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
         env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
         env.setParallelism(1);
//设置数据源
         // 设置数据源
         DataStream<Tuple3<String, String, Long>> source = env.addSource(new StreamDataSource()).name("Demo Source");
//设置水位线
        SingleOutputStreamOperator<Tuple3<String, String, Long>> stream= source.assignTimestampsAndWatermarks(
                 new BoundedOutOfOrdernessTimestampExtractor<Tuple3<String, String, Long>>(Time.milliseconds(delay)) {
                     @Override
                     public long extractTimestamp(Tuple3<String, String, Long> element) {
                         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                         System.out.println(element.f0 + "\t" + element.f1 + " watermark -> " + format.format(getCurrentWatermark().getTimestamp()) + " timestamp -> " + format.format(element.f2));

                         return element.f2;
                     }
                 }
         );

         //窗口聚合
         stream.keyBy(0).window(EventTimeSessionWindows.withGap(Time.milliseconds(windowGap))).reduce(
                 new ReduceFunction<Tuple3<String, String, Long>>() {
                     @Override
                     public Tuple3<String, String, Long> reduce(Tuple3<String, String, Long> value1, Tuple3<String, String, Long> value2) throws Exception {
                         return  Tuple3.of(value1.f0,value1.f1+""+value2.f1,1L);
                     }
                 }
         ).print();
         env.execute("demo");
     }
}
