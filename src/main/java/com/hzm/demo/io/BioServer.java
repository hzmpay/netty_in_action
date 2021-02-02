package com.hzm.demo.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO服务端
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月01日
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8000);

        // 接收新连接线程
        new Thread(() -> {
            while (true) {
                try {
                    // 阻塞方法获取新的连接
                    final Socket socket = serverSocket.accept();

                    // 每个新的连接都创建一个新的线程，负责读取数据
                    new Thread(() -> {
                        InputStream inputStream = null;
                        try {
                            int len;
                            byte[] data = new byte[1024];
                            inputStream = socket.getInputStream();

                            // 按字节流方式读取数据
                            while ((len = inputStream.read(data)) != -1) {
                                System.out.println("接收到数据 ：" + new String(data, 0, len));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }).start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
