package com.zc.jdk;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Java8FunctionIdentityExample {

    /**
     * 将列表转换为集合，如果列表有重复->显示重复计数
     */
    public static void main(String[] args) {

        List<String> names = Arrays.asList(
                "Peter",
                "Martin",
                "John",
                "Peter",
                "Vijay",
                "Martin",
                "Peter",
                "Arthur"
        );
        Set<String> namesSet = new HashSet(names);

        names.stream()
                .map(getFunction(names, names.size() != namesSet.size()))
                .collect(Collectors.toSet())
                .forEach(System.out::println);


    }

    static Function<String, String> getFunction(List<String> names, boolean hasDuplicates) {

        return hasDuplicates ?
                name -> name + " (" + Collections.frequency(names, name) + ")"  //如果集合中有重复元素也就是hasDuplicates为false的时候执行本行代码
                : Function.identity();//如果集合中没有重复元素也就是hasDuplicates为true的时候执行本行代码
    }
}