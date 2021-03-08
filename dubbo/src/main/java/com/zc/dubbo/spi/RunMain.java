package com.zc.dubbo.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.ServiceLoader;

public class RunMain {
    public static void main(String[] args) {
        ServiceLoader<RobotInterface> serviceLoader = ServiceLoader.load(RobotInterface.class);
        serviceLoader.forEach(RobotInterface::sayHello);

        ExtensionLoader<RobotInterface> extensionLoader = ExtensionLoader.getExtensionLoader(RobotInterface.class);
        RobotInterface bumblebee = extensionLoader.getExtension("Bumblebee");
        bumblebee.sayHello();
        RobotInterface optimusPrime = extensionLoader.getExtension("OptimusPrime");
        optimusPrime.sayHello();
    }
}
