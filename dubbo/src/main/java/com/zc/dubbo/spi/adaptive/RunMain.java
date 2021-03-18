package com.zc.dubbo.spi.adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.apache.dubbo.common.utils.NetUtils;
import org.apache.dubbo.remoting.RemotingServer;
import org.apache.dubbo.remoting.exchange.ExchangeChannel;
import org.apache.dubbo.remoting.exchange.Exchangers;

public class RunMain {
    public static void main(String[] args) throws Exception{

        /*ExtensionLoader<Interfaces> extensionLoader = ExtensionLoader.getExtensionLoader(Interfaces.class);
        Interfaces impl1 = extensionLoader.getExtension("impl1");
        impl1.hello();
        Interfaces impl2 = extensionLoader.getExtension("impl2");
        impl2.hello();*/

       /* ExchangeChannel client = Exchangers.connect(URL.valueOf("exchange://172.0.0.1:20880?client=netty3&heartbeat=1000"));
        client.request()*/

        ExtensionLoader<Interfaces> extensionLoader = ExtensionLoader.getExtensionLoader(Interfaces.class);
        extensionLoader.getAdaptiveExtension();
    }
}
