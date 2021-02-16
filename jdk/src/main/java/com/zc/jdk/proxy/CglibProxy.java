package com.zc.jdk.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zhangchi
 */
public class CglibProxy implements MethodInterceptor {
    //维护目标对象
    private Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    /**
     * 创建代理对象,实际将是在内存中生成的目标对象的一个子类
     * @return
     */
    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        //设置父类,即目标对象
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类，即代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事物");
        //执行目标对象的方法
        Object returnValue = method.invoke(target,objects);
        System.out.println("提交事物");
        return returnValue;
    }


    public static void main(String[] args) {
        //目标对象就是一个简单的类，没有实现任何接口
        Target target = new Target();
        //获取代理对象
        Target proxy = (Target) new CglibProxy(target).getProxyInstance();
        //执行代理对象方法
        proxy.print();
    }
}
class Target{
    public void print() {
        System.out.println("------目标对象------");
    }
}
