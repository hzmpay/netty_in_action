package com.hzm.demo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * 自定义编码器
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月13日
 */
public class DemoDecoder extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        out.add(in.toString(CharsetUtil.UTF_8));
    }
}
