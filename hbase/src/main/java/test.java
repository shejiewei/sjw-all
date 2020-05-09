import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 *
 */
public class test {

    private static Configuration conf = null;
    private Connection connection=null;

    @Before
    public void init() throws IOException {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.93.131");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "192.168.93.131:60000");
        connection=ConnectionFactory.createConnection(conf);
    }
    @Test
    public void isExit() throws IOException {

      //  Admin admin = connection.getAdmin();
       // HBaseAdmin admin= new HBaseAdmin(conf);
        Admin admin = connection.getAdmin();
        boolean text = admin.tableExists(TableName.valueOf("test"));
        connection=ConnectionFactory.createConnection(conf);
        System.out.println(text);
    }


}
