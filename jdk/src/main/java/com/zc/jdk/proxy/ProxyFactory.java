package com.zc.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author zhangchi
 */
public class ProxyFactory {
    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 生成代理对象
     *
     * @return
     */
    public Object getProxyInstance() {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事物");
                        //执行目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("提交事物");
                        return returnValue;
                    }
                });
    }

    public static void main(String[] args) {
        //目标对象,即实现UserService接口的UserServiceImpl实现类
        UserService target = new UserServiceImpl();

        //目标对象的原始类型：com.zc.jdk.proxy.UserServiceImpl
        System.out.println(target.getClass());

        //为目标对象创建代理对象
        UserService proxy = (UserService) new ProxyFactory(target).getProxyInstance();
        //这里打印的将是内存中动态生成的代理对象
        System.out.println(proxy.getClass());
        //执行代理对象方法
        proxy.print();
    }
}
