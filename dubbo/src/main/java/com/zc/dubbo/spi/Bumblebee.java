package com.zc.dubbo.spi;

public class Bumblebee implements RobotInterface{
    @Override
    public void sayHello() {
        System.out.println("Hello, I'm bumblebee");
    }
}
