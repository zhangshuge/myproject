package com.zc.jdk.data.structure;

public interface MapWapper<k,v> {
    int size();
    v put(k key,v value);
    v get(Object key);
    interface Entry<k,v>{
        k getKey();
        v getValue();
        v setValue(v value);
    }
}
