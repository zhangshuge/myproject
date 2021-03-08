package com.zc.dubbo.spi;

import org.apache.dubbo.common.URL;

public class WheeMain {
    public static void main(String[] args) {
        RaceCarMaker carMaker = new RaceCarMaker();
        carMaker.setWheelMaker(new AdaptiveWheelMaker());
        //dubbo://localhost:8080?Wheel.maker=run
        URL url = new URL("dubbo", "localhost", 8080);
        carMaker.makeCar(url.addParameter("Wheel.maker", "run"));
        carMaker.makeCar(url.addParameter("Wheel.maker", "start"));
    }
}
