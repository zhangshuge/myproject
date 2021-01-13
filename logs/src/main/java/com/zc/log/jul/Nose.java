package com.zc.log.jul;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nose {
    private static Logger logger = Logger.getLogger("com.zc.nose");
    //从父管理器集成基础配置信息，包括路径和级别。
    private static Logger loggerSun = Logger.getLogger("com.zc.nose.sun");
    static {
        try {
            Handler fh = new FileHandler("/Users/zhangchi/Downloads/wombat.log",true);
            MyFormatter formatter = new MyFormatter();
            fh.setFormatter(formatter);

            Logger.getLogger("com.zc.nose").addHandler(fh);
            Logger.getLogger("com.zc.nose").setLevel(Level.FINEST);
            logger.setUseParentHandlers(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        logger.setLevel(Level.INFO);
        logger.fine("记录跟踪信息");
        logger.info("info----");
        logger.log(Level.WARNING, "trouble sneezing", new Exception());

        logger.log(Level.FINE,"level fine");
        logger.fine("done");

        loggerSun.info("sun logger is info");
        loggerSun.fine("sun logger is fine");
    }
}
