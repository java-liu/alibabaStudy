package com.ljava.somemethod.java8Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapFlatMapStreamDemo {

    public static void main(String[] args) {
        //mapFlatMapStream();
        //mapFlatMapStream2();
        mapFlatMapStream3();
    }

    public static void mapFlatMapStream() {
        String[] strArr = {"abcd", "debc", "jipu", "UIo"};
        List<String> stringList = Arrays.stream(strArr).map(String::toUpperCase).collect(Collectors.toList());
        System.out.println("每个元素大写:" + stringList);

        //整数数组每个元素 + 3
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> intListNew = intList.stream().map(x -> x + 3).collect(Collectors.toList());
        System.out.println("每个元素 + 3:" + intListNew);
    }

    /**
     * 英文字符串数组的元素全部改为大写.整数数组每个元素 + 3
     * 将员工的薪资全部增加1000
     */
    public static void mapFlatMapStream2() {
        List<Person> personList = Arrays.asList(new Person("张三", 10000, 20, "男", "北京"),
                new Person("李四", 2000, 21, "男", "上海"),
                new Person("王五", 3000, 22, "女", "深圳"),
                new Person("赵六", 4000, 23, "女", "杭州"));

        //不改变原来员工集合的方式
        List<Person> personListNew = personList.stream().map(person -> {
            Person personNew = new Person(person.getName(), 0, 0, null, null);
            personNew.setSalary(person.getSalary() + 1000);
            return personNew;
        }).collect(Collectors.toList());
        System.out.println("一次改动前:" + personList.get(0).getName() + "---->" + personList.get(0).getSalary());
        System.out.println("一次改动后,员工工资加1000:" + personListNew.get(0).getName() + "---->" + personListNew.get(0).getSalary());

        //改变原来员工集合的方式
        List<Person> personListNew2 = personList.stream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        System.out.println("一次改动前:" + personList.get(0).getName() + "---->" + personList.get(0).getSalary());
        System.out.println("一次改动后,员工工资加1000:" + personListNew2.get(0).getName() + "---->" + personListNew2.get(0).getSalary());
    }

    /**
     * 将两个字符数组合并成一个新的字符数组
     */
    public static void mapFlatMapStream3() {
        List<String> list = Arrays.asList("1,4,5,8", "j,a,v,a");
        List<String> listNew = list.stream().flatMap(x -> {
            String[] split = x.split(",");
            Stream<String> s2 = Arrays.stream(split);
            return s2;
        }).collect(Collectors.toList());
        System.out.println("处理前的集合:" + list);
        System.out.println("处理后的集合listNew:" + listNew);
    }
}
