package com.hzm.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.buffer.DefaultByteBufHolder;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月07日
 */
@Slf4j
public class ByteBufHolderTest {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("123456".getBytes(CharsetUtil.UTF_8));

        ByteBufHolder byteBufHolder = new DefaultByteBufHolder(byteBuf);

        // 浅拷贝
        ByteBufHolder duplicate = byteBufHolder.duplicate();
        outByteBufStr(duplicate);
        byteBuf.setByte(0, 'A');
        outByteBufStr(duplicate);

        log.info("<=========================== duplicate ===========================>");

        // 深拷贝
        ByteBufHolder copy = byteBufHolder.copy();
        outByteBufStr(copy);
        byteBuf.setByte(0, 'B');
        outByteBufStr(copy);

        log.info("<=========================== copy ===========================>");
    }


    public static void outByteBuf(ByteBufHolder byteBufHolder) {
        log.info("byteBufHolder : {}", byteBufHolder);
    }

    public static void outByteBufStr(ByteBufHolder byteBufHolder) {
        ByteBuf content = byteBufHolder.content();
        log.info("byteBufHolder info : {}", content.toString(CharsetUtil.UTF_8));
    }

}
