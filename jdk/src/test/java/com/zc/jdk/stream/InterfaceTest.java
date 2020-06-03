package com.zc.jdk.stream;

import com.zc.jdk.Impl1;
import com.zc.jdk.Interface1;
import com.zc.jdk.Interface2;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InterfaceTest {
    @Test
    public void dfTest(){
        //调用接口中的默认方法
        Impl1 impl1 = new Impl1();
        impl1.df();
        //调用接口中的静态方法
        Interface1.sf();
        //Interface2继承了Interface1，但是不能使用其static方法.Impl1实现了Interface1，同样也不能使用其static方法。
        //Interface2.sf();
        //impl1.sf();
    }



    public static Class[] mapClasses(final List<String> exceptions) {
        return exceptions.stream().map(className -> {
            try {
                return Class.forName(className);
            } catch(Exception ex) {
                System.out.println("Failed to load class for exceptionWhiteList");
            }
            return null;
        }).toArray(Class[]::new);
    }


    public static Class[] mapClassesBetter(final List<String> exceptions) {
        return exceptions.stream().map(InterfaceTest::mapClass).toArray(Class[]::new);
    }
    public static Class mapClass(String className) {
        try {
            return Class.forName(className);
        } catch(Exception ex) {
            System.out.println("Failed to load class ");
        }
        return null;
    }



    @Test
    public void streamDebug(){
        Stream.of("hello","how","what","hi","head","hello")
                .filter(s -> s.startsWith("h"))
                .map(m -> m.substring(2))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    @Test
    public void lambdaDebug(){

    }
}
