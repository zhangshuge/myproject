package com.zc.designpattern.commander;

/**
 * 命令者，封装了具体的命令执行信息
 */
public class EditorImpl implements Editor{
    @Override
    public void save() {
        System.out.println("save");
    }

    @Override
    public void open() {
        System.out.println("open");
    }

    @Override
    public void close() {
        System.out.println("close");
    }
}
