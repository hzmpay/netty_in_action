package com.hzm.bytebuf;

import io.netty.buffer.*;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月07日
 */
@Slf4j
public class UnpooledTest {

    public static void main(String[] args) {


        // 返回一个未池化的基于堆内存存储的ByteBuf
        ByteBuf buffer = Unpooled.buffer(10).writeBytes("123456".getBytes(CharsetUtil.UTF_8));
        outByteBuf(buffer);

        // 返回一个未池化的基于直接内存存储的ByteBuf
        ByteBuf directBuffer = Unpooled.directBuffer();
        outByteBuf(directBuffer);

        // 返回一个复制了给定数据的 ByteBuf
        ByteBuf copiedBuffer = Unpooled.copiedBuffer("123456".getBytes(CharsetUtil.UTF_8));
        outByteBuf(copiedBuffer);

        // 返回一个包装了给定数据的 ByteBuf
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer("123456".getBytes(CharsetUtil.UTF_8));
        outByteBuf(wrappedBuffer);

        log.info("ByteBufUtil.hexDump :{} ", ByteBufUtil.hexDump(buffer));

    }

    public static void outByteBuf(ByteBuf byteBuf) {
        log.info("byteBuf : {}", byteBuf);
    }

    public static void outByteBufStr(ByteBuf byteBuf) {
        log.info("byteBuf info : {}", byteBuf.toString(CharsetUtil.UTF_8));
    }

    public static void outIndex(ByteBuf byteBuf1) {
        log.info("capacity : {}", byteBuf1.capacity());
        log.info("readerIndex : {}", byteBuf1.readerIndex());
        log.info("writerIndex : {}", byteBuf1.writerIndex());
    }
}
