package com.zc.test;

import javax.print.DocFlavor;
import java.util.*;
import java.util.stream.Collectors;

public class Test {
    @org.junit.Test
    public void hasetSize() {

        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.remove();
        threadLocal.set("");

        Set set = new HashSet(10);
        Model m1 = new Model();
        Model m2 = new Model();
        Model m3 = new Model();
        m1.setNum("aaa");
        m2.setNum("aaa");
        m3.setNum("bbb");
        List<Model> list = new ArrayList<>();
        list.add(m1);
        list.add(m2);
        list.add(m3);

        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(m -> m.getNum(), Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + ":" + v));
        map.forEach((k, v) -> {
            if (v > 1) {
                System.out.println(k);
            }
        });

        //最终写法
        list.stream().collect(Collectors.groupingBy(m -> m.getNum(), Collectors.counting())).forEach((k, v) -> {
            new Exception();
        });
        //循环校验是否存在数据库当中
    }

    @org.junit.Test
    public void singletonListTest(){
        Model model = new Model();
        List<Model> list = Collections.singletonList(model);
        System.out.println(list);


    }
}

class Model {
    private String num;

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

}
