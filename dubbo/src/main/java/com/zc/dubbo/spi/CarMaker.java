package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;

public interface CarMaker {
    Car makeCar(URL url);
}
