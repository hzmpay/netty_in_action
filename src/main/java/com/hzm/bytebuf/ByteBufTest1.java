package com.hzm.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * ByteBuf的复合缓冲区模式
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月07日
 */
@Slf4j
public class ByteBufTest1 {

    public static void main(String[] args) {
        CompositeByteBuf msgByteBuf = Unpooled.compositeBuffer();
        ByteBuf header = Unpooled.copiedBuffer("header".getBytes(CharsetUtil.UTF_8));
        ByteBuf body = Unpooled.copiedBuffer("body".getBytes(CharsetUtil.UTF_8));
        msgByteBuf.addComponents(header, body);

        msgByteBuf.removeComponent(0);
        for (ByteBuf byteBuf : msgByteBuf) {
            log.info("打印byteBuf：{}", byteBuf.toString());
        }
    }
}
