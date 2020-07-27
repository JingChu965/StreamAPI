package com.jingchu.jdk8;


import com.sun.jmx.snmp.SnmpUnknownMsgProcModelException;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @description: StreamApi 流操作
 * @author: JingChu
 * @createtime :2020-07-27 11:22:25
 **/
public class StreamOprator {
    /**
     * 筛选与切片
     */
    @Test
    public void test() {
        List<User> users = Arrays.asList(
                new User("张三", 24),
                new User("李四", 15),
                new User("王五", 16),
                new User("赵六", 27),
                new User("田七", 18)
        );
        Stream<User> stream = users.stream()
                .filter(e -> {
                    System.out.println("Stream API中间操作");
                    return e.age >= 18;
                }).limit(2);
        //终止操作
        stream.forEach(System.out::println);
    }

    class User {
        String name;
        int age;

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    /**
     * 映射
     */
    @Test
    public void test1() {
        List<String> list = Arrays.asList("aaa", "bbbb", "ccc");
        list.stream()
                .map((x) -> x.toUpperCase())
                .forEach(System.out::println);
    }

    /**
     * 排序
     */
    @Test
    public void test2() {
        List<String> list = Arrays.asList("ddd", "aaa", "bbbb", "ccc");
        System.out.println("---------自然排序");
        list.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------zidingyi排序");
        list.stream()
                .sorted((a,b)->{
                    if(a.equals(b)){
                        return a.compareTo(b);
                    }else {
                        return -a.compareTo(b);
                    }
                })
                .forEach(System.out::println);

    }
    /**
     * 查找与匹配
     */
    @Test
    public void test3(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();

        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());
    }

    /**
     * 规约
     */
    @Test
    public void test4(){
        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        //确定有初始值的情况接收
        Integer sum = numbers.stream()
                .reduce(0,(x,y)->x+y);
        System.out.println(sum);

        //不确定有没有初始值
        Optional<Integer> optionalInteger = numbers.stream()
                .reduce(Integer::sum);
        System.out.println(optionalInteger.get());
    }

    /**
     * 收集
     */

    @Test
    public void test5(){
        List<String>strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        System.out.println("筛选列表: " + filtered);
        String mergedString = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.joining(", "));
        System.out.println("合并字符串: " + mergedString);
    }
}
