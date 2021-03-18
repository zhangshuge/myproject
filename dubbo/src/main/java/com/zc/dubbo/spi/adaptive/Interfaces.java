package com.zc.dubbo.spi.adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author zhangchi
 */
@SPI
public interface Interfaces {

    @Adaptive({"impl","number"})
    void hello(URL url);
}
