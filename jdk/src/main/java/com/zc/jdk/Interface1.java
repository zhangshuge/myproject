package com.zc.jdk;

public interface Interface1 {
    default void df(){
        System.out.println("Interface1  :  df");
    }
    static void sf(){
        System.out.println("Interface1  :  sf");
    }
}
