package com.hzm.bytebuf;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

/**
 * ByteBuffer的复合缓冲区模式
 *
 * @author Hezeming
 * @version 1.0
 * @date 2021年02月07日
 */
@Slf4j
public class ByteBufferTest1 {

    public static void main(String[] args) {

        ByteBuffer header = ByteBuffer.allocate(4);
        ByteBuffer body = ByteBuffer.allocate(4);
        ByteBuffer[] msg = new ByteBuffer[]{header, body};
        ByteBuffer msg2 = ByteBuffer.allocate(header.remaining() + body.remaining());

        msg2.put(header);
        msg2.put(body);
        msg2.flip();
    }
}
