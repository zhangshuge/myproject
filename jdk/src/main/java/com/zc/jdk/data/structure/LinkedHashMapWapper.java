package com.zc.jdk.data.structure;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapWapper<k,v> extends HashMapWapper<k,v> implements MapWapper<k,v> {
    static class Entry<k,v> extends HashMapWapper.Node<k,v> {
        LinkedHashMapWapper.Entry<k,v> before, after;
        Entry(int hash, k key, v value, HashMapWapper.Node<k,v> next) {
            super(hash, key, value, next);
        }
    }
}
