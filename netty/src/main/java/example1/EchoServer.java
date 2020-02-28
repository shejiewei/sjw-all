package example1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * Created by shejiewei on 2020/2/28.
 */
public class EchoServer {

    private final int port;
    public EchoServer(int port){
        this.port=port;
    }
     public static void main(String[] args) throws InterruptedException {

         int port=8088;
         new EchoServer(port).start();

      }

    public void  start() throws InterruptedException {
        EchoServerHandler echoServerHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            ServerBootstrap b= new ServerBootstrap();
            b.group(group)
                     .channel(NioServerSocketChannel.class)//指定nio所使用的channel
                     .localAddress(new InetSocketAddress(port))//指定套接口地址
                 .childHandler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel channel) throws Exception {
                             channel.pipeline().addLast(echoServerHandler);   //echoserverhandle被标注为@Shareable
                         //,所以我们总是使用同样的实例
                     }
                 });
            ChannelFuture f= b.bind().sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            group.shutdownGracefully().sync();
        }


    }


}
