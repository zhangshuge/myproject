package com.zc.jdk.proxy;

/**
 * @author zhangchi
 */
public class UserServiceProxy implements UserService {

    /**
     * 接收将要被代理的目标对象
     */
    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void print() {
        System.out.println("开始事物------");
        userService.print();
        System.out.println("提交事物------");
    }

    public static void main(String[] args) {
        //创建目标对象
        UserServiceImpl impl = new UserServiceImpl();
        //把目标对象传给代理对象
        UserServiceProxy proxy = new UserServiceProxy(impl);
        proxy.print();//执行代理对象的方法
    }
}
