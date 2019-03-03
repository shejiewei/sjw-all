/**
 * Created by Administrator on 2019/3/1.
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.io.encoding.DataBlockEncoding;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HbaseConnection {
    private String rootDir;
    private String zkServer;
    private String port;
    private Configuration conf;
    private HConnection hConn = null;

    private HbaseConnection(String rootDir, String zkServer, String port) throws IOException {
        this.rootDir = rootDir;
        this.zkServer = zkServer;
        this.port = port;
        conf = new Configuration();
        conf.set("hbase.rootdir", rootDir);
        conf.set("hbase.zookeepr.quorum", zkServer);
        conf.set("hbase.zookeeper.property.clientPort", port);
        hConn = HConnectionManager.createConnection(conf);
    }

    public void createTable(String tableName, List<String> cols) {
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);

            if (admin.tableExists(tableName)){
                throw new IOException("table exits");
            }
            else {
                HTableDescriptor tableDesc = new HTableDescriptor(tableName);
            for(String col:cols){
                HColumnDescriptor colDesc = new HColumnDescriptor(col);
                colDesc.setCompressionType(Compression.Algorithm.GZ);
                colDesc.setDataBlockEncoding(DataBlockEncoding.DIFF);
                tableDesc.addFamily(colDesc);
            }

            admin.createTable(tableDesc);
            }
            admin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void saveData(String tableName, List<Put> puts) {


    }
    public void getData(String tablename,String rowkey){

    }

    public static void main(String[] args) throws IOException {
     String rootDir="hdfs://192.168.93.131/hbase";
     String zkServer="192.168.93.131";
     String port="2181";
        HbaseConnection conn = new HbaseConnection(rootDir, zkServer, port);
      List<String> cols=new LinkedList<String>();
      cols.add("id");
      cols.add("name");
      cols.add("age");
        conn.createTable("stu",cols);

    }

}