package table;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;

/**
 * Created by shejiewei on 2020/4/27.
 */
public class orderTest {
    public static void main(String[] args) {
       // EnvironmentSettings settings = EnvironmentSettings.newInstance()...
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);

        String create_sql ="CREATE TABLE user_visit (\n" +
                "    user_name VARCHAR,\n" +
                "    ts timestamp\n" +
                ") WITH (\n" +
                "    'format.type' = 'csv',\n" +
                "    'format.derive-schema' = 'true'\n" +
                ")";

      /*  val csvTableSource = CsvTableSource
                .builder
                .path("/path/to/your/file.csv")
                .field("name", Types.STRING)
                .field("id", Types.INT)
                .field("score", Types.DOUBLE)
                .field("comments", Types.STRING)
                .fieldDelimiter("#")
                .lineDelimiter("$")
                .ignoreFirstLine
                .ignoreParseErrors
                .commentPrefix("%")*/


//CREATE 语句用于向当前或指定的 Catalog 中注册表、视图或函数。注册后的表、视图和函数可以在 SQL 查询中使用。
// 对已经已经注册的表进行 SQL 查询
// 注册名为 “Orders” 的表



        tableEnv.sqlUpdate("CREATE TABLE Orders (`user` BIGINT, product STRING, amount INT)");
// 在表上执行 SQL 查询，并把得到的结果作为一个新的表
        Table result = tableEnv.sqlQuery(
                "SELECT product, amount FROM Orders WHERE product LIKE '%Rubber%'");

// SQL 对已注册的表进行 update 操作
// 注册 TableSink
        tableEnv.sqlUpdate("CREATE TABLE RubberOrders(product STRING, amount INT) WITH (...)");
// 在表上执行 SQL 更新查询并向 TableSink 发出结果
        tableEnv.sqlUpdate(
                "INSERT INTO RubberOrders SELECT product, amount FROM Orders WHERE product LIKE '%Rubber%'");
    }
}
