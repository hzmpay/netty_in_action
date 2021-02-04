package com.hzm.jdk;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BIO客户端
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月02日
 */
public class BioClient {

    public static void main(String[] args) {

        new Thread(() -> {
            try {
                Socket socket = new Socket("localhost", 8000);

                while (true) {
                    try {
//                        socket.getOutputStream().write(("now time :" + new SimpleDateFormat("YYYY-mm-dd HH:mm:ss").format(new Date())).getBytes());
//                        Thread.sleep(2000);

                        byte[] bytes = "你好，欢迎关注我的微信公众号，《闪电侠的博客》!".getBytes(Charset.forName("utf-8"));
                        socket.getOutputStream().write(bytes);
                        Thread.sleep(10);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
