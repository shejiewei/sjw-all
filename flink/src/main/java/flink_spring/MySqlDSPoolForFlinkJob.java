package flink_spring;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.PrintSinkFunction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shejiewei on 2020/5/11.
 */
public class MySqlDSPoolForFlinkJob {

    public static void main(String[] args) throws Exception {

        // 初始化spring容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");

        // 获取bean
        MysqlDSWithSpringForFlink mysqlDSWithSpringForFlink = (MysqlDSWithSpringForFlink) applicationContext.getBean("mysqlDsWithSpring");

        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(mysqlDSWithSpringForFlink).addSink(new PrintSinkFunction<>());

        env.execute("mysql Datasource with pool and spring");
    }
}
