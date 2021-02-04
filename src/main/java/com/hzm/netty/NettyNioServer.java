package com.hzm.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月03日
 */
public class NettyNioServer {

    public static final int port = 8888;

    public static void main(String[] args) throws InterruptedException {

        ByteBuf byteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("hi, I am NettyNioServer！".getBytes(CharsetUtil.UTF_8)));

        // 相对于NettyOioServer改动点1
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    // 相对于NettyOioServer改动点2
                    .channel(NioServerSocketChannel.class)
                    .localAddress(port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    ctx.writeAndFlush(byteBuf)
                                            .addListener(ChannelFutureListener.CLOSE);
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = b.bind().sync();
            Channel channel = channelFuture.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            group.shutdownGracefully().sync();
        }
    }
}
