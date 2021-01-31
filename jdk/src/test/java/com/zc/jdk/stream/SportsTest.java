package com.zc.jdk.stream;

import com.zc.jdk.balls.Ball;
import com.zc.jdk.balls.BasketBall;
import com.zc.jdk.balls.FootBall;
import com.zc.jdk.balls.Sports;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SportsTest {
    @Test
    public void sumPeople() {
        BasketBall basketBall = new BasketBall();
        basketBall.setPeople(10);
        FootBall footBall = new FootBall();
        footBall.setPeople(22);


        List<Ball> balls = new ArrayList<>();
        balls.add(basketBall);
        balls.add(footBall);
        List<Sports> sports = new ArrayList<>();
        Sports sport = new Sports();
        sport.setBalllist(balls);
        sports.add(sport);
        int peoples = sports.parallelStream()
                .flatMap(Sports::getSports)
                .mapToInt(Ball::getPeople).sum();
        assert peoples == 32;
    }

    @Test
    public void paralleDiceRolls() {
        double fraction = 1.0 / 1000;
        Map<Integer, Double> map = IntStream.range(0, 1000)
                .parallel()
                .mapToObj(twoDiceThrows())
                .collect(Collectors.groupingBy(side -> side, Collectors.summingDouble(n -> fraction)));
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public IntFunction<Integer> twoDiceThrows() {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return e -> threadLocalRandom.nextInt(1, 7) + threadLocalRandom.nextInt(1, 7);
    }

    @Test
    public void sumTest(){
        List<Integer> list = Arrays.asList(1,2,3,4);
        int sum = list.parallelStream().mapToInt(i -> i).sum();
        assert sum == 10;

    }

}
