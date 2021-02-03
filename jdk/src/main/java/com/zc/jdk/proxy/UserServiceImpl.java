package com.zc.jdk.proxy;

/**
 * @author zhangchi
 */
public class UserServiceImpl implements UserService{
    @Override
    public void print() {
        System.out.println("------目标对象------");
    }
}
