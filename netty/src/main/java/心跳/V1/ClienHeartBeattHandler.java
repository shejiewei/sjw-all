/*
package 心跳;

*/
/**
 * Created by shejiewei on 2019/1/24.
 *//*

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.Swap;

public class ClienHeartBeattHandler extends ChannelHandlerAdapter {

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> heartBeat;

    */
/*channel激活所执行的方法*//*

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String ip = InetAddress.getLocalHost().getHostAddress();
        //在实际的项目中可能需要根据具体需求使用特定的认证信息，并加密处理认证信息，保证网络传输认证信息的安全
        ctx.writeAndFlush(ip);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            if(msg instanceof String){
                String ret = (String)msg;
                if("server authentication success".equals(ret)){
                    // 握手成功，主动发送心跳消息。初始化时等待0秒，每隔2秒执行一次发送心跳信息的任务
        			*/
/*Parameters:
        				command: the task to execute
        				initialDelay: the time to delay first execution
        				delay: the delay between the termination of one execution and the commencement of the next
        				unit: the time unit of the initialDelay and delay parameters*//*

                    this.heartBeat = this.scheduler.scheduleWithFixedDelay(new HeartBeatTask(ctx), 0, 2, TimeUnit.SECONDS);
                    System.out.println("Client:接收到Server端返回信息"+msg);
                }
                else {
                    System.out.println(msg);
                }
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    //内部类，心跳信息的类
    private class HeartBeatTask implements Runnable {
        private final ChannelHandlerContext ctx;

        public HeartBeatTask(final ChannelHandlerContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                RequestInfo info = new RequestInfo();
                InetAddress addr=InetAddress.getLocalHost();
                info.setIp(addr.getHostAddress());

                Sigar sigar = new Sigar();
                //cpu info
                CpuPerc cpuPerc = sigar.getCpuPerc();
                HashMap<String, Object> cpuPercMap = new HashMap<String, Object>();
                cpuPercMap.put("combined", cpuPerc.getCombined());
                cpuPercMap.put("user", cpuPerc.getUser());
                cpuPercMap.put("sys", cpuPerc.getSys());
                cpuPercMap.put("wait", cpuPerc.getWait());
                cpuPercMap.put("idle", cpuPerc.getIdle());
                //memory info
                Mem mem = sigar.getMem();
                HashMap<String, Object> memoryMap = new HashMap<String, Object>();
                memoryMap.put("total", mem.getTotal() / 1024L);
                memoryMap.put("used", mem.getUsed() / 1024L);
                memoryMap.put("free", mem.getFree() / 1024L);
                info.setCpuPercMap(cpuPercMap);
                info.setMemoryMap(memoryMap);
                ctx.writeAndFlush(info);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            if (heartBeat != null) {
                heartBeat.cancel(true);
                heartBeat = null;
            }
            ctx.fireExceptionCaught(cause);
        }

    }
}
*/
