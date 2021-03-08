package com.zc.dubbo.spi;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author zhangchi
 */
@SPI
public interface RobotInterface {
    void sayHello();
}
