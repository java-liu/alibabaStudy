package com.ljava.somemethod.java8Stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo1 {
    public static void main(String[] args) {
        //findTest();
        filterTest();
    }

    /**
     * Stream也是支持类似集合的遍历和匹配元素的,只是Stream中的元素是以Optional类型存在的.
     */
    public static void findTest(){
        List<Integer> list = Arrays.asList(7,6,8,3,5,1,4);
        //遍历输出符合条件的元素
        list.stream().filter((item)-> item > 5).forEach(System.out::println);

        //匹配第一个
        Integer result = list.stream().filter((item)-> item > 5).findFirst().get();
        Optional<Integer> findFirst = list.stream().filter((item)-> item > 5).findFirst();

        //匹配任意
        Optional<Integer> findAny = list.parallelStream().filter((item)-> item > 5).findAny();

        //是否包含符合特定条件的元素
        boolean anyMatch = list.stream().anyMatch((item)-> item > 5);

        System.out.println("result:" + result);
        System.out.println("findFirst:" + findFirst);
        System.out.println("findAny:" + findAny);
        System.out.println("anyMatch:" + anyMatch);
    }

    public static void filterTest(){
        //筛选出Integer集合中大于5的元素,并打印出来
        List<Integer> list = Arrays.asList(7,6,8,3,5,1,4);
        Stream<Integer> stream = list.stream();
        stream.filter((item)-> item > 5).forEach(System.out::println);

        //筛选员工中工资高于8000的人,并形成新的集合.形成新集合
        List<Person> personList = Arrays.asList(new Person("张三", 10000, 20, "男", "北京"),
                new Person("李四", 2000, 21, "男", "上海"),
                new Person("王五", 3000, 22, "女", "深圳"),
                new Person("赵六", 4000, 23, "女", "杭州"));

        List<String> filterList = personList.stream().filter((item)-> item.getSalary() > 8000).map((item)-> item.getName()).collect(Collectors.toList());

        System.out.println("高于8000的员工姓名:" + filterList);
    }
}
