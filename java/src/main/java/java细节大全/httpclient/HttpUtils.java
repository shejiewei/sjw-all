package java细节大全.httpclient;/*

package com.linkcm.forcast.util;


import okhttp3.*;

import java.io.IOException;


*/
/**
 * Created by shejiewei on 2019/9/11.
 *//*


public class HttpUtils {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client = new OkHttpClient();

    public Response post(String url, String json,String X_Access_Token ) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .addHeader("x-access-token",X_Access_Token)
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept-Charset", "utf-8")
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }
   public static Response postCopy(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response;
    }

    public static String get(String url) throws IOException, InterruptedException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        String s = response.body().string();
        return s;
    }
    public static String okHttpGet(String url) {
        // 首先需要创建一个OkHttpClient对象用于Http请求, 可以改成全局型
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        // 创建一个request对象
        Request request = new Request.Builder().url(url).build();
        // 执行和回调
        String str=null;
        client.newCall(request).enqueue(new Callback() {
            public void onFailure(Call call, IOException e) {
                System.out.println("failure");
            }

            public void onResponse(Call call, Response response)
                    throws IOException {
               String str = response.body().string();
               System.out.println("str" );
            }
        });
        return  null;
    }

}
*/
