package com.zc.jdk;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class HelloWorld {
    public static void main(String[] args) {
        // System.out.println("Hello World!");
        //System.out.println(new Object());
        Consumer<String> consumer1 = s -> System.out.print("车名："+s.split(",")[0]);
        Consumer<String> consumer2 = s -> System.out.println("-->颜色："+s.split(",")[1]);

        String[] strings = {"保时捷,白色", "法拉利,红色"};
        for (String string : strings) {
            consumer1.andThen(consumer2).accept(string);
        }

        Function<Integer,Integer> function1 = e -> e * 2;
        Function<Integer,Integer> function2 = e -> e * 3;
        Integer apply2 = function1.compose(function2).apply(3);
        System.out.println(apply2);


        Function<Integer, Integer> identity = Function.identity();
        Integer apply = identity.apply(3);
        System.out.println(apply);
/*


        Function<Integer, Integer> identityFunction = Function.identity();
        Function<Integer, Integer> intFunction = e -> e;
        System.out.println(identityFunction.apply(10)); // 10
        System.out.println(intFunction.apply(10)); // 10
        List<String> names = Arrays.asList(
                "Peter",
                "Martin",
                "John",
                "Vijay",
                "Arthur"
        );
        // Just for example
        System.out.println("----- Function.identity() -----");
        names.stream().map(Function.identity()).forEach(System.out::println);
        System.out.println("----- Function(e-> e) -----");
        names.stream().map(e -> e).forEach(System.out::println);
        System.out.println("------------");
        names.stream().forEach(System.out::println);


        Predicate<String> p1 = s -> s.length() > 0;
        Predicate<String> p2 = Objects::nonNull;
        boolean flag = p1.and(p2).test("Hello World");
        System.out.println(flag);

        Predicate<String> p3 = s -> false;
        Predicate<String> p4 = Objects::nonNull;
        boolean flagOr = p3.or(p4).test("Hello World");
        System.out.println(flagOr);


        Predicate<Integer> p5 = e -> e > 5;
        boolean flagNegate = p5.negate().test(10);
        System.out.println(flagNegate);


        boolean test1 = Predicate.isEqual("test").test("test");
        boolean test2 = Predicate.isEqual("test").test("equal");
        System.out.println(test1);   //true
        System.out.println(test2);   //false

*/

        System.out.println(StringUtils.startsWithAny("1dafa","1","2","3"));

        System.out.println(StringUtils.startsWithAny("2dafa","1","2","3"));
        System.out.println(StringUtils.startsWithAny("3dafa","1","2","3"));
        System.out.println(StringUtils.startsWithAny("4dafa","1","2","3"));

    }



}
