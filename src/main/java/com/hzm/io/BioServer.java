package com.hzm.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2021年01月13日
 */
public class BioServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocket socket = new ServerSocket(8081);
        Socket clientSocket = socket.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String result;
        while ((result = in.readLine()) != null) {
            Thread.sleep(200);
            System.out.println(result);
        }

    }
}
