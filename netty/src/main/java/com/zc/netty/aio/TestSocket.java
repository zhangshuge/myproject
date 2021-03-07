package com.zc.netty.aio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhangchi
 */
public class TestSocket {
    public static void main(String[] args) throws Exception{
        ServerSocket socket = new ServerSocket(8080);
        System.out.println("step1 : create server socket of port is 8080");
        //死循环接收客户端访问，并为每个client创建一个受理thread
        while (true){
            Socket client = socket.accept();
            System.out.println("step2: accept client, port is :" + client.getPort());
            new Thread(() ->{
                try {
                    InputStream in = client.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    while (true){
                        System.out.println(br.readLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
