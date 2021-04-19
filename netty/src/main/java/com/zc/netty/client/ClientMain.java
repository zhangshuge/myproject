package com.zc.netty.client;

public class ClientMain {
    public static void main(String[] args) {
        new ChatClient().connect("localhost",8080,"HelloNettyClient");
    }
}
