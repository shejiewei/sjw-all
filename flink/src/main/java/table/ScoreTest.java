package table;

import org.apache.flink.api.common.functions.MapFunction;
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
public class ScoreTest {
     public static void main(String[] args) throws Exception {
         ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
         BatchTableEnvironment tableEnv = BatchTableEnvironment.create(env);



         DataSet<String> input = env.readTextFile(" D:\\gitbase\\sjw-all\\flink\\src\\main\\resources\\score.csv");
         DataSet<PlayerData> topInput = input.map(new MapFunction<String, PlayerData>() {
             @Override
             public PlayerData map(String s) throws Exception {
                 String[] split = s.split(",");
                 return new PlayerData(String.valueOf(split[0]),
                         String.valueOf(split[1]),
                         String.valueOf(split[2]),
                         Integer.valueOf(split[3]),
                         Double.valueOf(split[4]),
                         Double.valueOf(split[5]),
                         Double.valueOf(split[6]),
                         Double.valueOf(split[7]),
                         Double.valueOf(split[8])
                 );
             }
         });


         Table topScore = tableEnv.fromDataSet(topInput);
         tableEnv.registerTable("score", topScore);
         Table queryResult = tableEnv.sqlQuery("select player, count(season) as num from score group by player order by num desc limit 3");

       DataSet<Result> result = tableEnv.toDataSet(queryResult, Result.class);
         result.print();
         TableSink sink = new CsvTableSink("/home/result.csv", ",");
         String[] fieldNames = {"name", "num"};
         TypeInformation[] fieldTypes = {Types.STRING, Types.INT};
         tableEnv.registerTableSink("result", fieldNames, fieldTypes, sink);
        // queryResult.insertInto("result");//这里1.10版本好像不让用了
         //tableEnv.insertInto("D:\\gitbase\\sjw-all\\flink\\src\\main\\resources\\result.csv",queryResult);
         env.execute();



     }
    public static class PlayerData {
        /**
         * 赛季，球员，出场，首发，时间，助攻，抢断，盖帽，得分
         */
        public String season;
        public String player;
        public String play_num;
        public Integer first_court;
        public Double time;
        public Double assists;
        public Double steals;
        public Double blocks;
        public Double scores;

        public PlayerData() {
            super();
        }

        public PlayerData(String season,
                          String player,
                          String play_num,
                          Integer first_court,
                          Double time,
                          Double assists,
                          Double steals,
                          Double blocks,
                          Double scores
        ) {
            this.season = season;
            this.player = player;
            this.play_num = play_num;
            this.first_court = first_court;
            this.time = time;
            this.assists = assists;
            this.steals = steals;
            this.blocks = blocks;
            this.scores = scores;
        }
    }
    public static class Result {
        public String player;
        public Long num;

        public Result() {
            super();
        }
        public Result(String player, Long num) {
            this.player = player;
            this.num = num;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "player='" + player + '\'' +
                    ", num=" + num +
                    '}';
        }
    }

}
