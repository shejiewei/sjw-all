/**
 * Created by Administrator on 2019/3/1.
 */


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HBaseDemo {

    Configuration conf;
    HBaseAdmin admin;
    HTable htable;
    byte[] family = "cf".getBytes();

    // 1、删除 cell？？
    // 2、表设计


    String TN = "phone";

    @Before
    public void begin() throws Exception {
        conf = new Configuration();

        // 分布式hbase  zk列表指定zk集群
        conf.set("hbase.zookeeper.quorum", "192.168.93.131");

        admin = new HBaseAdmin(conf);//通过admin对象操作DDL语言
        htable = new HTable(conf, TN);//通过Htable对象操作表DML语言
    }

    @After
    public void end() throws Exception {
        if(admin != null) {
            admin.close();
        }
        if(htable != null) {
            htable.close();
        }
    }

    @Test
    public void createTbl() throws Exception {
        if(admin.tableExists(TN)) {
            admin.disableTable(TN);
            admin.deleteTable(TN);
        }

        HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(TN));//表的描述

        HColumnDescriptor cf = new HColumnDescriptor("cf");
        cf.setInMemory(true);//设置读缓存
        cf.setMaxVersions(1);

        desc.addFamily(cf);//创建表的时候必须制定列族，相当于一个DDL语言描述。

        admin.createTable(desc);
    }

    @Test
    public void insertDB1() throws Exception {
        String rowkey = "123";

        Put put = new Put(rowkey.getBytes());
        put.add("cf".getBytes(), "name".getBytes(), "xiaoming".getBytes());
        put.add("cf".getBytes(), "sex".getBytes(), "man".getBytes());

        htable.put(put);
    }

    @Test
    public void getDB1() throws Exception {
        String rowkey = "123";
        Get get = new Get(rowkey.getBytes());
        get.addColumn("cf".getBytes(), "name".getBytes());

        Result rs = htable.get(get);
        Cell cell = rs.getColumnLatestCell("cf".getBytes(), "name".getBytes());//result返回的是一个Cell对象

        System.out.println(new String(CellUtil.cloneValue(cell)));//取出Cell对象中的值
    }

    /**
     * 通话详单
     * 包含：手机号，对方手机号，日期，通话时长，主叫被叫类型...
     *
     * Rowkey设计：手机号_(Long.Max-时间戳)
     *
     * 1、查询某个月份 的  所有的通话详单  时间降序
     *
     * 2、查询某个手机号   所有主叫类型   通话记录
     * @throws Exception
     */

  /*  HTools t = new HTools();

    Random r = new Random();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

    *//**
     * 生成测试数据
     *
     * 十个用户 产生一百条通话记录
     *//*
    @Test
    public void insertDB2() throws Exception {


        List<Put> puts = new ArrayList<Put>();

        for (int i = 0; i < 10; i++) {
            String pnum = t.getPhoneNum("186");

            for (int j = 0; j < 100; j++) {
                String dnum = t.getPhoneNum("177");
                String datestr = t.getDate("2018");
                String length = r.nextInt(99) + "";
                String type = r.nextInt(2) + "";

                String rowkey = pnum + "_" + (Long.MAX_VALUE-sdf.parse(datestr).getTime());//默认Hbase是按照row_key的字典升序排列，此处降序。

                Put put = new Put(rowkey.getBytes());

                put.add(family, "dnum".getBytes(), dnum.getBytes());
                put.add(family, "date".getBytes(), datestr.getBytes());
                put.add(family, "length".getBytes(), length.getBytes());
                put.add(family, "type".getBytes(), type.getBytes());

                puts.add(put);
            }
        }

        htable.put(puts);
    }
*/
    /**
     * 查询某个手机号  某个月份所有的通话记录
     * 范围
     * @throws Exception
     */
 /*   @Test
    public void scanDB1() throws Exception {
        Scan scan = new Scan();

        String pnum = "18692739289_";

        String startRowkey = pnum + (Long.MAX_VALUE-sdf.parse("20181001000000").getTime());
        String stopRowkey = pnum + (Long.MAX_VALUE-sdf.parse("20180901000000").getTime());

        scan.setStartRow(startRowkey.getBytes());
        scan.setStopRow(stopRowkey.getBytes());//scan操作设置起始和结束的rowkey

        ResultScanner rss = htable.getScanner(scan);//返回一个Result集合。
        for (Result rs : rss) {//遍历Result集合
            System.out.print(new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "dnum".getBytes()))));//得到一条条数据。
            System.out.print(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "date".getBytes()))));
            System.out.print(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "type".getBytes()))));
            System.out.println(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "length".getBytes()))));
        }
    }
*/
    /**
     * 查询某个手机号  所有的主叫type=1
     * 过滤器
     * @throws Exception
     */
    @Test
    public void scanDB2() throws Exception {
        Scan scan = new Scan();

        FilterList list = new FilterList(FilterList.Operator.MUST_PASS_ALL);//定义多个过滤器，必须全部筛选

        PrefixFilter filter1 = new PrefixFilter("18692739289".getBytes());//前缀过滤器，先返回所有以这个判定条件的rowkey
        list.addFilter(filter1);

        SingleColumnValueFilter filter2 = new SingleColumnValueFilter(family, //列值过滤器，因为是两个查询条件，所以需要两个过滤器
                "type".getBytes(), CompareOp.EQUAL, "1".getBytes()); //判断所有type等于1的值

        list.addFilter(filter2);//加第二个过滤器

        scan.setFilter(list);

        ResultScanner rss = htable.getScanner(scan);
        for (Result rs : rss) {
            System.out.print(new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "dnum".getBytes()))));
            System.out.print(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "date".getBytes()))));
            System.out.print(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "type".getBytes()))));
            System.out.println(" - " + new String(CellUtil.cloneValue(rs.getColumnLatestCell(family, "length".getBytes()))));
        }

    }

}