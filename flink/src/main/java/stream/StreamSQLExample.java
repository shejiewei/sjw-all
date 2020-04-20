package stream;

import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.StreamTableEnvironment;

import java.util.Arrays;

/**
 * Simple example for demonstrating the use of SQL on a Stream Table in Java.
 *
 * <p>This example shows how to:
 *  - Convert DataStreams to Tables
 *  - Register a Table under a name
 *  - Run a StreamSQL query on the registered Table
 *
 */
public class StreamSQLExample {

    // *************************************************************************
    //     PROGRAM
    // *************************************************************************

    public static void main(String[] args) throws Exception {

        // set up execution environment
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setParallelism(1);
        StreamTableEnvironment tEnv = StreamTableEnvironment.create(env);

        DataStream<Order> orderA = env.fromCollection(Arrays.asList(
                new Order(1L, "beer", 3),
                new Order(1L, "diaper", 4),
                new Order(3L, "rubber", 2)));

        DataStream<Order> orderB = env.fromCollection(Arrays.asList(
                new Order(2L, "pen", 3),
                new Order(2L, "rubber", 3),
                new Order(4L, "beer", 1)));

        // register DataStream as Table
        tEnv.registerDataStream("OrderA", orderA, "user, product, amount");
        tEnv.registerDataStream("OrderB", orderB, "user, product, amount");

        // union the two tables
        Table result = tEnv.sqlQuery("SELECT " +
                "* " +
                "FROM " +
                "( " +
                "SELECT " +
                "* " +
                "FROM " +
                "OrderA " +
                "WHERE " +
                "user < 3 " +
                "UNION ALL " +
                "SELECT " +
                "* " +
                "FROM " +
                "OrderB " +
                "WHERE " +
                "product <> 'rubber' " +
                ") OrderAll " +
                "WHERE " +
                "amount > 2");

        System.out.println(tEnv.explain(result));

        tEnv.toAppendStream(result, Order.class).print();

        env.execute();
    }

    // *************************************************************************
    //     USER DATA TYPES
    // *************************************************************************

    /**
     * Simple POJO.
     */
    public static class Order {
        public Long user;
        public String product;
        public int amount;

        public Order() {
        }

        public Order(Long user, String product, int amount) {
            this.user = user;
            this.product = product;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "user=" + user +
                    ", product='" + product + '\'' +
                    ", amount=" + amount +
                    '}';
        }
    }
}
