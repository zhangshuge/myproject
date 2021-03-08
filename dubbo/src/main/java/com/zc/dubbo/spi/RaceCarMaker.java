package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;

public class RaceCarMaker implements CarMaker{
    WheelMaker wheelMaker;
    // 通过 setter 注入 AdaptiveWheelMaker
    public void setWheelMaker(WheelMaker wheelMaker) {
        this.wheelMaker = wheelMaker;
    }

    @Override
    public Car makeCar(URL url) {
        Wheel wheel = wheelMaker.makeWheel(url);
        return new Car(wheel);
    }
}
