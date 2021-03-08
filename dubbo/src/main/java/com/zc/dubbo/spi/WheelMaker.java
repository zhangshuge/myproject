package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.SPI;

@SPI
public interface WheelMaker {
    Wheel makeWheel(URL url);
}
