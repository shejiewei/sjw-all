package table;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.table.api.Table;
import org.apache.flink.table.api.java.BatchTableEnvironment;
import org.apache.flink.table.sinks.CsvTableSink;
import org.apache.flink.table.sinks.TableSink;

/**
 * Created by shejiewei on 2020/4/27.
 */
public class nameTest {
    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
        BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);

        /* CsvTableSource nameSource = CsvTableSource.builder()
                 .path(" D:\\gitbase\\sjw-all\\flink\\src\\main\\resources\\name.csv")
                 .field("id", Types.STRING)
                 .field("name", Types.STRING)
                 .build();
         tableEnv.registerTableSource("nameTable",nameSource);*/
        String orderSql = "CREATE TABLE nameTable(id STRING, name STRING, time1 STRING)WITH(\n" +
                "  'connector.type' = 'filesystem',\n" +
                "  'connector.path' = 'D:\\gitbase\\sjw-all\\flink\\src\\main\\resources\\name.csv',\n" +
                "  'format.type' = 'csv'\n" +
                ")";
        String orderSql2 = "CREATE TABLE nameTable2(id STRING, name STRING, time1 STRING)WITH(\n" +
                "  'connector.type' = 'filesystem',\n" +
                "  'connector.path' = 'D:\\gitbase\\sjw-all\\flink\\src\\main\\resources\\result',\n" +
                "  'format.type' = 'csv'\n" +
                ")";

        tableEnv.sqlUpdate(orderSql);
        tableEnv.sqlUpdate(orderSql2);
        tableEnv.registerFunction("nameUdf", new nameUdf());
        tableEnv.registerFunction("timeUdf", new timeUdf());
        Table queryResult = tableEnv.sqlQuery("select id,nameUdf(`name`) as name,timeUdf(`time1`) as time1 from nameTable ");

        queryResult.printSchema();
        DataSet<Result> result = tableEnv.toDataSet(queryResult, Result.class);
        result.print();

        TableSink csvSink = new CsvTableSink("D:\\gitbase\\sjw-all\\flink\\src\\main\\resources", ",");
        String[] fieldNames = {"id", "name"};
        TypeInformation[] fieldTypes = {Types.STRING, Types.STRING};
        tableEnv.registerTableSink("CsvSinkTable", fieldNames, fieldTypes, csvSink);
      //  queryResult.insertInto("CsvSinkTable");


        //write to hdfs sink
        //  BucketingSink<Tuple5<String, Integer, Long, Integer, Integer>> sink = new BucketingSink<>("hdfs://localhost/logs/");
        // sink.setUseTruncate(false);
//        sink.setBucketer(new Bucketer<UI>() {
//            @Override
//            public Path getBucketPath(Clock clock, Path basePath, UI element) {
//                String newDateTimeString = dateTimeFormatter.format(Instant.ofEpochMilli(element.getTimestamp()));
//                return new Path(basePath + "/" + newDateTimeString);
//            }
//        });
    /*     sink.setBucketer(new DateTimeBucketer<Tuple5<String, Integer, Long, Integer, Integer>>("yyyy-MM-dd--HHmm", ZoneId.of("UTC+8")));
         sink.setWriter(new StringWriter<Tuple5<String, Integer, Long, Integer, Integer>>());
         sink.setBatchSize(1024 * 1024 * 10); // this is 10 MB,
         sink.setBatchRolloverInterval(60 * 1000); // this is 1 min
         ds.addSink(sink);*/


//        tenv.toAppendStream(result1, Result.class).addSink(sink);

        //输出到控制台
        //  tableEnv.toAppendStream(result1, Row.class).print();


    //用sql的方式写sink
        tableEnv.sqlUpdate(
                "INSERT INTO nameTable2 select id,nameUdf(`name`) as name,timeUdf(`time1`) as time1 from nameTable");


        env.execute();


    }

    public static class Result {
        public String id;
        public String name;
        public String time1;
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Result() {
            super();
        }

        public String getTime1() {
            return time1;
        }

        public void setTime1(String time1) {
            this.time1 = time1;
        }

        public Result(String id, String name, String time1) {
            this.id = id;
            this.name = name;
            this.time1 = time1;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", time1='" + time1 + '\'' +
                    '}';
        }
    }
}
