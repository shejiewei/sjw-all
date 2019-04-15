package sparksql

import java.sql.DriverManager

/**
  * Created by shejiewei on 2019/4/15.
  */

/**   使用时,要先启动thriftserver
  *
  *
  *
  */
object SparkSqlThriftServer {

  def main(args: Array[String]): Unit = {

    Class.forName("org.apache.hive.jdbc.HiveDriver");

    val conn=DriverManager.getConnection("jdbc:hive2://192.168.93.131:10000","root","");
    val psmt=conn.prepareStatement("select * from emp");
    val rs=psmt.executeQuery()
    while (rs.next()){
      println(rs.getString("ename"));
    }
    rs.close()
    psmt.close()
    conn.close()
  }
}
