package es;


import org.elasticsearch.action.get.GetRequestBuilder;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by shejiewei on 2019/3/31.
 */
public class test2 {


    @Test
    public void test1() throws UnknownHostException {

        //指定ES集群

        Settings setting=  Settings.builder().put("cluster.name","my-application").build();

        TransportClient client= new PreBuiltTransportClient(setting).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.93.6"),9300));

        GetResponse response=client.prepareGet("lib","user","1").execute().actionGet();

        //得到查询的数据
        System.out.println(response.getSourceAsString());

        client.close();
    }

}
