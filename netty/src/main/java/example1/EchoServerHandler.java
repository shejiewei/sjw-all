package example1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * Created by shejiewei on 2020/2/28.
 */
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "server is active!!", CharsetUtil.UTF_8
        ));
        ctx.fireChannelActive();
    }
    @Override
     public void channelRead(ChannelHandlerContext ctx,Object msg){
        ByteBuf in=(ByteBuf)msg;
        System.out.println("Server receive::::"+in.toString(CharsetUtil.UTF_8));
       // ctx.write(in);//将接收到信息写给发送者,而不冲刷出站消息
     }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
        //将未决消息冲刷到远端节点,并且关闭该channel
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.writeAndFlush(Unpooled.copiedBuffer(
                "channel 关闭了!!", CharsetUtil.UTF_8
        ));
       cause.printStackTrace();
       ctx.close();//异常后关闭
    }

}
