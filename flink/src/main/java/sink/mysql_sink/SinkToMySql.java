package sink.mysql_sink;

import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * Created by shejiewei on 2020/5/11.
 */
public class SinkToMySql  extends RichSinkFunction<Student>{

    PreparedStatement ps;
    private Connection connection;
//建立连接
    public void open(Configuration parameters) throws Exception {
      super.open(parameters);
        connection=getConnection();
        String sql="insert into Student(id,name,password,age) values(?,?,?,?);";

    }

    public void close() throws Exception {
        super.close();
        if(connection!=null){
            connection.close();
        }
        if (ps!=null)
        {
            ps.close();
        }
    }
    public void invoke(Student student,Context context)throws  Exception{
        ps.setString(1,student.getId());
        ps.setString(2,student.getName());
        ps.setString(3,student.getPassword());
        ps.setString(4,student.getAge());
        ps.executeUpdate();
    }
    private static  Connection getConnection(){
        Connection con=null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8", "root", "123");
        } catch (Exception e) {
            System.out.println("-----------mysql get connection has exception , msg = "+ e.getMessage());
        }
        return con;


    }

}
