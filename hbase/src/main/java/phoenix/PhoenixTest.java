package phoenix;

/**
 * Created by shejiewei on 2019/8/21.
 */
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

public class PhoenixTest {
    private Connection conn;
    private Statement stat;
    private ResultSet rs;

    @Before
    public void initResource() throws Exception{
        Class.forName(ConfigUtils.getDriver());
        conn = DriverManager.getConnection(ConfigUtils.getUrl(), ConfigUtils.getUserName(), ConfigUtils.getPassWord());
        stat = conn.createStatement();

    }

    @Test
    public void testCreateTable() throws SQLException {
        String sql="create table test_phoenix_api(mykey integer not null primary key ,mycolumn varchar )";
        stat.executeUpdate(sql);
        conn.commit();
    }

    @Test
    public void upsert() throws SQLException {
        String sql1="upsert into test_phoenix_api values(1,'test1')";
        String sql2="upsert into test_phoenix_api values(2,'test2')";
        String sql3="upsert into test_phoenix_api values(3,'test3')";
        stat.executeUpdate(sql1);
        stat.executeUpdate(sql2);
        stat.executeUpdate(sql3);
        conn.commit();
    }

    @Test
    public void delete() throws SQLException {
        String sql1="delete from test_phoenix_api where mykey = 1";
        stat.executeUpdate(sql1);
        conn.commit();
    }



    @After
    public void closeResource() throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if(stat!=null){
            stat.close();
        }
        if(conn!=null){
            conn.close();
        }
    }
}
