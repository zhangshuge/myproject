package com.zc.jvm.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * VM Args : -XX:PermSize=6M -XX:MaxPermSize=6M
 * @author zhangchi
 */
public class MethodAreaOom {
    public static void main(String[] args) {
        MethodAreaOom oom = new MethodAreaOom();
        oom.cgLibTest();
    }

    private void test1(){
        //使用set保持常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true){
            set.add(String.valueOf(i++).intern());
        }
    }

    private void cgLibTest(){
        while (true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OomObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor(){
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }
    static class OomObject{}
}
