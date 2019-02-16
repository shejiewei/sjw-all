/*
package 心跳;

*/
/**
 * Created by shejiewei on 2019/1/24.
 *//*


import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.util.HashMap;

public class ServerHeartBeatHandler extends ChannelHandlerAdapter {

    */
/*Server端需要对IP进行判断*//*

    private boolean auth(ChannelHandlerContext ctx, Object msg) {
        System.out.println("接收到的内容:" + msg);
        if (null != msg && msg.equals("192.168.0.105")) {
            ctx.writeAndFlush("server authentication success");
            return true;
        } else {
            //短连接
            ctx.writeAndFlush("server authentication fail!")
                    .addListener(ChannelFutureListener.CLOSE);
            return false;
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof String) {
            auth(ctx, msg);
        } else if (msg instanceof RequestInfo) {

            RequestInfo info = (RequestInfo) msg;//因为使用了Marshalling序列化框架，所以可以直接转换为RequestInfo对象
            System.out.println("--------------------------------------------");
            System.out.println("当前主机ip为: " + info.getIp());
            System.out.println("当前主机cpu情况: ");
            HashMap<String, Object> cpu = info.getCpuPercMap();
            System.out.println("总使用率: " + cpu.get("combined"));
            System.out.println("用户使用率: " + cpu.get("user"));
            System.out.println("系统使用率: " + cpu.get("sys"));
            System.out.println("等待率: " + cpu.get("wait"));
            System.out.println("空闲率: " + cpu.get("idle"));

            System.out.println("当前主机memory情况: ");
            HashMap<String, Object> memory = info.getMemoryMap();
            System.out.println("内存总量: " + memory.get("total"));
            System.out.println("当前内存使用量: " + memory.get("used"));
            System.out.println("当前内存剩余量: " + memory.get("free"));
            System.out.println("--------------------------------------------");

            ctx.writeAndFlush("RequestInfo has received!");
        } else {
            ctx.writeAndFlush("connect failure!").addListener(ChannelFutureListener.CLOSE);
        }
    }
}*/
