package com.jingchu.jdk8;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @description: StreamAPI 创建流
 * @author: JingChu
 * @createtime :2020-07-27 09:32:11
 **/
public class MyStreamCreate {


    /**
     * 创建流
     */
    @Test
    public void createStream() {

        /**
         * 1。
         * 用collection集合的stream() − 为集合创建串行流
         *
         * 或者用collection集合的parallelStream() − 为集合创建并行流。
         */
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();


        /**
         * 2。通过Arrays中的静态方法stream()获取数组流
         *  这里因为不想创建对象了，就使用了一个系统对象夫类Object
         */

        Object[] objects = new Object[10];
        Stream<Object> stream2 = Arrays.stream(objects);
        /**
         * 3。通过Stream类中的静态方法of
         */
        Stream<String> stream3 = Stream.of("aa", "bb", "cc");

        /**
         * 4.创建无限流
         */
        //迭代产生无限流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        //查看结果，用了中间流
        stream4.limit(10)
                .forEach(System.out::println);
        //生成无限流
        Stream.generate(() -> Math.random())
                .limit(5)
                .forEach(System.out::println);
    }
}
