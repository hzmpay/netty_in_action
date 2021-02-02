package com.hzm.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月02日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class EchoServer {

    private int port;

    public static void main(String[] args) throws Exception {
//        if (args.length != 1) {
//            log.error(
//                    "Usage: " + EchoServer.class.getSimpleName() +
//                            " <port>");
//        }
//        int port = Integer.parseInt(args[0]);
        new EchoServer(8888).start();
    }

    public void start() throws Exception {
        EchoServerChannelHandler serverHandler = new EchoServerChannelHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    // 指定所使用的NIO传输 Channel
                    .channel(NioServerSocketChannel.class)
                    // 设置端口套接字信息
                    .localAddress(new InetSocketAddress(port))
                    // 添加自己的ChannelHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(serverHandler);
                        }
                    });
            // 异步地绑定服务器，调用 sync()方法阻塞，等待直到绑定完成
            ChannelFuture channelFuture = bootstrap.bind().sync();
            // 获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            // 关闭 EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }
    }
}
