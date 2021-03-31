package com.sanshao.offer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description: java传值还是传引用
 * @Author: Liuys
 * @CreateDate: 2021/3/29 10:36
 * @Version: 1.0
 */
public class TestTransferValue {

    public int changeValue1(int age){
        age = 30;
        return age;
    }
    public void changeValue2(Person person){
        System.out.println(person);
        person.setName("xxxx");
    }
    public void changeValus3(String str){
        str = "xxx";
    }
    public static void main(String[] args){
        TestTransferValue test = new TestTransferValue();
        //栈管运行,堆管存储
        int age = 20;
        int ageCopy = test.changeValue1(age);
        //打印结果:20  基本类型传的是备份(复印件)
        System.out.println(age);
        System.out.println(ageCopy);
        //----------------
        Person person = new Person("abc");
        System.out.println(person);
        test.changeValue2(person);
        System.out.println("Person====" + person.getName());

        String str = "abc";
        test.changeValus3(str);
        System.out.println(str);

    }
}

@Setter
@Getter
@NoArgsConstructor
class Person{
    String name;
    int age;

    Person(String name){
        this.name = name;
    }
}
