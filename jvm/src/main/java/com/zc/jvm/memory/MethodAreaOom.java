package com.zc.jvm.memory;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args : -XX:PermSize=6M -XX:MaxPermSize=6M
 * @author zhangchi
 */
public class MethodAreaOom {
    public static void main(String[] args) {
        //使用set保持常量池引用，避免Full GC回收常量池行为
        Set<String> set = new HashSet<>();
        short i = 0;
        while (true){
            set.add(String.valueOf(i++).intern());
        }
    }
}
