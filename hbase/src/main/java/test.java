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
 * Created by shejiewei on 2019/3/31.
 */
public class test {

    private static Configuration conf = null;
    private Connection connection;

    @Before
    public void init() throws IOException {

//        configuration.set("hbase.rootdir","hdfs://192.168.93.131:8020/hbase");
//        configuration.set("hbase.zookeepr.quorum","192.168.93.131:2181");
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.93.131");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.master", "192.168.93.131:6000");
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "192.168.93.131");
//        configuration.set("hbase.master", "192.168.93.131:60000");
        connection= ConnectionFactory.createConnection(conf);



    }
    @Test
    public void isExit() throws IOException {
        Admin admin = connection.getAdmin();

        boolean text = admin.tableExists(TableName.valueOf("text"));

        System.out.println(text);
    }


}
