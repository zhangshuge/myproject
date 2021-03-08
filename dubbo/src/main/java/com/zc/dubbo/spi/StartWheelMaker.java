package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;

public class StartWheelMaker implements WheelMaker{
    @Override
    public Wheel makeWheel(URL url) {
        System.out.println("StartWheelMaker is runing");
        return null;
    }
}
