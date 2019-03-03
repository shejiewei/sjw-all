import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by Administrator on 2019/3/1.
 */
public class hbaseApi {

    private static void createTable() throws IOException {
        Configuration conf = HBaseConfiguration.create();

        conf.set("hbase.rootdir","hdfs://192.168.93.131:8020/hbase");
        conf.set("hbase.zookeepr.quorum","192.168.93.131:2181");

        HBaseAdmin hBaseAdmin = new HBaseAdmin(conf);
        hBaseAdmin.createNamespace(NamespaceDescriptor.create("ns1").build());
        TableName name = TableName.valueOf("ns1:t1");
        if(!hBaseAdmin.tableExists(name))
        {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(name);

            HColumnDescriptor hcd = new HColumnDescriptor("f1");
            hTableDescriptor.addFamily(hcd);
            hBaseAdmin.createTable(hTableDescriptor);
        }
        hBaseAdmin.close();
    }
   private static Connection conn=null;
    private static Configuration conf=null;

    public static void connect() throws IOException {
        conf = HBaseConfiguration.create();

        conf.set("hbase.rootdir","hdfs://192.168.93.131:8020/hbase");
        conf.set("hbase.zookeepr.quorum","192.168.93.131:2181");
        conn = ConnectionFactory.createConnection(conf);
    }
//普通put
    public void put1() throws IOException {
        //通过连接得到表
            Table table = conn.getTable(TableName.valueOf("ns1:t1"));
             //获取put对象并添加数据
             Put put = new Put(Bytes.toBytes("row1"));
             put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes("tom"));
            put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("id"),Bytes.toBytes("1"));
             put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes("20"));
              //执行put操作
             table.put(put);
             table.close();
             conn.close();
    }




    public static void main(String[] args) throws IOException {

  //connect();
 createTable();
    }

}
