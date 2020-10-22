package com.zc.jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp
 * @author zhangchi
 */
public class HeapOom {
    static class OomObject{};
    public static void main(String[] args) {
        List<OomObject> list = new ArrayList<OomObject>();
        while (true){
            list.add(new OomObject());
        }
    }
}
