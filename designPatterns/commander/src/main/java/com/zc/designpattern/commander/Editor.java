package com.zc.designpattern.commander;

/**
 * 命令接收者接口
 */
public interface Editor {
    /**
     * save command
     */
    public void save();

    /**
     * open command
     */
    public void open();

    /**
     * close command
     */
    public void close();
}
