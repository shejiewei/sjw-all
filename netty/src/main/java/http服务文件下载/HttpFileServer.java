package http服务文件下载;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by shejiewei on 2020/2/29.
 */
public class HttpFileServer {

    private static final String default_url="/src/";
    public void run(int port,String url) throws InterruptedException {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                          socketChannel.pipeline().addLast("http-decoder",
                                  new HttpRequestDecoder());
                          socketChannel.pipeline().addLast("http-aggregator",
                                  new HttpObjectAggregator(65536));
                          socketChannel.pipeline().addLast("http-enocder",
                                  new HttpResponseEncoder());
                          socketChannel.pipeline().addLast("http-chunked",
                                  new ChunkedWriteHandler());
                        socketChannel.pipeline().addLast("fileServerHandler",
                    new HttpFileServerHandler());
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("文件目录服务器启动,地址是127.0.0.1");
            future.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
     public static void main(String[] args) throws InterruptedException {
       int port=8080;
       String url=default_url;
       new HttpFileServer().run(port,url);
      }

}
