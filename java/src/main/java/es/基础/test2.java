package es.基础;


import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetItemResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by shejiewei on 2019/3/31.
 */
public class test2 {

    private TransportClient  client;
    @Before
    public void test1() throws UnknownHostException {

        //指定ES集群

        Settings setting=  Settings.builder().put("cluster.name","my-application").build();

        client= new PreBuiltTransportClient(setting).addTransportAddress(new TransportAddress(InetAddress.getByName("192.168.2.45"),9300));


    }
    @Test  //查询
    public void test(){

        GetResponse response=client.prepareGet("lib","user","1").execute().actionGet();

        //得到查询的数据
        System.out.println(response.getSourceAsString());

        client.close();

    }

    @Test  //添加
    public void test2() throws IOException {

       XContentBuilder doc= XContentFactory.jsonBuilder()
                .startObject()
                .field("id","1")
                .field("title","的方式发送到发的鬼地方个")
                .field("content","给第三方士大夫士大夫 ")
                .field("postdata","2019-01-01")
                .field("url","www.badd.com")
                .endObject();
        IndexResponse response = client.prepareIndex("index1", "blog", "3").setSource(doc).get();

        System.out.println(response.status());

    }

    @Test  //更新
    public void test3() throws IOException, ExecutionException, InterruptedException {

        UpdateRequest updateRequest = new UpdateRequest();

        updateRequest.index("index1")
                .type("blog")
                .id("2")
                .doc(
                        XContentFactory.jsonBuilder().startObject().field("title","谁")
                        .endObject()
                );
        UpdateResponse updateResponse = client.update(updateRequest).get();
        System.out.println(updateResponse.status());

    }
    @Test  //批量
    public void test4(){
        MultiGetResponse res = client.prepareMultiGet()
                .add("index1", "blog", "1", "2","3","4")
                .add("lib", "user", "1")
                .get();
        for (MultiGetItemResponse item:res){
            GetResponse response = item.getResponse();
            if (response!=null){
                System.out.println(response.getSourceAsString());
            }
        }
    }
   @Test
    public void test5() throws IOException {
       BulkRequestBuilder buik = client.prepareBulk();

       buik.add(client.prepareIndex("index1","blog","3")
                       .setSource(
                               XContentFactory.jsonBuilder()
                                       .startObject()
                                       .field("id","1")
                                       .field("title","的方式发送到发的鬼地方个")
                                       .field("content","给第三方士大夫士大夫 ")
                                       .field("postdata","2019-01-01")
                                       .field("url","www.badd.com")
                                       .endObject()
                       )
       );
       buik.add(client.prepareIndex("index1","blog","4")
               .setSource(
                       XContentFactory.jsonBuilder()
                               .startObject()
                               .field("id","1")
                               .field("title","的方式发送到发的鬼地方个")
                               .field("content","给第三方士大夫士大夫 ")
                               .field("postdata","2019-01-01")
                               .field("url","www.badd.com")
                               .endObject()
               )
       );

       BulkResponse response = buik.get();

       System.out.println(response.status());

   }
    @Test
     public void test6(){

     BulkByScrollResponse response= DeleteByQueryAction.INSTANCE
                .newRequestBuilder(client)
                .filter(QueryBuilders.matchQuery("title","地方"))
                .source("index1")
                .get();
        long counts = response.getDeleted();
        System.out.println(counts);


    }
    @Test
    public void test7(){
        MatchAllQueryBuilder qb = QueryBuilders.matchAllQuery();

        SearchResponse sr= client.prepareSearch("lib3")
               .setQuery(qb)
                .setSize(3).get();
        SearchHits hits= sr.getHits();
        for (SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key:map.keySet()){
                System.out.println(key+"="+map.get(key));
            }

        }


    }

    @Test
    public void test8(){

        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "发");
        SearchResponse response = client.prepareSearch("lib3")
                .setQuery(matchQueryBuilder).setSize(3).get();

        SearchHits hits= response.getHits();
        for (SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key:map.keySet()){
                System.out.println(key+"="+map.get(key));
            }

        }


    }

    @Test
    public void test9(){
        TermQueryBuilder bu = QueryBuilders.termQuery("age", "40");
        SearchResponse response = client.prepareSearch("lib3")
                .setQuery(bu)
                .setSize(2).get();
        SearchHits hits= response.getHits();
        for (SearchHit hit:hits){
            System.out.println(hit.getSourceAsString());
            Map<String, Object> map = hit.getSourceAsMap();
            for (String key:map.keySet()){
                System.out.println(key+"="+map.get(key));
            }

        }

    }

}
