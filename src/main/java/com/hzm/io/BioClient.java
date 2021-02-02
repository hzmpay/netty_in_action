package com.hzm.io;

import java.io.IOException;
import java.net.Socket;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月13日
 */
public class BioClient {

    public static void main(String[] args) throws IOException, InterruptedException {

        Socket socket = new Socket("localhost", 8081);

        while (true) {

            String msg = "hello world!";

            byte[] bytes = msg.getBytes("utf-8");
            socket.getOutputStream().write(bytes);
            Thread.sleep(1000);
        }
    }
}
