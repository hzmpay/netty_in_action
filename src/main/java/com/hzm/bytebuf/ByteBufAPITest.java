package com.hzm.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.ByteProcessor;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月07日
 */
@Slf4j
public class ByteBufAPITest {

    public static void main(String[] args) {


        ByteBuf byteBuf = Unpooled.copiedBuffer("123456789123456789".getBytes(CharsetUtil.UTF_8));

        // 分片的效果，内存是共享的
        ByteBuf slice = byteBuf.slice(0, 9);

        outByteBufStr(slice);

        slice.setByte(0, (byte) 'A');

        outByteBufStr(byteBuf);
        outByteBufStr(slice);
        log.info("equals : {}", byteBuf.getByte(0) == slice.getByte(0));

        log.info("<=========================== slice ===========================>");

        ByteBuf copy = byteBuf.copy(0, 9);
        copy.setByte(0, (byte) 'B');

        outByteBufStr(byteBuf);
        outByteBufStr(copy);
        log.info("equals : {}", byteBuf.getByte(0) == copy.getByte(0));

        log.info("<=========================== copy ===========================>");


    }

    public static void test1() {
        ByteBuf byteBuf1 = Unpooled.copiedBuffer("123456".getBytes(CharsetUtil.UTF_8));
        // ridx: 0, widx: 6, cap: 6/6
        outByteBuf(byteBuf1);
        // get set不会修改索引
        log.info("getByte : {}", byteBuf1.getByte(5));
        outByteBuf(byteBuf1);
        log.info("read : {}", byteBuf1.readBytes(4));
        outByteBuf(byteBuf1);
        log.info("read : {}", byteBuf1.readBytes(1));
        outByteBuf(byteBuf1);
        // 回收readerIndex的空间，扔掉已读数据
        byteBuf1.discardReadBytes();
        log.info("discardReadBytes after : {}", byteBuf1);
//        byteBuf1.discardSomeReadBytes();
//        log.info("discardSomeReadBytes after : {}", byteBuf1);
        outByteBuf(byteBuf1);

        while (byteBuf1.isReadable()) {
            log.info("read : {}", byteBuf1.readByte());
        }
        // ridx: 1, widx: 1, cap: 6/6
        outByteBuf(byteBuf1);

        byteBuf1.writeBytes("12345".getBytes(CharsetUtil.UTF_8));

        outByteBuf(byteBuf1);

        log.info("ByteBuf info : {} ", byteBuf1.readBytes(4).toString(CharsetUtil.UTF_8));

        outByteBuf(byteBuf1);

        byteBuf1.markReaderIndex();
        outByteBuf(byteBuf1);

        byteBuf1.markWriterIndex();
        outByteBuf(byteBuf1);

        byteBuf1.resetReaderIndex();
        outByteBuf(byteBuf1);

        // 清除buf（把readerIndex和writerIndex都设为0）
        byteBuf1.clear();
        outByteBuf(byteBuf1);

        byteBuf1.writeBytes("123".getBytes(CharsetUtil.UTF_8));
        int index = byteBuf1.forEachByte(new ByteProcessor.IndexOfProcessor(new Byte("1")));
        log.info("1 index ：{}", index);
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
