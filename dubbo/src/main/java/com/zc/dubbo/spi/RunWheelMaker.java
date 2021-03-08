package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;

public class RunWheelMaker implements WheelMaker{
    @Override
    public Wheel makeWheel(URL url) {
        System.out.println("RunWheelMaker is runing");
        return null;
    }
}
