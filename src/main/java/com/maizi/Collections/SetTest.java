package com.maizi.Collections;

import com.maizi.entity.Person;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: java类作用描述
 * @Author: Liuys
 * @CreateDate: 2020/5/19 18:33
 * @Version: 1.0
 */
public class SetTest {
    public static void main(String[] args){
        Person p = new Person();
        p.setId(1);
        p.setName("aa");
        Person p1 = new Person();
        p1.setId(2);
        p1.setName("aa");
        Person p2 = new Person();
        p2.setId(null);
        p2.setName("aa");
        Set<Person> set = new HashSet<>();
        set.add(p);
        set.add(p1);
        set.add(p2);
        System.out.println(set.size());
        set.forEach( pp ->{
            System.out.println(pp.getId() + "," + pp.getName());
        });
    }
}
