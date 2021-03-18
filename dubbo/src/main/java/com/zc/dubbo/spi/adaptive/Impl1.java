package com.zc.dubbo.spi.adaptive;

import org.apache.dubbo.common.URL;

public class Impl1 implements Interfaces{
    @Override
    public void hello(URL url) {
        System.out.println("impl 1");
    }
}
