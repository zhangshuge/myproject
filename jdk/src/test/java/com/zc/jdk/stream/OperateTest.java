package com.zc.jdk.stream;


import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.sun.javafx.scene.SceneEventDispatcher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class OperateTest {

    List<Artist> allArtists = new ArrayList<>();

    @Before
    public void setUp() {
        Artist artist1 = new Artist();
        artist1.setIsFrom("北京");
        artist1.setName("张三");
        Artist artist2 = new Artist();
        artist2.setIsFrom("上海");
        artist2.setName("李四");
        allArtists.add(artist1);
        allArtists.add(artist2);
    }

    @Test
    public void demo1() {
        allArtists.stream().filter(artist -> artist.isFrom("北京"));
    }

    @Test
    public void demo2() {
        allArtists.stream().filter(artist -> {
            System.out.println("demo2" + artist.getName());
            return artist.isFrom("北京");
        });
    }

    @Test
    public void demo3() {
        long count = allArtists.stream().filter(artist -> {
            System.out.println("demo3:" + artist.getName());
            return artist.isFrom("北京");
        }).count();
    }

    @Test
    public void demo4() {
        Artist artist = new Artist();
        artist.setIsFrom("上海");
        artist.setName("李四");
        allArtists.add(artist);
        allArtists.stream().distinct().forEach(e -> System.out.println(e.getName()));
        System.out.println("--------------------------------");
        allArtists.stream().forEach(e -> System.out.println(e.getName()));
        System.out.println("--------------------------------");
        Stream.of("1", "1", "2").distinct().forEach(System.out::println);
    }

    @Test
    public void collectTest() {
        List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
        assertEquals(Arrays.asList("a", "b", "c"), collect);
    }

    @Test
    public void mapTest() {
        List<String> map = Stream.of("a", "b", "c").map(s -> s.toUpperCase()).collect(Collectors.toList());
        assertEquals(Arrays.asList("A", "B", "C"), map);
    }

    @Test
    public void filterTest() {
        List<String> filter = Stream.of("a", "1abc", "abc1").filter(s -> s.matches("[0-9]+.*")).collect(Collectors.toList());
        assertEquals(Arrays.asList("1abc"), filter);
    }

    @Test
    public void flatMapTest() {
        List<Integer> flatMap = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4))
                .flatMap(strings -> strings.stream())
                .collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), flatMap);
    }

    @Test
    public void maxAndMinTest() {
        Integer max = Stream.of(1, 2, 3, 4).max(Comparator.comparing(Function.identity())).get();
        assert max == 4;
        Integer min = Stream.of(1, 2, 3, 4).min(Comparator.comparing(Function.identity())).get();
        assert min == 1;
        long count = Stream.of(1, 2, 3, 4).count();
        assert count == 4;
    }

    @Test
    public void reduceTest() {
        Integer ruduce = Stream.of(1, 2, 3, 4).reduce(0, (accumulator, currentElement) -> accumulator + currentElement);
        assert ruduce == 10;
        Integer integer = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        Optional<Integer> integer2 = Stream.of(1, 2, 3, 4).reduce((a, b) -> a + b);
        System.out.println(integer2.get());
        assert integer == 10;
        BinaryOperator<Integer> accumulator = (acc, element) -> acc + element;
        int sum = accumulator.apply(accumulator.apply(accumulator.apply(accumulator.apply(0, 1), 2), 3), 4);
        assert sum == 10;
    }

    @Test
    public void parallelTest() {
        Stream.of().parallel();
    }

    @Test
    public void optionalFilterTest() {
        Optional<Integer> optional1 = Optional.ofNullable(1);
        Optional<Integer> optional2 = Optional.ofNullable(null);

        Optional<Integer> filter1 = optional1.filter((a) -> a == null);
        Optional<Integer> filter2 = optional1.filter((a) -> a == 1);
        Optional<Integer> filter3 = optional2.filter((a) -> a == null);

        System.out.println(filter1.isPresent());// false
        System.out.println(filter2.isPresent());// true
        System.out.println(filter2.get().intValue() == 1);// true
        System.out.println(filter3.isPresent());// false
    }

    @Test
    public void methodReferenceTest() {
        Consumer<String> consumer = s -> System.out.println(s);
        consumer.accept("default method");
        //使用方法引用::
        Consumer<String> stringConsumer = System.out::println;
        stringConsumer.accept("method reference");

        // Function<Integer, int[]> fun = n -> new int[n];
        Function<Integer, int[]> fun = int[]::new;
        int[] arr = fun.apply(10);
        System.out.println(arr);
    }

    @Test
    public void collectHigherTest() {
        //转换list
        List<Integer> lists = Stream.of(1, 2, 3, 4).collect(Collectors.toList());
        assertEquals(Arrays.asList(1, 2, 3, 4), lists);
        //转换set
        Set<Integer> sets = Stream.of(1, 2, 3, 4).collect(Collectors.toSet());
        assertEquals(Sets.newHashSet(1, 2, 3, 4), sets);
        //通过toCollection()可以转换成自定义类型
        TreeSet<Integer> treeSet = Stream.of(1, 2, 3, 4).collect(Collectors.toCollection(TreeSet::new));
        assertEquals(new TreeSet(Arrays.asList(1, 2, 3, 4)), treeSet);
    }

    @Test
    public void maxByTest() {
        Optional<String> optionals = Stream.of("zhangsan", "lisi")
                .collect(Collectors.maxBy(Comparator.comparing(s -> s.length())));
        assertEquals("zhangsan", optionals.get());
        double avg = Stream.of("zhangsan", "lisi")
                .collect(Collectors.averagingInt(s -> s.length()));
        assert avg == 6;

    }

    @Test
    public void partitioningByTest() {
        //按照以z开通的元祖进行分块
        Map<Boolean, List<String>> map = Stream.of("zhangchi", "zhangmochen", "cuixiaoxue")
                .collect(Collectors.partitioningBy(s -> s.startsWith("z")));
        map.forEach((k, v) -> System.out.println(k + "---" + v));
    }

    @Test
    public void groupingByTest() {
        Map<Object, List<String>> map = Stream.of("zhangchi", "zhangmochen", "cuixiaoxue", "zhangchi")
                .collect(Collectors.groupingBy(s -> s.length()));
        map.forEach((k, v) -> System.out.println(k + "---" + v));
    }

    @Test
    public void joiningTest() {
        String str = Stream.of("zhangchi", "zhangmochen", "cuixiaoxue", "zhangchi")
                .collect(Collectors.joining(",", "[", "]"));
        System.out.println(str);
    }

    @Test
    public void mappingTest() {

        Model model1 = new Model("zhangchi", 30);
        Model model2 = new Model("cuixiaoxue", 30);
        Model model3 = new Model("zhangmochen", 3);

        Map<Integer, List<String>> map = Stream.of(model1, model2, model3).collect(
                Collectors.groupingBy(Model::getAge, Collectors.mapping(Model::getName, Collectors.toList()))
        );
        map.forEach((k, v) -> System.out.println(k + "---" + v));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class Model {
        private String name;
        private int age;
    }

    public void computeTest(){
        Map map = new HashMap();
        map.computeIfAbsent("", s -> s);
    }
}