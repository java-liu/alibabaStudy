package com.ljava.somemethod.java8Stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByStreamDemo {
    public static void main(String[] args) {
        method1();
    }
    public static void method1(){
        List<Person> personList = new ArrayList<Person>();
        personList.add(new Person("Tom", 8900, 22,"male", "New York"));
        personList.add(new Person("Jack", 7000, 23, "male", "Washington"));
        personList.add(new Person("Lily", 7800, 24,"female", "Washington"));
        personList.add(new Person("Anni", 8200, 25,"female", "New York"));
        personList.add(new Person("Owen", 9500, 26,"male", "New York"));
        personList.add(new Person("Alisa", 7900, 27,"female", "New York"));

        // 将员工按薪资是否高于8000分组
        Map<Boolean, List<Person>> part = personList.stream().collect(Collectors.partitioningBy(x -> x.getSalary() > 8000));
        // 将员工按性别分组
        Map<String, List<Person>> group = personList.stream().collect(Collectors.groupingBy(Person::getSex));
        // 将员工先按性别分组，再按地区分组
        Map<String, Map<String, List<Person>>> group2 = personList.stream().collect(Collectors.groupingBy(Person::getSex, Collectors.groupingBy(Person::getArea)));
        System.out.println("员工按薪资是否大于8000分组情况：" + part);
        System.out.println("员工按性别分组情况：" + group);
        System.out.println("员工按性别、地区：" + group2);
    }
}
