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
public class ByteBufAllocatorTest {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("123456".getBytes(CharsetUtil.UTF_8));

        ByteBufAllocator byteBufAllocator = new PooledByteBufAllocator();

        // 返回一个基于堆或者直接内存存储的 ByteBuf
        ByteBuf buffer = byteBufAllocator.buffer();

        // 返回一个基于堆内存存储的ByteBuf
        ByteBuf heapByteBuf = byteBufAllocator.heapBuffer();

        // 返回一个基于直接内存存储的ByteBuf
        ByteBuf directBuffer = byteBufAllocator.directBuffer();

        // 返回一个用于套接字的 I/O 操作的 ByteBuf
        ByteBuf ioBuffer = byteBufAllocator.ioBuffer();

        // 返回一个可以通过添加最大到指定数目的基于堆的或者直接内存存储的缓冲区来扩展的CompositeByteBuf
        CompositeByteBuf compositeByteBuf = byteBufAllocator.compositeBuffer();

    }


    public static void outByteBuf(ByteBufHolder byteBufHolder) {
        log.info("byteBufHolder : {}", byteBufHolder);
    }

    public static void outByteBufStr(ByteBufHolder byteBufHolder) {
        ByteBuf content = byteBufHolder.content();
        log.info("byteBufHolder info : {}", content.toString(CharsetUtil.UTF_8));
    }

}
