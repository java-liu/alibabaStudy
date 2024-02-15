package com.ljava.somemethod.java8Stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxMinCountStreamDemo {
    public static void main(String[] args) {
        maxStream();
    }

    public static void maxStream() {
        List<String>  list = Arrays.asList("java", "php", "python", "go", "c++");

        Optional<String> max = list.stream().max(Comparator.comparing(String::length));
        System.out.println("最长的字符串为:" + max.get());


        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //自然排序
        Optional<Integer> max1 = list1.stream().max(Comparator.naturalOrder());
        //list1.stream().max(Integer::compareTo);
        Optional<Integer> max2 = list1.stream().max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println("自然排序的最大值:" + max1.get());
        System.out.println("自定义排序的最大值:" + max2.get());

        //获取员工工资最高的人
        List<Person> personList = Arrays.asList(new Person("张三", 10000, 20, "男", "北京"),
                new Person("李四", 2000, 21, "男", "上海"),
                new Person("王五", 3000, 22, "女", "深圳"),
                new Person("赵六", 4000, 23, "女", "杭州"));

        Optional<String> maxSalaryName = personList.stream().max(Comparator.comparingInt(Person::getSalary)).map(p -> p.getName());
        Optional<Person> maxSalaryPerson = personList.stream().max(Comparator.comparingInt(Person::getSalary));

        System.out.println("员工工资最高的人:" + maxSalaryName.get());
        System.out.println("员工工资最大值:" + maxSalaryPerson.get().getSalary());

        //计算Integer集合中大于5的元素的个数
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        long count = list2.stream().filter(i -> i > 5).count();
        System.out.println("大于5的元素的个数:" + count);
    }
}
